 

package com.bookathlon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookathlon.entities.Amicizia;
import com.bookathlon.entities.AmiciziaId;
import com.bookathlon.entities.Utente;

/**
 * Repository per l'entità Amicizia.
 * Estende JpaRepository per fornire operazioni CRUD di base
 * e definisce query personalizzate per la gestione delle relazioni di amicizia.
 */
@Repository 
public interface AmiciziaRepo extends JpaRepository<Amicizia, AmiciziaId> {

    /**
     * Trova tutte le relazioni di amicizia con stato 'ACCEPTED'
     * in cui l'utente specificato è uno dei due partecipanti.
     */
    @Query(value = """
            SELECT * FROM "better-mockup-schema".amicizia
        WHERE stato = 'ACCEPTED'
          AND (id_utente1 = :userId OR id_utente2 = :userId)
                """, nativeQuery = true) // nativeQuery = true indica che la query è SQL nativo.
    List<Amicizia> trovaAmiciAccettati(@Param("userId") Long userId); // @Param collega il parametro del metodo al placeholder nella query.

    /**
     * Trova tutte le richieste di amicizia con stato 'PENDING'
     * che sono state ricevute dall'utente specificato (id_utente2).
     */
    @Query(value = """
                SELECT * FROM "better-mockup-schema".amicizia
        WHERE stato = 'PENDING'
          AND id_utente2 = :userId
                """, nativeQuery = true)
    List<Amicizia> trovaRichiesteRicevute(@Param("userId") Long userId);

    /**
     * Trova tutte le richieste di amicizia con stato 'PENDING'
     * che sono state inviate dall'utente specificato (id_utente1).
     */
    @Query(value = """
                SELECT * FROM "better-mockup-schema".amicizia
        WHERE stato = 'PENDING'
          AND id_utente1 = :userId
                """, nativeQuery = true)
    List<Amicizia> trovaRichiesteInviate(@Param("userId") Long userId);
    
    @Query()
    	List<Utente> cercaPerUsername(@Param("username") String username);

}






