package com.bookathlon.service;

import java.util.List;
import com.bookathlon.entities.Utente;

public interface UtenteService {
    List<Utente> getUtenti();
    Utente getUtenteById(Long userId);
    Utente addUtente(Utente u);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}