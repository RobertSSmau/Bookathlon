const searchType = document.getElementById('searchType');
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');

searchType.addEventListener('change', function () {
  if (searchType.value === 'autore') {
    searchForm.action = '/cerca-per-autore';
    searchInput.name = 'autore';
    searchInput.placeholder = 'Inserisci l’autore';
  } else {
    searchForm.action = '/cerca-per-titolo';
    searchInput.name = 'titolo';
    searchInput.placeholder = 'Inserisci il titolo';
  }
});