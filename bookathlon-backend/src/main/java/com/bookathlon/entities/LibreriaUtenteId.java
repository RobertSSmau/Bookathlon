package com.bookathlon.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
/**
 * Classe che rappresenta la chiave composta per l'entità LibreriaUtente.
 * Questa classe è necessaria a JPA per identificare univocamente le righe
 * nella tabella 'libreria_utente', che ha una chiave primaria composta
 * dagli ID di utente e libro.
 * Deve implementare Serializable e sovrascrivere i metodi equals() e hashCode().
 */
public class LibreriaUtenteId implements Serializable {

    private Utente utente;
	 // Rappresenta la parte utente della chiave.
    private Libro libro;  
	 // Rappresenta la parte libro della chiave.

    /**
     * Costruttore di default.
     */
    public LibreriaUtenteId() {}

    // Getters e Setters 
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
     * Implementazione del metodo equals per confrontare due oggetti LibreriaUtenteId.
     * È fondamentale per il corretto funzionamento delle chiavi composte in JPA,
     * garantendo che due oggetti siano considerati uguali se i loro componenti
     * (utente e libro) sono uguali.
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
     * Implementazione del metodo hashCode per generare un codice hash per l'oggetto.
     * È essenziale che hashCode sia coerente con equals: se due oggetti sono uguali
     * secondo equals(), devono avere lo stesso valore di hashCode().
     * Questo è cruciale per il corretto funzionamento di collezioni basate su hash (es. HashMap, HashSet).
     */
    @Override
    public int hashCode() {
        return Objects.hash(utente, libro); // Genera un hash basato su utente e libro.
    }
}












