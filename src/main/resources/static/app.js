// Base da API. Vazio = mesma origem (front servido pelo próprio Spring Boot).
// Se abrir o HTML de outro lugar, troque para "http://localhost:8080".
const API = "";

const $ = (id) => document.getElementById(id);

let produtos = [];
let pedidos = [];

function log(msg, isError = false) {
  const line = document.createElement("div");
  if (isError) line.className = "err";
  line.textContent = `[${new Date().toLocaleTimeString()}] ${msg}`;
  $("log").prepend(line);
}

async function api(method, path, body) {
  const opts = { method, headers: {} };
  if (body !== undefined) {
    opts.headers["Content-Type"] = "application/json";
    opts.body = JSON.stringify(body);
  }
  log(`→ ${method} ${path}${body ? " " + JSON.stringify(body) : ""}`);
  const res = await fetch(API + path, opts);
  const text = await res.text();
  let data;
  try { data = JSON.parse(text); } catch { data = text; }
  if (!res.ok) {
    log(`← ${res.status} ${text}`, true);
    throw new Error(`HTTP ${res.status}`);
  }
  log(`← ${res.status} ${text || "(sem corpo)"}`);
  return data;
}

function esc(s) {
  return String(s ?? "").replace(/[&<>"]/g, (c) => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;" }[c]));
}

const money = (v) => "R$ " + Number(v).toFixed(2);

function fillSelect(sel, items, label) {
  sel.innerHTML = items.length
    ? items.map((i) => `<option value="${i.id}">${label(i)}</option>`).join("")
    : `<option value="">(nenhum)</option>`;
}