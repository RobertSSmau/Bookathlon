package com.bookathlon.controller;

import java.util.List;

// Importazioni delle annotazioni Spring per costruire il controller REST
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookathlon.entities.Utente;
import com.bookathlon.service.UtenteService;

// Indica che questa classe è un controller REST,restituisce dati
@RestController
public class UtenteREST {

    @Autowired
    private UtenteService service;

// Metodo che gestisce le richieste GET all'endpoint "api/utenti"
    @GetMapping("api/utenti")
    public List<Utente> getUtenti() {
        return service.getUtenti();  // restituisce la lista di tutti gli utenti
    }

// Metodo che gestisce le richieste POST all'endpoint "api/utenti"
    @PostMapping("api/utenti")
    public Utente addUtente(@RequestBody Utente u) {
        return service.addUtente(u);  // Richiede al service di salvare il nuovo utente ricevuto nel corpo della richiesta
    }

// Gestisce le richieste GET con parametro dinamico {id}
// Richiede al service di cercare l'utente con l'ID specificato
    @GetMapping("api/utenti/{id}")
    public Utente getUtenteById(@PathVariable Long userId) {
        return service.getUtenteById(userId);  
    }
}
