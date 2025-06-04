package com.bookathlon.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * Rappresenta un'entità Libro da salvare nel database
 * Ogni libro ha un ID, un codice ISBN, un titolo, un autore e un anno di pubblicazione
 */
@Entity // indica che la classe è una tabella del database
@Table(name="libro")// Mappa la classe alla tabella "libro" nel database

public class Libro {
	
	@Id
	private int id; // Identificativo univoco del libro (chiave primaria)
	private String isbn; // codice ISBN del libro
	private String titolo; // Titolo del libro
	private String autore; // Nome dell'autore del libro
	private int anno_pubblicazione; // Anno in cui il libro è stato pubblicato

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public int getAnno_pubblicazione() {
		return anno_pubblicazione;
	}
	public void setAnno_pubblicazione(int anno_pubblicazione) {
		this.anno_pubblicazione = anno_pubblicazione;
	}
	
}
