package com.bookathlon.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    @Column(nullable = false)
    private String tipo; // "APERTO" o "QUIZ"

    @Column(nullable = false, columnDefinition = "TEXT")
    private String domanda;

    // Solo per tipo QUIZ
    @Column(name = "opzione_a")
    private String opzioneA;

    @Column(name = "opzione_b")
    private String opzioneB;

    @Column(name = "opzione_c")
    private String opzioneC;

    @Column(name = "risposta_corretta")
    private String rispostaCorretta; // "A", "B", "C"

    // Solo per tipo APERTO
    private Boolean approvata;

    private String stato; // PENDING, RISPOSTA_INVIATA, COMPLETATA

    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione = LocalDateTime.now();

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Boolean getApprovata() {
		return approvata;
	}

	public void setApprovata(Boolean approvata) {
		this.approvata = approvata;
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

    
	
	
}
