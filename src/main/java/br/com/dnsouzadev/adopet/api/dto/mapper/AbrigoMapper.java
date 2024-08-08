package br.com.dnsouzadev.adopet.api.dto.mapper;

import br.com.dnsouzadev.adopet.api.dto.AbrigoDto;
import br.com.dnsouzadev.adopet.api.model.Abrigo;

public class AbrigoMapper {

    public static AbrigoDto toDto(Abrigo abrigo) {
        return new AbrigoDto(
                abrigo.getNome(),
                abrigo.getTelefone(),
                abrigo.getEmail(),
                abrigo.getPets()
        );
    }

    public static Abrigo toEntity(AbrigoDto dto) {
        return new Abrigo(
                dto.nome(),
                dto.telefone(),
                dto.email(),
                dto.pets()
        );
    }
}
