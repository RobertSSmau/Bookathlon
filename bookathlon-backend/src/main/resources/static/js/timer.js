let seconds = 10;
  const timerEl = document.getElementById("timer");

  const countdown = setInterval(() => {
    seconds--;
    timerEl.textContent = seconds;

    if (seconds <= 0) {
      clearInterval(countdown);
      const id = document.querySelector('input[name="challengeId"]').value;
      window.location.href = `/challenge/esito?id=${id}&corretta=false`;
    }
  }, 1000);