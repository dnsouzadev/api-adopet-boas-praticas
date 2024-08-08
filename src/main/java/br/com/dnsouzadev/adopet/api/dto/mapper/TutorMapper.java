package br.com.dnsouzadev.adopet.api.dto.mapper;

import br.com.dnsouzadev.adopet.api.dto.TutorDto;
import br.com.dnsouzadev.adopet.api.model.Tutor;

public class TutorMapper {

        public static TutorDto toDto(Tutor tutor) {
            return new TutorDto(tutor.getNome(), tutor.getEmail(), tutor.getTelefone());
        }

        public static Tutor toEntity(TutorDto dto) {
            return new Tutor(dto.nome(), dto.email(), dto.telefone());
        }

}
