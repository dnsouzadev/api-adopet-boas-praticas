package br.com.dnsouzadev.adopet.api.controller;

import br.com.dnsouzadev.adopet.api.model.Adocao;
import br.com.dnsouzadev.adopet.api.model.StatusAdocao;
import br.com.dnsouzadev.adopet.api.repository.AdocaoRepository;
import br.com.dnsouzadev.adopet.api.service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoService adocaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid Adocao adocao) {
        try {
            adocaoService.solicitar(adocao);
            return ResponseEntity.ok("Solicitação de adoção registrada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid Adocao adocao) {
        try {
            adocaoService.aprovar(adocao);
            return ResponseEntity.ok("Adoção aprovada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid Adocao adocao) {
        try {
            adocaoService.reprovar(adocao);
            return ResponseEntity.ok("Adoção reprovada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
