package com.bookathlon.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge_risposta", schema = "better-mockup-schema-2")
public class ChallengeRisp {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "challenge_id", nullable = false)
	    private Long challengeId;

	    @Column(nullable = false, columnDefinition = "TEXT")
	    private String risposta;

	    @Column(name = "data_risposta")
	    private LocalDateTime dataRisposta = LocalDateTime.now();

	    private Boolean valutata = false;

	    private Boolean approvata;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getChallengeId() {
			return challengeId;
		}

		public void setChallengeId(Long challengeId) {
			this.challengeId = challengeId;
		}

		public String getRisposta() {
			return risposta;
		}

		public void setRisposta(String risposta) {
			this.risposta = risposta;
		}

		public LocalDateTime getDataRisposta() {
			return dataRisposta;
		}

		public void setDataRisposta(LocalDateTime dataRisposta) {
			this.dataRisposta = dataRisposta;
		}

		public Boolean getValutata() {
			return valutata;
		}

		public void setValutata(Boolean valutata) {
			this.valutata = valutata;
		}

		public Boolean getApprovata() {
			return approvata;
		}

		public void setApprovata(Boolean approvata) {
			this.approvata = approvata;
		}
	    
	    
	
}
