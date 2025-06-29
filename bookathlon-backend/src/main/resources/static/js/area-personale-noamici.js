  document.addEventListener("DOMContentLoaded", () => {
    const amiciCount = parseInt(document.getElementById("dati-utente").dataset.amici);
    
    if (amiciCount === 0) {
      document.querySelectorAll('button[onclick^="apriSceltaChallenge"]').forEach(btn => {
        btn.disabled = true;
        btn.classList.add('btn-aggiunto');
        btn.innerText = "Nessun amico";
        btn.title = "Aggiungi amici per creare una challenge";
      });
    }
  });