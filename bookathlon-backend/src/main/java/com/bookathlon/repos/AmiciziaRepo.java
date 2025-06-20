 

package com.bookathlon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookathlon.entities.Amicizia;
import com.bookathlon.entities.AmiciziaId;

/**
 *Questa repository per l'entità Amicizia estende JpaRepository per operazioni CRUD.
  Definisce inoltre query personalizzate per la gestione delle relazioni di amicizia.
 */
@Repository 
public interface AmiciziaRepo extends JpaRepository<Amicizia, AmiciziaId> {

    /**
     *Questo metodo trova tutte le relazioni di amicizia con stato 'ACCEPTED' in cui l'utente fornito è coinvolto come richiedente o ricevente.
     */
    @Query(value = """
            SELECT * FROM "better-mockup-schema-2".amicizia
        WHERE stato = 'ACCEPTED'
          AND (id_utente1 = :userId OR id_utente2 = :userId)
                """, nativeQuery = true)
    List<Amicizia> trovaAmiciAccettati(@Param("userId") Long userId); // @Param collega il parametro del metodo al placeholder nella query.

    /**
     * Questo metodo recupera tutte le richieste di amicizia con stato 'PENDING' ricevute dall'utente identificato da id_utente2.
     */
    @Query(value = """
                SELECT * FROM "better-mockup-schema-2".amicizia
        WHERE stato = 'PENDING'
          AND id_utente2 = :userId
                """, nativeQuery = true)
    List<Amicizia> trovaRichiesteRicevute(@Param("userId") Long userId);

    /**
     * Questo metodo trova tutte le richieste di amicizia con stato 'PENDING' che sono state inviate dall'utente specificato (id_utente1).
     */
    @Query(value = """
                SELECT * FROM "better-mockup-schema-2".amicizia
        WHERE stato = 'PENDING'
          AND id_utente1 = :userId
                """, nativeQuery = true)
    List<Amicizia> trovaRichiesteInviate(@Param("userId") Long userId);
    
    

}






