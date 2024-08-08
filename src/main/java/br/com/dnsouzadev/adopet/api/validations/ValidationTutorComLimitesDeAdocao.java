package br.com.dnsouzadev.adopet.api.validations;

import br.com.dnsouzadev.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.dnsouzadev.adopet.api.exception.ValidacaoException;
import br.com.dnsouzadev.adopet.api.model.Adocao;
import br.com.dnsouzadev.adopet.api.model.StatusAdocao;
import br.com.dnsouzadev.adopet.api.model.Tutor;
import br.com.dnsouzadev.adopet.api.repository.AdocaoRepository;
import br.com.dnsouzadev.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationTutorComLimitesDeAdocao implements ValidationSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository repository;

    public void validar(SolicitacaoAdocaoDto dto) {
        int contador = repository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO);
        if (contador == 5) throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");

    }
}
