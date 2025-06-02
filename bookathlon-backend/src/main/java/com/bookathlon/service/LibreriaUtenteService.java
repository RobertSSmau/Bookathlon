package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.LibreriaUtente;

public interface LibreriaUtenteService {

	List<LibreriaUtente> getLibreriaUtente(Long utenteId);
    List<LibreriaUtente> getLibriByStato(Long utenteId, String stato);
    LibreriaUtente aggiungiLibro(Long utenteId, Long libroId, String stato);
    void rimuoviLibro(Long utenteId, Long libroId);
	
}
