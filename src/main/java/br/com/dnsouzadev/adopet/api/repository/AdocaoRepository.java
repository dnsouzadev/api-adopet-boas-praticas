package br.com.dnsouzadev.adopet.api.repository;

import br.com.dnsouzadev.adopet.api.model.Adocao;
import br.com.dnsouzadev.adopet.api.model.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    boolean existsByPetIdAndStatus(Long idPet, StatusAdocao status);
    boolean existsByTutorIdAndStatus(Long idTutor, StatusAdocao status);
    int countByTutorIdAndStatus(Long idTutor, StatusAdocao status);
}
