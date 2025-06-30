document.addEventListener('DOMContentLoaded', function () {
const textarea = document.getElementById('commentoText');
const counter = document.getElementById('charCounter');
const maxLen = textarea.getAttribute('maxlength');

textarea.addEventListener('input', function () {
    const current = textarea.value.length;
    counter.textContent = `${current} / ${maxLen}`;
});
});
