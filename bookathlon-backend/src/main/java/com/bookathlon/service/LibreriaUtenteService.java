package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.LibreriaUtente;

/**
 * Questa interfaccia definisce le operazioni per la gestione della libreria personale di un utente.
 *  Permette di visualizzare, aggiungere e rimuovere libri, gestendone lo stato.
 */
public interface LibreriaUtenteService {

    /**
     * Recupera tutti i libri presenti nella libreria di un utente specifico.
     */
    List<LibreriaUtente> getLibreriaUtente(Long utenteId);

    /**
     * Recupera i libri dalla libreria di un utente filtrandoli per uno stato specifico (es. "letti", "da leggere").
     */
    List<LibreriaUtente> getLibriByStato(Long utenteId, String stato);

    /**
     * Aggiunge un libro alla libreria di un utente con uno stato iniziale, o ne aggiorna lo stato se già presente.
     */
    LibreriaUtente aggiungiLibro(Long utenteId, Long libroId, String stato);

    /**
     * Rimuove un libro specifico dalla libreria di un utente.
     */
    void rimuoviLibro(Long utenteId, Long libroId);
    
    void iniziaLettura(Long utenteId, Long libroId);

}











