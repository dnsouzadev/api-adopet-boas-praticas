package br.com.dnsouzadev.adopet.api.repository;

import br.com.dnsouzadev.adopet.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByAdotadoIsTrue();
}
