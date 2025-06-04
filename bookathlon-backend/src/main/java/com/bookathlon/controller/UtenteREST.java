package com.bookathlon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookathlon.entities.Utente;
import com.bookathlon.service.UtenteService;

/**
 * RestController per la gestione delle operazioni sugli utenti
 * Espone endpoint per ottenere tutti gli utenti e aggiungerne uno nuovo
 * e recupera un utente specifico tramite ID
 */
@RestController
public class UtenteREST {

    //Spring inietta automaticamente un'istanza del servizio UtenteService
    @Autowired
    private UtenteService service;

    /**
     * Endpoint GET per ottenere la lista di tutti gli utenti
     * @return una lista di oggetti {@link Utente}
     */
    @GetMapping("api/utenti")
    public List<Utente> getUtenti() {
        //restituisce tutti gli utenti
        return service.getUtenti();
    }

    /**
     * Endpoint POST per creare un nuovo utente
     * @param u oggetto {@link Utente} ricevuto nel corpo della richiesta
     * @return l'utente salvato
     */
    @PostMapping("api/utenti")
    public Utente addUtente(@RequestBody Utente u) {
        //salva il nuovo utente
        return service.addUtente(u);
    }

    /**
     * Endpoint GET per ottenere un utente tramite ID
     * @param id identificato univoco dell'utente
     * @return l'oggetto {@link Utente} corrispondente all'ID
     */
    @GetMapping("api/utenti/{id}")
    public Utente getUtenteById(@PathVariable Long id) {
        //Restituisce l'utente con ID specificato
        return service.getUtenteById(id);
    }
}
