package br.com.dnsouzadev.adopet.api.controller;

import br.com.dnsouzadev.adopet.api.dto.TutorDto;
import br.com.dnsouzadev.adopet.api.dto.mapper.TutorMapper;
import br.com.dnsouzadev.adopet.api.model.Tutor;
import br.com.dnsouzadev.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService service;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody TutorDto tutor) {
        Tutor tutorEntity = TutorMapper.toEntity(tutor);
        service.cadastrar(tutorEntity);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizar(@RequestBody @Valid TutorDto tutor) {
        Tutor tutorEntity = TutorMapper.toEntity(tutor);
        service.atualizar(tutorEntity);
        return ResponseEntity.ok().build();
    }

}
