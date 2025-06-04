package com.bookathlon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookathlon.entities.Libro;
import com.bookathlon.service.LibroService;

/**
 * RestController gestisce le operazioni HTTP relative ai libri
 * Espone i punti di accesso a un'API(Endpoint) per ottenere l'elenco dei libri e aggiungerne uno nuovo 
 */

@RestController
public class LibroREST {

	//Spring inietta automaticamente un'istanza del servizio LibroService
	@Autowired
	private LibroService service;
	

	/**
	 * Endpoint GET permette di ottenere la lista di tutti i libri
	 * @return una lista di oggetti {@link Libro}
	 */
	@GetMapping("api/libri")
	public List<Libro> getLibri(){
		//Chiama il service per recuperare tutti i libri
		return service.getLibri();
	}
	
	/**
	 * Endpoint POST per aggiungere un nuovo libro
	 * @param l'oggetto {@link Libro} ricevuto nel corpo della richiesta
	 * @return il libro appena salvato, incluso l'eventuale ID assegnato dal sistema
	 */
	@PostMapping("api/libri")
	public Libro addLibro(@RequestBody Libro l) {
		//Invia il libro al service per salvarlo nel database
		return service.addLibro(l);
		}
	
}
