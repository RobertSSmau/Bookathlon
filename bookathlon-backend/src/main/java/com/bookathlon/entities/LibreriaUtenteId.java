package com.bookathlon.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
/**
 * Questa classe rappresenta la chiave composta per l'entità LibreriaUtente, necessaria a JPA per identificare univocamente le righe tramite gli ID di utente e libro.
  * Deve implementare Serializable, equals(), e hashCode().
 */
public class LibreriaUtenteId implements Serializable {

    private Utente utente;
	
    private Libro libro;  
	 

    /**
     * Costruttore di default.
     */
    public LibreriaUtenteId() {}

    
    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Il metodo equals confronta due LibreriaUtenteId per il corretto funzionamento delle chiavi composte in JPA. 
     * Garantisce che gli oggetti siano considerati uguali se i loro componenti (utente e libro) lo sono.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LibreriaUtenteId other = (LibreriaUtenteId) o;
        // Confronta gli attributi utente e libro per determinare l'uguaglianza.
        return Objects.equals(utente, other.utente) &&
               Objects.equals(libro, other.libro);
    }

    /**
     * Il metodo hashCode genera un codice hash, garantendo coerenza con equals(): oggetti uguali devono avere lo stesso hash code. 
     * Questo è cruciale per il funzionamento delle collezioni basate su hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(utente, libro); // Genera un hash basato su utente e libro.
    }
}












