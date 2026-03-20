let time;
let countdown;

function startTimer() {
  // Per-exam timer: read duration from localStorage (set when exam starts), fallback 30min
  const duration = parseInt(localStorage.getItem("examDuration") || "30");
  time = duration * 60;

  const timerEl = document.getElementById("timer");

  countdown = setInterval(async () => {
    const minutes = Math.floor(time / 60);
    const seconds = String(time % 60).padStart(2, "0");
    timerEl.textContent = `${minutes}:${seconds}`;

    // Warning when under 5 minutes
    if (time <= 300) {
      timerEl.closest(".timer-badge")?.classList.add("warning");
    }

    time--;

    if (time < 0) {
      clearInterval(countdown);
      await showAlert("Time's Up! ⏰", "Your time is over. Your exam is being submitted now.", "⏰");
      handleSubmit();
    }
  }, 1000);
}

startTimer();