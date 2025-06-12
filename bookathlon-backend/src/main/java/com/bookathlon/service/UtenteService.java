package com.bookathlon.service;

import java.util.List;
import com.bookathlon.entities.Utente;

public interface UtenteService {
    /**
     * Recupera una lista di tutti gli utenti registrati nel sistema.
     */
    List<Utente> getUtenti();
    /**
     * Recupera un singolo utente tramite il suo ID.
     */
    Utente getUtenteById(Long id);
    /**
     * Aggiunge un nuovo utente al sistema o aggiorna uno esistente.
     */

    Utente addUtente(Utente u);
/**
     * Verifica se un utente con una determinata email esiste già nel sistema.
     */
    boolean existsByEmail(String email);
/**
     * Verifica se un utente con un determinato username esiste già nel sistema.
     */
    boolean existsByUsername(String username);
}