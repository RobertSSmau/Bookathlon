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