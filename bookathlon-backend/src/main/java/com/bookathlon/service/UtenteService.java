package com.bookathlon.service;

import com.bookathlon.entities.Utente;

public interface UtenteService {
    
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
    
    Utente getByUsername(String username);
    
    Utente getById(Long id);
    
    void incrementaScore(Long id);
}