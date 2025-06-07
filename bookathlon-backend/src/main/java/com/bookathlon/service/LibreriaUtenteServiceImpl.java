package com.bookathlon.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.LibreriaUtente;
import com.bookathlon.entities.LibreriaUtenteId;
import com.bookathlon.entities.Libro;
import com.bookathlon.entities.Utente;
import com.bookathlon.repos.LibreriaUtenteRepository;
import com.bookathlon.repos.LibroRepository;
import com.bookathlon.repos.UtenteRepository;

@Service
public class LibreriaUtenteServiceImpl implements LibreriaUtenteService {

	@Autowired
    private LibreriaUtenteRepository repo;

    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private LibroRepository libroRepo;
    
	@Override
	public List<LibreriaUtente> getLibreriaUtente(Long utenteId) {
		return repo.findByUtenteId(utenteId);
	}

	@Override
	public List<LibreriaUtente> getLibriByStato(Long utenteId, String stato) {
		return repo.findByUtenteIdAndStato(utenteId, stato);
	}

	@Override
	public LibreriaUtente aggiungiLibro(Long utenteId, Long libroId, String stato) {
		Utente utente = utenteRepo.findById(utenteId).orElseThrow();
        Libro libro = libroRepo.findById(libroId).orElseThrow();
        LibreriaUtente entry = new LibreriaUtente();
      
        entry.setUtente(utente);
        entry.setLibro(libro);
        entry.setStato(stato);
        entry.setDataAggiunta(LocalDate.now());
        
        return repo.save(entry);
	}

	@Override
	public void rimuoviLibro(Long utenteId, Long libroId) {
	    Utente utente = utenteRepo.findById(utenteId).orElseThrow();
	    Libro libro = libroRepo.findById(libroId).orElseThrow();

	    LibreriaUtenteId id = new LibreriaUtenteId();
	    id.setUtente(utente);
	    id.setLibro(libro);

	    repo.deleteById(id);
	}

}
