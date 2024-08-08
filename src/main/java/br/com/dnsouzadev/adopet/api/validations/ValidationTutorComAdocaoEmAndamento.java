package br.com.dnsouzadev.adopet.api.validations;

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
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationTutorComAdocaoEmAndamento implements ValidationSolicitacaoAdocao{

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private TutorRepository tutorRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        boolean tutorTemAdocaoEmAndamento = repository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (tutorTemAdocaoEmAndamento) throw new ValidacaoException("Tutor já possui uma solicitação de adoção em andamento!");

    }
}
