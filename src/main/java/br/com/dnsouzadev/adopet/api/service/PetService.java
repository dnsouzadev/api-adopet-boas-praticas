package br.com.dnsouzadev.adopet.api.service;

import br.com.dnsouzadev.adopet.api.model.Pet;
import br.com.dnsouzadev.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public List<Pet> listarTodosDisponiveis() {
        return repository.findByAdotadoIsTrue();
    }
}
