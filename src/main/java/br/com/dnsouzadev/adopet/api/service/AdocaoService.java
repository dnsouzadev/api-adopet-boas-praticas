package br.com.dnsouzadev.adopet.api.service;

import br.com.dnsouzadev.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.dnsouzadev.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.dnsouzadev.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.dnsouzadev.adopet.api.exception.ValidacaoException;
import br.com.dnsouzadev.adopet.api.model.Adocao;
import br.com.dnsouzadev.adopet.api.model.Pet;
import br.com.dnsouzadev.adopet.api.model.StatusAdocao;
import br.com.dnsouzadev.adopet.api.model.Tutor;
import br.com.dnsouzadev.adopet.api.repository.AdocaoRepository;
import br.com.dnsouzadev.adopet.api.repository.PetRepository;
import br.com.dnsouzadev.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private EmailService emailService;

    public void solicitar(SolicitacaoAdocaoDto dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());

        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!");
        } else {
            List<Adocao> adocoes = repository.findAll();
            for (Adocao a : adocoes) {
                if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    throw new ValidacaoException("Tutor já possui uma solicitação de adoção em andamento!");
                }
            }
            for (Adocao a : adocoes) {
                if (a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
                }
            }
            for (Adocao a : adocoes) {
                int contador = 0;
                if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.APROVADO) {
                    contador = contador + 1;
                }
                if (contador == 5) {
                    throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
                }
            }
        }

        Adocao adocao = new Adocao();
        adocao.setPet(pet);
        adocao.setTutor(tutor);
        adocao.setMotivo(dto.motivo());
        adocao.setData(LocalDateTime.now());
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        repository.save(adocao);

        emailService.enviarEmail(adocao.getPet().getAbrigo().getEmail(), "Solicitação de adoção", "Olá " +adocao.getPet().getAbrigo().getNome() +"!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +adocao.getPet().getNome() +". \nFavor avaliar para aprovação ou reprovação.");
    }

    public void aprovar(AprovacaoAdocaoDto dto) {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.setStatus(StatusAdocao.APROVADO);

        emailService.enviarEmail(adocao.getPet().getAbrigo().getEmail(), "Adoção aprovada", "Olá " +adocao.getPet().getAbrigo().getNome() +"!\n\nA adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +" foi aprovada. Favor entrar em contato com o tutor " +adocao.getTutor().getNome() +" para agendar a entrega do pet.");
    }

    public void reprovar(ReprovacaoAdocaoDto dto) {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.setStatus(StatusAdocao.REPROVADO);
        adocao.setJustificativaStatus(dto.justificativa());

        emailService.enviarEmail(adocao.getTutor().getEmail(), "Adoção reprovada", "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus());
    }
}