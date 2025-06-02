package com.bookathlon.entities;

import java.io.Serializable;

//per gestire chiave composta, mi serve a JPA per sapere come identificare una riga univoca nella tabella libreria_utente


public class LibreriaUtenteId implements Serializable{

	private Long utente;
	private Long libro;
	
	public LibreriaUtenteId() {}

	public Long getUtente() {
		return utente;
	}

	public void setUtente(Long utente) {
		this.utente = utente;
	}

	public Long getLibro() {
		return libro;
	}

	public void setLibro(Long libro) {
		this.libro = libro;
	}
	
	
	
}
