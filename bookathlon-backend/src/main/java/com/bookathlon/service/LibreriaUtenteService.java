package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.LibreriaUtente;

/**
 * Interfaccia per il servizio di gestione della libreria personale dell'utente.
 * Definisce le operazioni disponibili per interagire con i libri
 * all'interno della libreria di un utente, come la visualizzazione,
 * l'aggiunta e la rimozione di libri con specifici stati.
 */
public interface LibreriaUtenteService {

    /**
     * Recupera tutti i libri presenti nella libreria di un utente specifico.
     */
    List<LibreriaUtente> getLibreriaUtente(Long utenteId);

    /**
     * Recupera i libri dalla libreria di un utente in base a uno stato specifico.
     * Utile per filtrare i libri come "letti", "da leggere", ecc.
     */
    List<LibreriaUtente> getLibriByStato(Long utenteId, String stato);

    /**
     * Aggiunge un libro alla libreria di un utente con uno stato iniziale.
     * Se il libro esiste già per quell'utente, potrebbe aggiornarne lo stato.
     */
    LibreriaUtente aggiungiLibro(Long utenteId, Long libroId, String stato);

    /**
     * Rimuove un libro specifico dalla libreria di un utente.
     */
    void rimuoviLibro(Long utenteId, Long libroId);
    
    void iniziaLettura(Long utenteId, Long libroId);

}











