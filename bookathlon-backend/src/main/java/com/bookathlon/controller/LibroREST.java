package com.bookathlon.controller;

import java.util.List;

// Importazioni delle annotazioni Spring necessarie per costruire un controller REST
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookathlon.entities.Libro;
import com.bookathlon.service.LibroService;

// Questa classe è un controller REST che gestisce richieste HTTP riguardanti i libri.
@RestController
public class LibroREST {

// Iniezione automatica del servizio LibroService da parte di Spring
	@Autowired
	private LibroService service;
	
// Metodo che gestisce le richieste GET all'endpoint "api/libri"
	@GetMapping("api/libri")
	public List<Libro> getLibri(){
		return service.getLibri(); // restituisce la lista di tutti i libri disponibili
	}
	
// Metodo che gestisce le richieste POST all'endpoint "api/libri"
	@PostMapping("api/libri")
	public Libro addLibro(@RequestBody Libro l) {
		return service.addLibro(l);  // Aggiunge un nuovo libro ricevuto nel corpo della richiesta
		}
	
}
