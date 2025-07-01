package com.bookathlon.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookathlon.entities.LikeCommento;
import com.bookathlon.entities.LikeCommentoId;

import jakarta.transaction.Transactional;

public interface LikeCommentoRepository extends JpaRepository<LikeCommento, LikeCommentoId> {

	@Query(value = """
	        SELECT COUNT(*) 
	        FROM "better-mockup-schema-2".like_commento
	        WHERE commento_id = :commentoId
	    """, nativeQuery = true)
	    long contaLike(@Param("commentoId") Long commentoId);

	    @Query(value = """
	        SELECT CASE 
	                 WHEN COUNT(*) > 0 THEN TRUE 
	                 ELSE FALSE 
	               END
	        FROM "better-mockup-schema-2".like_commento
	        WHERE commento_id = :commentoId AND utente_id = :utenteId
	    """, nativeQuery = true)
	    boolean haGiaMessoLike(
	        @Param("commentoId") Long commentoId,
	        @Param("utenteId") Long utenteId
	    );
	    
	    @Modifying
	    @Transactional
	    @Query(value = """
	        DELETE FROM "better-mockup-schema-2".like_commento
	        WHERE commento_id = :commentoId AND utente_id = :utenteId
	    """, nativeQuery = true)
	    void rimuoviLike(
	        @Param("commentoId") Long commentoId,
	        @Param("utenteId") Long utenteId);
	
}
