package com.bookathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookathlon.entities.Libro;
import com.bookathlon.repos.LibroRepository;

/**
 * Questa classe implementa LibroService  e fornisce la logica di business per gestire i dati dei libri, inclusi recupero, aggiunta e ricerca.
 */
@Service 
public class LibroServiceImpl implements LibroService {

    @Autowired // inserisce l'istanza di LibroRepository.
    private LibroRepository repo;

    /**
     * Recupera tutti i libri presenti nel sistema delegando la chiamata al metodo findAll() del repository.
     */
    @Override
    public List<Libro> getLibri() {
        return repo.findAll(); // Ritorna tutti i libri trovati nel database.
    }

    /**
     * Recupera un singolo libro tramite il suo ID.
     * Delega la ricerca al metodo `findById()` del repository.
     */
    @Override
    public Libro getLibroById(Long id) {
        return repo.findById(id).orElse(null); // Cerca il libro per ID, ritorna null se non esiste.
    }

    /**
     * Aggiunge un nuovo libro al sistema o aggiorna uno esistente.
     * Delega l'operazione di salvataggio al metodo `save()` del repository.
     */
    @Override
    public Libro addLibro(Libro l) {
        return repo.save(l); // Salva il libro nel database.
    }

    
	    @Override
	public List<Libro> getLibriDiTendenza() {
	    return repo.trovaLibriPopolari();
	}

    
    /**
     * Esegue una ricerca di libri basata su una parola chiave.
     * Delega la ricerca al metodo `ricercaSQL()` del repository.
     */
	    @Override
	    public List<Libro> cercaTitolo(String titolo) {
	        return repo.titoloSQL(titolo);
	    }

	    @Override
	    public List<Libro> cercaAutore(String autore) {
	        return repo.autoreSQL(autore);
	    }
}