package com.bookathlon.entities;

// Importa le annotazioni JPA che permettono di collegare la classe Java a una tabella del database.
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity   // Indica a Spring che questa classe rappresenta una tabella del database
@Table(name="libro")  // Specifica che è collegata alla tabella 'libro' nel database

public class Libro {
	
// Ogni attributo è mappato su una colonna della tabella libro.Spring e JPA si occuperà automaticamente della conversione
	@Id // Identifica il campo 'id' come chiave primaria della tabella
	private int id;
	private String isbn;
	private String titolo;
	private String autore;
	private int anno_pubblicazione;

// I metodi getter e setter servono per accedere e modificare i valori degli attributi.
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
