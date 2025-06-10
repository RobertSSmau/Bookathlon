package com.bookathlon.repos;

import java.awt.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookathlon.entities.Amicizia;
import com.bookathlon.entities.AmiciziaId;

@Repository
public interface AmiciziaRepo extends JpaRepository<Amicizia, AmiciziaId>{

	// fare le select in base a questi contesti
	
			// gli amici che sono amici
	 		@Query(value = """
		      SELECT * FROM "better-mockup-schema".amicizia
        WHERE stato = 'ACCEPTED'
          AND (id_utente1 = :userId OR id_utente2 = :userId)
		        """, nativeQuery = true)
		    List<Amicizia> trovaAmiciAccettati(@Param("userId") Long userId);

	 		// le richieste di amicizia ricevute
		    @Query(value = """
				SELECT * FROM "better-mockup-schema".amicizia
        WHERE stato = 'PENDING'
          AND id_utente2 = :userId
		        """, nativeQuery = true)
		    List<Amicizia> trovaRichiesteRicevute(@Param("userId") Long userId);

		    // le richieste che sono state inviate
		    @Query(value = """
				SELECT * FROM "better-mockup-schema".amicizia
        WHERE stato = 'PENDING'
          AND id_utente1 = :userId
		        """, nativeQuery = true)
		    List<Amicizia> trovaRichiesteInviate(@Param("userId") Long userId);

}
