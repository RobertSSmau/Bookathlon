package com.bookathlon.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.LibreriaUtente;
import com.bookathlon.entities.LibreriaUtenteId;
import com.bookathlon.entities.Libro;
import com.bookathlon.entities.Utente;
import com.bookathlon.repos.LibreriaUtenteRepository;
import com.bookathlon.repos.LibroRepository;
import com.bookathlon.repos.UtenteRepository;

/**
 * Questa classe implementa LibreriaUtenteService e gestisce la logica di business per la libreria personale dell'utente. 
 * Permette di visualizzare, aggiungere e rimuovere libri, gestendone gli stati.
 */
@Service
public class LibreriaUtenteServiceImpl implements LibreriaUtenteService {

    @Autowired
    private LibreriaUtenteRepository repo;

    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private LibroRepository libroRepo;

    @Override
    public List<LibreriaUtente> getLibreriaUtente(Long utenteId) {
        return repo.findByUtenteId(utenteId);
    }

    @Override
    public List<LibreriaUtente> getLibriByStato(Long utenteId, String stato) {
        return repo.findByUtenteIdAndStato(utenteId, stato);
    }

    /**
     * Questo metodo aggiunge un libro alla libreria di un utente, creando e salvando una nuova entry LibreriaUtente dopo aver recuperato gli oggetti Utente e Libro corrispondenti.
     */
    @Override
    public LibreriaUtente aggiungiLibro(Long utenteId, Long libroId, String stato) {
        Utente utente = utenteRepo.findById(utenteId).orElseThrow();
        Libro libro = libroRepo.findById(libroId).orElseThrow();

        LibreriaUtente entry = new LibreriaUtente();
        entry.setUtente(utente);
        entry.setLibro(libro);
        entry.setStato(stato);
        entry.setDataAggiunta(LocalDate.now());

        if (stato.equals("DA_LEGGERE")) {
            entry.setDataInizioLettura(LocalDate.now());
            entry.setDataFineLettura(null);
        }

        if (stato.equals("LETTO")) {
            if (entry.getDataInizioLettura() == null) {
                entry.setDataInizioLettura(LocalDate.now());
            }

            if (entry.getDataFineLettura() == null) {
                entry.setDataFineLettura(LocalDate.now());

                utenteRepo.incrementaScore(utenteId);
            }
        }

        return repo.save(entry);
    }

    @Override
    public void rimuoviLibro(Long utenteId, Long libroId) {
        Utente utente = utenteRepo.findById(utenteId).orElseThrow();
        Libro libro = libroRepo.findById(libroId).orElseThrow();

        LibreriaUtenteId id = new LibreriaUtenteId();
        id.setUtente(utente);
        id.setLibro(libro);

        repo.deleteById(id);
    }

    @Override
    public void iniziaLettura(Long utenteId, Long libroId) {
        Utente utente = utenteRepo.findById(utenteId).orElseThrow();
        Libro libro = libroRepo.findById(libroId).orElseThrow();

        LibreriaUtenteId id = new LibreriaUtenteId();
        id.setUtente(utente);
        id.setLibro(libro);

        LibreriaUtente entry = repo.findById(id).orElse(null);
        if (entry == null || !"DA_LEGGERE".equals(entry.getStato()) || entry.getDataInizioLettura() != null) return;

        entry.setDataInizioLettura(LocalDate.now());
        repo.save(entry);
    }
}
