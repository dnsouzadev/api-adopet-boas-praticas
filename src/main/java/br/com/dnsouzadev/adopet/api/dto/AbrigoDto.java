package br.com.dnsouzadev.adopet.api.dto;

import br.com.dnsouzadev.adopet.api.model.Pet;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AbrigoDto(
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank @Email String email,
        @NotNull List<Pet> pets
        ) {
}
