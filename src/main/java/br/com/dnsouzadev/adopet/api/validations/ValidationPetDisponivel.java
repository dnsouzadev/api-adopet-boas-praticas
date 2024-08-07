package br.com.dnsouzadev.adopet.api.validations;

import br.com.dnsouzadev.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.dnsouzadev.adopet.api.exception.ValidacaoException;
import br.com.dnsouzadev.adopet.api.model.Pet;
import br.com.dnsouzadev.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPetDisponivel {

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!");
        }
    }
}
