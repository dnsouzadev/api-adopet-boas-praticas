package br.com.dnsouzadev.adopet.api.controller;

import br.com.dnsouzadev.adopet.api.dto.AbrigoDto;
import br.com.dnsouzadev.adopet.api.model.Abrigo;
import br.com.dnsouzadev.adopet.api.model.Pet;
import br.com.dnsouzadev.adopet.api.repository.AbrigoRepository;
import br.com.dnsouzadev.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService service;

    @GetMapping
    public ResponseEntity<List<Abrigo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody AbrigoDto dto) {
        service.cadastrar(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<Pet>> listarPets(@PathVariable String idOuNome) {
        return ResponseEntity.ok(service.listarPets(idOuNome));
    }

    @PostMapping("/{idOuNome}/pets")
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid Pet pet) {
        service.cadastrarPet(idOuNome, pet);
        return ResponseEntity.ok("Pet cadastrado com sucesso!");
    }

}
