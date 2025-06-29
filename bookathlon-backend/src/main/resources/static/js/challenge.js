  function mostraSezioneChallenge(sezione) {
    const sezioni = ['classifiche', 'challenge'];
    sezioni.forEach(id => {
      const el = document.getElementById('sezione-' + id);
      if (el) {
        el.classList.toggle('d-none', id !== sezione);
      }
    });
  }

  document.addEventListener('DOMContentLoaded', () => {
    mostraSezioneChallenge('classifiche');
  });