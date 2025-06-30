--REPORT DB--

-- TABELLA: utente
-- Questa tabella serve per tenere traccia di tutti gli utenti che useranno la tua applicazione.
CREATE TABLE utente (
    id SERIAL, 
    username VARCHAR(50) NOT NULL, 
    password VARCHAR(255) NOT NULL, 
    ruolo VARCHAR(10) NOT NULL, 
    score INTEGER DEFAULT 0,
    CONSTRAINT pk_utente PRIMARY KEY (id), 
    CONSTRAINT uq_username UNIQUE (username),
    CONSTRAINT chk_ruolo CHECK (ruolo IN ('USER', 'ADMIN')), 
    CONSTRAINT chk_score_non_negativo CHECK (score >= 0) 
);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELLA: libro
-- Questa tabella serve per registrare tutti i libri disponibili nell'applicazione.
CREATE TABLE libro (
    id SERIAL, 
    isbn VARCHAR(20), 
    titolo VARCHAR(255) NOT NULL, 
    autore VARCHAR(100),
    genere VARCHAR(50), 
    data_pubblicazione DATE, 
    CONSTRAINT pk_libro PRIMARY KEY (id), 
    CONSTRAINT uq_isbn UNIQUE (isbn) 
);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELLA: libreria_utente
-- Questa tabella serve per collegare gli utenti ai libri che possiedono o vogliono leggere.
CREATE TABLE libreria_utente (
    utente_id INTEGER, 
    libro_id INTEGER, 
    stato VARCHAR(20) NOT NULL, 
    data_aggiunta DATE DEFAULT CURRENT_DATE, 
    CONSTRAINT pk_libreria PRIMARY KEY (utente_id, libro_id), 
    CONSTRAINT fk_libreria_utente FOREIGN KEY (utente_id) REFERENCES utente(id), 
    CONSTRAINT fk_libreria_libro FOREIGN KEY (libro_id) REFERENCES libro(id), 
    CONSTRAINT chk_stato CHECK (stato IN ('LETTO', 'DA_LEGGERE'))
);


-- MODIFICHE ALLA TABELLA 'libro'
-- Aggiungiamo una nuova colonna e un controllo alla tabella 'libro'.
ALTER TABLE libro
ADD COLUMN url_copertina TEXT, 
ADD CONSTRAINT chk_url_copertina CHECK ( 
    url_copertina IS NULL OR url_copertina ~ '^https?://'
);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELLA: amicizia
-- Questa tabella serve per gestire le richieste e le accettazioni di amicizia tra gli utenti.
CREATE TABLE amicizia (
    id SERIAL PRIMARY KEY, 
    id_utente1 BIGINT NOT NULL, 
    id_utente2 BIGINT NOT NULL, 
    stato VARCHAR(20) NOT NULL CHECK (stato IN ('PENDING', 'ACCEPTED', 'DECLINED')), 
    data_richiesta TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    CONSTRAINT fk_utente1 FOREIGN KEY (id_utente1) REFERENCES utente(id), 
    CONSTRAINT fk_utente2 FOREIGN KEY (id_utente2) REFERENCES utente(id), 
    CONSTRAINT amicizia_unica UNIQUE (id_utente1, id_utente2) 
);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- TABELLA: challenge
-- Questa tabella rappresenta una sfida tra due utenti, basata su un libro.
-- Contiene la domanda, le 4 opzioni di risposta e la risposta corretta.
CREATE TABLE "better-mockup-schema-2".challenge (
	id serial4 NOT NULL,
	libro_id int8 NOT NULL,
	autore_id int8 NOT NULL,
	destinatario_id int8 NULL,
	domanda text NOT NULL,
	opzione_a text NOT NULL,
	opzione_b text NOT NULL,
	opzione_c text NOT NULL,
	opzione_d text NOT NULL,
	risposta_corretta varchar(1) NOT NULL,
	stato varchar(30) NULL,
	data_creazione timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT challenge_pkey PRIMARY KEY (id)
);


-- "better-mockup-schema-2".challenge foreign keys

