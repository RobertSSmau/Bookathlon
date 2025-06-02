package com.bookathlon.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libreria_utente")
@IdClass(LibreriaUtenteId.class)
public class LibreriaUtente {

	@Id
	@ManyToOne
	@JoinColumn(name= "utente_id")
	private Utente utente;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
	
	@Column(nullable = false)
    private String stato;
	
	@Column(name = "data_aggiunta")
    private LocalDate dataAggiunta = LocalDate.now();

	public LibreriaUtente() {}
	
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

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public LocalDate getDataAggiunta() {
		return dataAggiunta;
	}

	public void setDataAggiunta(LocalDate dataAggiunta) {
		this.dataAggiunta = dataAggiunta;
	}
	
	
	
}
