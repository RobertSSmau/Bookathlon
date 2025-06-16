document.addEventListener('DOMContentLoaded', function () {
    const select = document.getElementById('genereSelect');
    const sezioni = document.querySelectorAll('.genere-section');

    select.addEventListener('change', function () {
      const valore = this.value;

      sezioni.forEach(section => {
        const genere = section.getAttribute('data-genere');

        if (valore === 'all' || valore === genere) {
          section.style.display = 'block';
        } else {
          section.style.display = 'none';
        }
      });
    });
  });