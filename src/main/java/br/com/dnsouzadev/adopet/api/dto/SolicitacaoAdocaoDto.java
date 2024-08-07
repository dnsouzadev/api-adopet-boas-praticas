package br.com.dnsouzadev.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoAdocaoDto(
        @NotNull Long idTutor,
        @NotNull Long idPet,
        @NotBlank String motivo
) {
}
