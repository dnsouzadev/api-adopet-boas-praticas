package br.com.dnsouzadev.adopet.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TutorDto(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String telefone) {
}
