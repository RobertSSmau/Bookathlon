let libroIdSelezionato = null;

function apriSceltaChallenge(libroId) {
  libroIdSelezionato = libroId;
  document.getElementById("challenge-modal").classList.remove("d-none");
}

function chiudiModaleChallenge() {
  document.getElementById("challenge-modal").classList.add("d-none");
}

function vaiACreazioneChallenge() {
  if (!libroIdSelezionato) return;
  window.location.href = `/challenge/nuova?libroId=${libroIdSelezionato}`;
}