const BASE_URL = "https://smart-exam-system-171k.onrender.com";

function getAuthHeaders() {
  const token = localStorage.getItem("token");
  return {
    "Content-Type": "application/json",
    ...(token ? { "Authorization": "Bearer " + token } : {})
  };
}

async function handleResponse(response) {
  if (response.status === 401) {
    // Don't redirect if already on login or register page
    const onAuthPage = window.location.href.includes("login") || window.location.href.includes("register");
    if (!onAuthPage) {
      localStorage.clear();
      window.location.href = "../pages/login.html";
    }
    throw new Error("Session expired. Please log in again.");
  }
  return response;
}

async function login(data) {
  const response = await fetch(`${BASE_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username: data.username, password: data.password || "", role: data.role || "student" })
  });
  if (response.status === 401) {
    const err = await response.json().catch(() => ({}));
    throw new Error(err.error || "Invalid credentials");
  }
  if (!response.ok) throw new Error("Login failed");
  return response.json();
}

async function registerUser(data) {
  const response = await fetch(`${BASE_URL}/students/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });
  if (!response.ok) {
    const err = await response.json().catch(() => ({}));
    throw new Error(err.message || "Registration failed");
  }
  return response.json();
}

async function getAllStudents() {
  const r = await fetch(`${BASE_URL}/students`, { headers: getAuthHeaders() }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to load students");
  return r.json();
}

async function getAllTeachers() {
  const r = await fetch(`${BASE_URL}/teachers`, { headers: getAuthHeaders() }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to load teachers");
  return r.json();
}

async function registerTeacher(data) {
  const r = await fetch(`${BASE_URL}/teachers/register`, {
    method: "POST", headers: getAuthHeaders(), body: JSON.stringify(data)
  }).then(handleResponse);
  if (!r.ok) throw new Error("Teacher registration failed");
  return r.json();
}

async function getExams() {
  const r = await fetch(`${BASE_URL}/exams`, { headers: getAuthHeaders() }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to load exams");
  return r.json();
}

async function createExam(data) {
  const r = await fetch(`${BASE_URL}/exams/create`, {
    method: "POST", headers: getAuthHeaders(), body: JSON.stringify(data)
  }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to create exam");
  return r.json();
}

async function deleteExam(id) {
  const r = await fetch(`${BASE_URL}/exams/${id}`, {
    method: "DELETE", headers: getAuthHeaders()
  }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to delete exam");
  return r.text();
}

async function getQuestions(examId) {
  const r = await fetch(`${BASE_URL}/questions/exam/${examId}`, { headers: getAuthHeaders() }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to load questions");
  return r.json();
}

async function addQuestion(data) {
  const r = await fetch(`${BASE_URL}/questions/add`, {
    method: "POST", headers: getAuthHeaders(), body: JSON.stringify(data)
  }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to add question");
  return r.json();
}

async function updateQuestion(id, data) {
  const r = await fetch(`${BASE_URL}/questions/${id}`, {
    method: "PUT", headers: getAuthHeaders(), body: JSON.stringify(data)
  }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to update question");
  return r.json();
}

async function deleteQuestion(id) {
  const r = await fetch(`${BASE_URL}/questions/${id}`, {
    method: "DELETE", headers: getAuthHeaders()
  }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to delete question");
  return r.text();
}

async function submitExam(data) {
  const r = await fetch(`${BASE_URL}/exams/attempt`, {
    method: "POST", headers: getAuthHeaders(), body: JSON.stringify(data)
  }).then(handleResponse);
  // 409 = already attempted
  if (r.status === 409) {
    const body = await r.json();
    const err = new Error(body.error || "Already attempted");
    err.alreadyTaken = true;
    err.score = body.score;
    throw err;
  }
  if (!r.ok) throw new Error("Submission failed");
  return r.json();
}

async function getAllResults() {
  const r = await fetch(`${BASE_URL}/results`, { headers: getAuthHeaders() }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to load results");
  return r.json();
}

async function getResultsByStudent(studentId) {
  const r = await fetch(`${BASE_URL}/results/student/${studentId}`, { headers: getAuthHeaders() }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to load results");
  return r.json();
}

async function getResultsByExam(examId) {
  const r = await fetch(`${BASE_URL}/results/exam/${examId}`, { headers: getAuthHeaders() }).then(handleResponse);
  if (!r.ok) throw new Error("Failed to load results");
  return r.json();
}

function logout() {
  localStorage.clear();
  window.location.href = "../index.html";
}

// Theme toggle — persists across pages
function initTheme() {
  const saved = localStorage.getItem("theme") || "dark";
  document.documentElement.setAttribute("data-theme", saved);
  const btn = document.getElementById("themeToggle");
  if (btn) btn.textContent = saved === "dark" ? "☀️" : "🌙";
}

function toggleTheme() {
  const current = document.documentElement.getAttribute("data-theme");
  const next = current === "dark" ? "light" : "dark";
  document.documentElement.setAttribute("data-theme", next);
  localStorage.setItem("theme", next);
  const btn = document.getElementById("themeToggle");
  if (btn) btn.textContent = next === "dark" ? "☀️" : "🌙";
}

// Mobile sidebar
function initSidebar() {
  const hamburger = document.getElementById("hamburger");
  const sidebar   = document.getElementById("sidebar");
  const overlay   = document.getElementById("sidebarOverlay");
  if (!hamburger) return;
  hamburger.onclick = () => {
    sidebar.classList.toggle("open");
    overlay.classList.toggle("open");
  };
  overlay.onclick = () => {
    sidebar.classList.remove("open");
    overlay.classList.remove("open");
  };
}