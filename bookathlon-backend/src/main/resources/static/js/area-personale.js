// area-personale.js

function toggleMenu() {
  const nav = document.querySelector('.nav-buttons');
  if (nav) {
    nav.classList.toggle('show');
  }
}

function nascondiElementi(ids) {
  ids.forEach(id => {
    const el = document.getElementById(id);
    if (el) el.classList.add('d-none');
  });
}

function mostraElementi(ids) {
  ids.forEach(id => {
    const el = document.getElementById(id);
    if (el) el.classList.remove('d-none');
  });
}

function mostraLibreria() {
  mostraElementi(['sezione-da-leggere', 'sezione-letti']);
  nascondiElementiAmici();
}

function mostraSezione(sezione) {
  if (sezione === 'amici') {
    nascondiElementi(['sezione-da-leggere', 'sezione-letti']);
    mostraElementiAmici();
  }
}

function mostraElementiAmici() {
  document.querySelectorAll('#sezione-amici').forEach(el => el.classList.remove('d-none'));
}

function nascondiElementiAmici() {
  document.querySelectorAll('#sezione-amici').forEach(el => el.classList.add('d-none'));
}

document.addEventListener("DOMContentLoaded", function () {
  let currentRemoveForm = null;

  const removeButtons = document.querySelectorAll("form[action*='/area-personale/rimuovi'] button[type='submit']");

  removeButtons.forEach(button => {
    button.addEventListener("click", function (e) {
      e.preventDefault();

      currentRemoveForm = button.closest("form");

      const card = button.closest(".card");
      const img = card.querySelector("img");
      const modalImg = document.getElementById("remove-cover");

      if (img && modalImg) {
        modalImg.src = img.src;
      }

      document.getElementById("remove-modal").classList.remove("d-none");
    });
  });

  document.getElementById("remove-yes").addEventListener("click", function () {
    if (currentRemoveForm) {
      document.getElementById("remove-modal").classList.add("d-none");
      currentRemoveForm.submit();
    }
  });

  document.getElementById("remove-no").addEventListener("click", function () {
    document.getElementById("remove-modal").classList.add("d-none");
    currentRemoveForm = null;
  });
});