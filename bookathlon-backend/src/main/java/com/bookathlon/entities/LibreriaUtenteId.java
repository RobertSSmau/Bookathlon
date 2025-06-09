package com.bookathlon.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

//per gestire chiave composta, mi serve a JPA per sapere come identificare una riga univoca nella tabella libreria_utente


public class LibreriaUtenteId implements Serializable{

	   private Utente utente;
	    private Libro libro;

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

	@Override
	public boolean equals(Object o) {
	    if (this == o) {
	        return true;
	    }
	    if (o == null) {
	        return false;
	    }
	    if (getClass() != o.getClass()) {
	        return false;
	    }
	    LibreriaUtenteId other = (LibreriaUtenteId) o;
	    if (utente == null) {
	        if (other.utente != null) {
	            return false;
	        }
	    } else if (!utente.equals(other.utente)) {
	        return false;
	    }
	    if (libro == null) {
	        if (other.libro != null) {
	            return false;
	        }
	    } else if (!libro.equals(other.libro)) {
	        return false;
	    }
	    return true;
	}


	@Override
	public int hashCode() {
	    return Objects.hash(utente, libro);
	}
	
	
}
