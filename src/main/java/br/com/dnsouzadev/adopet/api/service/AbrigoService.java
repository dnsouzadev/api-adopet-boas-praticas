package br.com.dnsouzadev.adopet.api.service;

import br.com.dnsouzadev.adopet.api.dto.AbrigoDto;
import br.com.dnsouzadev.adopet.api.dto.mapper.AbrigoMapper;
import br.com.dnsouzadev.adopet.api.exception.ValidacaoException;
import br.com.dnsouzadev.adopet.api.model.Abrigo;
import br.com.dnsouzadev.adopet.api.model.Pet;
import br.com.dnsouzadev.adopet.api.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Transactional(readOnly = true)
    public List<Abrigo> listar() {
        return repository.findAll();
    }

    @Transactional
    public void cadastrar(AbrigoDto dto) {
        Abrigo abrigo = AbrigoMapper.toEntity(dto);

        boolean exists = repository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email());

        if (exists) throw new ValidacaoException("Dados já cadastrados para outro abrigo!");

        repository.save(abrigo);

    }

    @Transactional(readOnly = true)
    public List<Pet> listarPets(String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            return repository.getReferenceById(id).getPets();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Abrigo não encontrado!");
        } catch (NumberFormatException e) {
            try {
                return repository.findByNome(idOuNome).getPets();
            } catch (EntityNotFoundException ex) {
                throw new EntityNotFoundException("Abrigo não encontrado!");
            }
        }
    }

    @Transactional
    public void cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid Pet pet) {
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = repository.getReferenceById(id);
            pet.setAbrigo(abrigo);
            pet.setAdotado(false);
            abrigo.getPets().add(pet);
            repository.save(abrigo);
        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException("Abrigo não encontrado!");
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = repository.findByNome(idOuNome);
                pet.setAbrigo(abrigo);
                pet.setAdotado(false);
                abrigo.getPets().add(pet);
                repository.save(abrigo);
            } catch (EntityNotFoundException enfe) {
                throw new ValidacaoException("Abrigo não encontrado!");
            }
        }
    }
}
