package com.bookathlon.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "challenge", schema = "better-mockup-schema-2")
public class Challenge {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libro_id", nullable = false)
    private Long libroId;

    @Column(name = "autore_id", nullable = false)
    private Long autoreId;

    //soluzione sporca , reso il field detinatario_id nullabile temporaneamente
    @Column(name = "destinatario_id", nullable = true)
    private Long destinatarioId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String domanda;

    // Solo per tipo QUIZ
    @Column(name = "opzione_a")
    private String opzioneA;

    @Column(name = "opzione_b")
    private String opzioneB;

    @Column(name = "opzione_c")
    private String opzioneC;
    
    @Column(name = "opzione_d")
    private String opzioneD;

    @Column(name = "risposta_corretta")
    private String rispostaCorretta; 


    private String stato; // PENDING, RISPOSTA_INVIATA, COMPLETATA

    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione = LocalDateTime.now();
    
    
    // autore_id e destinatario_id sono fkey verso utente e non scrive su DB
    @ManyToOne
    @JoinColumn(name = "destinatario_id", insertable = false, updatable = false)
    private Utente destinatario;

    @ManyToOne
    @JoinColumn(name = "autore_id", insertable = false, updatable = false)
    private Utente autore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLibroId() {
		return libroId;
	}

	public void setLibroId(Long libroId) {
		this.libroId = libroId;
	}

	public Long getAutoreId() {
		return autoreId;
	}

	public void setAutoreId(Long autoreId) {
		this.autoreId = autoreId;
	}

	public Long getDestinatarioId() {
		return destinatarioId;
	}

	public void setDestinatarioId(Long destinatarioId) {
		this.destinatarioId = destinatarioId;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public String getOpzioneA() {
		return opzioneA;
	}

	public void setOpzioneA(String opzioneA) {
		this.opzioneA = opzioneA;
	}

	public String getOpzioneB() {
		return opzioneB;
	}

	public void setOpzioneB(String opzioneB) {
		this.opzioneB = opzioneB;
	}

	public String getOpzioneC() {
		return opzioneC;
	}

	public void setOpzioneC(String opzioneC) {
		this.opzioneC = opzioneC;
	}

	public String getRispostaCorretta() {
		return rispostaCorretta;
	}

	public void setRispostaCorretta(String rispostaCorretta) {
		this.rispostaCorretta = rispostaCorretta;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Utente getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Utente destinatario) {
		this.destinatario = destinatario;
	}

	public Utente getAutore() {
		return autore;
	}

	public void setAutore(Utente autore) {
		this.autore = autore;
	}

	public String getOpzioneD() {
		return opzioneD;
	}

	public void setOpzioneD(String opzioneD) {
		this.opzioneD = opzioneD;
	}



    
	
	
}