ALTER TABLE "better-mockup-schema-2".challenge ADD CONSTRAINT fk_challenge_autore FOREIGN KEY (autore_id) REFERENCES "better-mockup-schema-2".utente(id) ON DELETE CASCADE;
ALTER TABLE "better-mockup-schema-2".challenge ADD CONSTRAINT fk_challenge_destinatario FOREIGN KEY (destinatario_id) REFERENCES "better-mockup-schema-2".utente(id) ON DELETE CASCADE;
ALTER TABLE "better-mockup-schema-2".challenge ADD CONSTRAINT fk_challenge_libro FOREIGN KEY (libro_id) REFERENCES "better-mockup-schema-2".libro(id) ON DELETE CASCADE;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- TABELLA: commento
-- Questa tabella raccoglie i commenti degli utenti relativi ai libri.
CREATE TABLE "better-mockup-schema-2".commento (
	id serial4 NOT NULL,
	utente_id int4 NOT NULL,
	libro_id int4 NOT NULL,
	contenuto text NOT NULL,
	data_creazione timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT const_commento_pk PRIMARY KEY (id)
);


-- "better-mockup-schema-2".commento foreign keys

ALTER TABLE "better-mockup-schema-2".commento ADD CONSTRAINT const_commento_fk1 FOREIGN KEY (utente_id) REFERENCES "better-mockup-schema-2".utente(id) ON DELETE CASCADE;
ALTER TABLE "better-mockup-schema-2".commento ADD CONSTRAINT const_commento_fk2 FOREIGN KEY (libro_id) REFERENCES "better-mockup-schema-2".libro(id) ON DELETE CASCADE;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- TABELLA: like_commento
-- Questa tabella rappresenta i "mi piace" che gli utenti mettono ai commenti.
CREATE TABLE "better-mockup-schema-2".like_commento (
	commento_id int4 NOT NULL,
	utente_id int4 NOT NULL,
	CONSTRAINT const_like_commento_pk PRIMARY KEY (commento_id, utente_id)
);

-- "better-mockup-schema-2".like_commento foreign keys

ALTER TABLE "better-mockup-schema-2".like_commento ADD CONSTRAINT const_like_commento_fk1 FOREIGN KEY (commento_id) REFERENCES "better-mockup-schema-2".commento(id) ON DELETE CASCADE;
ALTER TABLE "better-mockup-schema-2".like_commento ADD CONSTRAINT const_like_commento_fk2 FOREIGN KEY (utente_id) REFERENCES "better-mockup-schema-2".utente(id) ON DELETE CASCADE;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- TABELLA: challenge_risposta
-- Questa tabella registra le risposte date dagli utenti alle sfide.
CREATE TABLE "better-mockup-schema-2".challenge_risposta (
	id serial4 NOT NULL,
	challenge_id int8 NOT NULL,
	utente_id int8 NOT NULL,
	risposta text NOT NULL,
	data_risposta timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	corretta bool NULL,
	CONSTRAINT challenge_risposta_pkey PRIMARY KEY (id)
);


-- "better-mockup-schema-2".challenge_risposta foreign keys

ALTER TABLE "better-mockup-schema-2".challenge_risposta ADD CONSTRAINT fk_risposta_challenge FOREIGN KEY (challenge_id) REFERENCES "better-mockup-schema-2".challenge(id) ON DELETE CASCADE;
ALTER TABLE "better-mockup-schema-2".challenge_risposta ADD CONSTRAINT fk_risposta_utente FOREIGN KEY (utente_id) REFERENCES "better-mockup-schema-2".utente(id) ON DELETE CASCADE;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--QUERY PER TEST DEL DB--

--Vedere i Migliori Punteggi degli Utenti--
SELECT id, username, score
FROM utente
WHERE score >= 5
ORDER BY score DESC;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--Cercare i Libri Letti da un Utente Specifico
SELECT l.titolo, l.autore
FROM libreria_utente lu, libro l
WHERE lu.stato = 'LETTO'
  AND lu.libro_id = l.id
  AND lu.utente_id = 1;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--Verificare se Due Utenti Sono Amici Accettati
SELECT *
FROM amicizia
WHERE ((id_utente1 = 1 AND id_utente2 = 2) OR (id_utente1 = 2 AND id_utente2 = 1))
  AND stato = 'ACCEPTED';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--Vedere i Commenti di un Libro Specifico
SELECT c.contenuto, u.username, c.data_creazione
FROM "better-mockup-schema-2".commento c, utente u
WHERE c.utente_id = u.id
  AND c.libro_id = 1;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--Contare i "Mi Piace" per i Commenti di un Libro
