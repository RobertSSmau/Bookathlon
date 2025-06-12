package com.bookathlon.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

/**
 * Rappresenta l'entità 'Amicizia' nel database.
 * Questa classe gestisce le relazioni di amicizia tra due utenti,
 * utilizzando una chiave composta definita da {@link AmiciziaId}.
 */
@Entity 
// Indica che questa classe è un'entità JPA.
@Table(name="amicizia")
 // Specifica il nome della tabella nel database.
@IdClass(AmiciziaId.class)
 // Definisce la classe che rappresenta la chiave primaria composta.
public class Amicizia {

    @Id 
	// Indica che questa colonna fa parte della chiave primaria.
    @Column(name = "id_utente1")
	 // Specifica il nome della colonna nel database.
    private Long utente1;
	 // ID del primo utente coinvolto nell'amicizia.

    @Id
    @Column(name = "id_utente2") 
    private Long utente2; 

    @Column(nullable = false) 
    private String stato;
	 // Stato dell'amicizia: PENDING, ACCEPTED, DECLINED.

    @Column(name = "data_richiesta")
	 // Specifica il nome della colonna nel database.
    private LocalDateTime dataRichiesta = LocalDateTime.now(); 
	// Data e ora della richiesta di amicizia, inizializzata automaticamente.

    /**
     * Costruttore di default.
     */
    public Amicizia() {}

    // Getters e Setters 
    public Long getUtente1() {
        return utente1;
    }

    public void setUtente1(Long utente1) {
        this.utente1 = utente1;
    }

    public Long getUtente2() {
        return utente2;
    }

    public void setUtente2(Long utente2) {
        this.utente2 = utente2;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public LocalDateTime getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDateTime dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }
}






