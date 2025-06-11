package com.bookathlon.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="amicizia")
@IdClass(AmiciziaId.class)
public class Amicizia {

	 @Id
	    @Column(name = "id_utente1")
	    private Long utente1;

	    @Id
	    @Column(name = "id_utente2")
	    private Long utente2;

	    @Column(nullable = false)
	    private String stato; // PENDING, ACCEPTED, DECLINED

	    @Column(name = "data_richiesta")
	    private LocalDateTime dataRichiesta = LocalDateTime.now();

	    public Amicizia() {}

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