SELECT c.id, COUNT(lc.utente_id) AS numero_like
FROM "better-mockup-schema-2".commento c
LEFT JOIN "better-mockup-schema-2".like_commento lc ON c.id = lc.commento_id
WHERE c.libro_id = 1
GROUP BY c.id;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--Vedere le Challenge Assegnate a un Utente
SELECT ch.id, l.titolo, ch.domanda, ch.stato
FROM "better-mockup-schema-2".challenge ch, libro l
WHERE ch.destinatario_id = 1
  AND ch.libro_id = l.id;
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--Trovare le Risposte Corrette alle Challenge
SELECT cr.*
FROM "better-mockup-schema-2".challenge_risposta cr
WHERE cr.corretta = true;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--Classifica dei Libri Più Letti
SELECT l.titolo, COUNT(*) AS numero_lettori
FROM libreria_utente lu, libro l
WHERE lu.stato = 'LETTO'
  AND lu.libro_id = l.id
GROUP BY l.titolo
ORDER BY numero_lettori DESC;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--Vedere gli Utenti che Hanno Proposto Challenge
SELECT DISTINCT u.id, u.username
FROM utente u, "better-mockup-schema-2".challenge ch
WHERE u.id = ch.autore_id;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 --QUERY IN AMICIZIA REPO--

--Recupera tutte le amicizie accettate per un utente (sia come mittente che come destinatario)--
SELECT * FROM "better-mockup-schema-2".amicizia
        WHERE stato = 'ACCEPTED'
          AND (id_utente1 = :userId OR id_utente2 = :userId)

--Mostra le richieste di amicizia in attesa ricevute dall'utente--
SELECT * FROM "better-mockup-schema-2".amicizia
        WHERE stato = 'PENDING'
          AND id_utente2 = :userId

--Mostra le richieste di amicizia in attesa inviate dall'utente--
SELECT * FROM "better-mockup-schema-2".amicizia
        WHERE stato = 'PENDING'
          AND id_utente1 = :userId

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--QUERY IN CHALLENGE REPO--

--Recupera le challenge ricevute da un utente, ordinate per data (più recenti prima)--
SELECT * 
            FROM "better-mockup-schema-2".challenge 
            WHERE "destinatario_id" = :utenteId 
            ORDER BY "data_creazione" desc;


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--QUERY IN COMMENTO REPO--

--Restituisce tutti i commenti associati a un libro, ordinati dal più recente--
 SELECT * FROM "better-mockup-schema-2".commento
	        WHERE libro_id = :libroId
	        ORDER BY data_creazione DESC



------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--QUERY IN LIBRO REPO--
	
--Ricerca libri per titolo o autore--
SELECT *
		FROM "better-mockup-schema-2".libro
		WHERE titolo ILIKE CONCAT('%', :kw, '%')
		OR autore ILIKE CONCAT('%', :kw, '%');

--Top 10 libri più presenti nelle librerie degli utenti--
SELECT l.* 
		FROM "better-mockup-schema-2".libreria_utente lu
		JOIN "better-mockup-schema-2".libro l ON lu.libro_id = l.id
		GROUP BY l.id, l.isbn, l.titolo, l.autore, l.genere, l.data_pubblicazione, l.url_copertina
		ORDER BY COUNT(*) DESC
		LIMIT 10;

		

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--QUERY IN LIKE COMMENTO REPO--

--Conta il numero di like per un commento--
SELECT COUNT(*) 
	        FROM "better-mockup-schema-2".like_commento
	        WHERE commento_id = :commentoId;

--Verifica se un utente ha già messo like a un commento--
SELECT CASE 
	      WHEN COUNT(*) > 0 THEN TRUE 
	      ELSE FALSE 
	         END
	      FROM "better-mockup-schema-2".like_commento
	      WHERE commento_id = :commentoId AND utente_id = :utenteId;

--Rimuove il like di un utente a un commento--
DELETE FROM "better-mockup-schema-2".like_commento
	        WHERE commento_id = :commentoId AND utente_id = :utenteId;


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--QUERY IN UTENTE REPO--

--Cerca utenti per username--
SELECT * 
	FROM utente 
	WHERE username ILIKE CONCAT('%', :username, '%');

--Mostra i 20 utenti con punteggio più alto--
SELECT * 
	FROM utente 
	ORDER BY score 
	DESC LIMIT 20;

--Mostra i top 20 utenti (tra un elenco specifico di ID) ordinati per punteggio
SELECT * 
	FROM utente
	WHERE id IN (:ids) 
	ORDER BY score
	DESC LIMIT 20;

--Incrementa lo score di un utente--
UPDATE utente 
	SET score = score + 1 
	WHERE id = :userId;


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Cancellare Richieste di Amicizia Non Accettate e Vecchie
DELETE FROM amicizia
WHERE stato = 'DECLINED'
  AND data_richiesta < CURRENT_DATE - INTERVAL '30 days';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------







