package com.bookathlon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookathlon.entities.Utente;
import com.bookathlon.service.UtenteService;

@RestController
public class UtenteREST {

    @Autowired
    private UtenteService service;

    @GetMapping("api/utenti")
    public List<Utente> getUtenti() {
        return service.getUtenti();
    }

    @PostMapping("api/utenti")
    public Utente addUtente(@RequestBody Utente u) {
        return service.addUtente(u);
    }

    @GetMapping("api/utenti/{id}")
    public Utente getUtenteById(@PathVariable Long id) {
        return service.getUtenteById(id);
    }
}
