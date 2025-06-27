


  document.addEventListener("DOMContentLoaded", function () {
    let currentForm = null;

    const addButtons = document.querySelectorAll("form[action*='/area-personale/aggiungi'] button[type='submit']");

    addButtons.forEach(button => {
      button.addEventListener("click", function (e) {
        e.preventDefault();

        currentForm = button.closest("form");

        // Trova l'immagine di copertina nella stessa card
        const card = button.closest(".card");
        const img = card.querySelector("img");
        const modalImg = document.getElementById("modal-cover");

        // Imposta la src nella modale
        if (img && modalImg) {
          modalImg.src = img.src;
        }

        document.getElementById("confirm-modal").classList.remove("d-none");
      });
    });

    document.getElementById("confirm-yes").addEventListener("click", function () {
      if (currentForm) {
        document.getElementById("confirm-modal").classList.add("d-none");
        currentForm.submit();
      }
    });

    document.getElementById("confirm-no").addEventListener("click", function () {
      document.getElementById("confirm-modal").classList.add("d-none");
      currentForm = null;
    });
  });

  



