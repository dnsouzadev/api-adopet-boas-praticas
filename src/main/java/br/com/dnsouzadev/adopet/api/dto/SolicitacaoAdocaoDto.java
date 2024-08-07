package br.com.dnsouzadev.adopet.api.dto;

public record SolicitacaoAdocaoDto(
        Long idTutor,
        Long idPet,
        String motivo
) {
}
