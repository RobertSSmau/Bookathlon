package com.bookathlon.service;

import java.util.List;
import com.bookathlon.entities.Utente;

public interface UtenteService {
    List<Utente> getUtenti();
    Utente getUtenteById(Long id);
    Utente addUtente(Utente u);
}