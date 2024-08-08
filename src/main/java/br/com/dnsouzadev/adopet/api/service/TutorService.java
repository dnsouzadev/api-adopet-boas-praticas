package br.com.dnsouzadev.adopet.api.service;

import br.com.dnsouzadev.adopet.api.exception.ValidacaoException;
import br.com.dnsouzadev.adopet.api.model.Tutor;
import br.com.dnsouzadev.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Transactional
    public void cadastrar(Tutor tutor) {
        boolean exists = repository.existsByTelefoneOrEmail(tutor.getTelefone(), tutor.getEmail());

        if (exists) throw new ValidacaoException("Telefone ou e-mail já cadastrado!");

        repository.save(tutor);
    }

    @Transactional
    public void atualizar(Tutor tutor) {
        repository.save(tutor);
    }
}
