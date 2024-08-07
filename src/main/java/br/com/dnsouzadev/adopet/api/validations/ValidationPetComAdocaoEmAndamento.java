package br.com.dnsouzadev.adopet.api.validations;

import br.com.dnsouzadev.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.dnsouzadev.adopet.api.exception.ValidacaoException;
import br.com.dnsouzadev.adopet.api.model.Adocao;
import br.com.dnsouzadev.adopet.api.model.Pet;
import br.com.dnsouzadev.adopet.api.model.StatusAdocao;
import br.com.dnsouzadev.adopet.api.repository.AdocaoRepository;
import br.com.dnsouzadev.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationPetComAdocaoEmAndamento implements ValidationSolicitacaoAdocao{

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = repository.findAll();
        Pet pet = petRepository.getReferenceById(dto.idPet());
        for (Adocao a : adocoes) {
            if (a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
            }
        }
    }
}
