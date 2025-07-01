package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.Libro;

/**
 * Questa interfaccia definisce le operazioni per la gestione dei libri, includendo il recupero, la ricerca per ID, l'aggiunta, il recupero dei libri di tendenza e la ricerca per parola chiave.
 */
public interface LibroService {

    /**
     * Recupera una lista di tutti i libri presenti nel sistema.
     */
    List<Libro> getLibri();

    /**
     * Recupera un singolo libro tramite il suo ID.
     */
    Libro getLibroById(Long id);

    /**
     * Aggiunge un nuovo libro al sistema.
     */
    Libro addLibro(Libro l);

    /**
     * Recupera una lista di libri considerati "di tendenza" secondo la logica implementata nel servizio.
     */
    List<Libro> getLibriDiTendenza();

    /**
     * Esegue una ricerca di libri basata su una parola chiave, filtrando su vari campi come titolo, autore o genere.
     */
    List<Libro> cercaTitolo(String titolo);
    
    List<Libro> cercaAutore(String autore);
}






