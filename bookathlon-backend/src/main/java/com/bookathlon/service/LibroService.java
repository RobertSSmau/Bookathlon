package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.Libro;

/**
 * Interfaccia per il servizio di gestione dei libri.
 * Definisce le operazioni disponibili per interagire con i dati dei libri,
 * inclusi il recupero di tutti i libri, la ricerca per ID, l'aggiunta di nuovi libri,
 * il recupero dei libri di tendenza e la ricerca tramite parola chiave.
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
     * Recupera una lista di libri considerati "di tendenza".
     * La logica per definire un libro di tendenza è implementata nel servizio.
     */
    List<Libro> getLibriDiTendenza();

    /**
     * Esegue una ricerca di libri basata su una parola chiave.
     * La ricerca può essere effettuata su vari campi del libro (es. titolo, autore, genere).
     */
    List<Libro> cerca(String keyword);

}






