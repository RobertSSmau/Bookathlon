package com.bookathlon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookathlon.entities.LibreriaUtente;
import com.bookathlon.entities.LibreriaUtenteId;

/**
 * Repository per l'entità LibreriaUtente.
 * Estende JpaRepository per fornire operazioni CRUD di base
 * e query derivate per l'accesso ai dati della libreria utente.
 */
public interface LibreriaUtenteRepository extends JpaRepository<LibreriaUtente, LibreriaUtenteId> { 

	List<LibreriaUtente> findByUtenteId(Long utenteId);
	
	List<LibreriaUtente> findByUtenteIdAndStato(Long utenteId, String stato);
	
}
	

