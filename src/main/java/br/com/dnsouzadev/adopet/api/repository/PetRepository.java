package br.com.dnsouzadev.adopet.api.repository;

import br.com.dnsouzadev.adopet.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
