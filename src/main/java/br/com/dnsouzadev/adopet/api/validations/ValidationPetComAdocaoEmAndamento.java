package br.com.dnsouzadev.adopet.api.validations;

import br.com.dnsouzadev.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.dnsouzadev.adopet.api.exception.ValidacaoException;
import br.com.dnsouzadev.adopet.api.model.StatusAdocao;
import br.com.dnsouzadev.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidationPetComAdocaoEmAndamento implements ValidationSolicitacaoAdocao{

    @Autowired
    private AdocaoRepository repository;

    public void validar(SolicitacaoAdocaoDto dto) {
        boolean petTemAdocaoEmAndamento = repository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (petTemAdocaoEmAndamento) throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
    }
}
