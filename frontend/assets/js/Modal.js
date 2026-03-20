function showAlert(title, message, icon = "ℹ️") {
  return new Promise(resolve => {
    const b = document.createElement("div");
    b.className = "modal-backdrop";
    b.innerHTML = `<div class="modal-box"><div class="modal-icon">${icon}</div><div class="modal-title">${title}</div><div class="modal-message">${message}</div><div class="modal-actions"><button class="btn btn-primary" id="mOk" style="width:auto;padding:11px 36px">OK</button></div></div>`;
    document.body.appendChild(b);
    b.querySelector("#mOk").onclick = () => { b.remove(); resolve(true); };
  });
}

function showConfirm(title, message, icon = "❓", type = "danger") {
  return new Promise(resolve => {
    const b = document.createElement("div");
    b.className = "modal-backdrop";
    const confirmCls = type === "danger" ? "btn-danger-full" : "btn btn-primary";
    b.innerHTML = `<div class="modal-box"><div class="modal-icon">${icon}</div><div class="modal-title">${title}</div><div class="modal-message">${message}</div><div class="modal-actions"><button class="btn btn-outline" id="mNo" style="width:auto;padding:11px 28px">Cancel</button><button class="${confirmCls}" id="mYes">Confirm</button></div></div>`;
    document.body.appendChild(b);
    b.querySelector("#mYes").onclick = () => { b.remove(); resolve(true); };
    b.querySelector("#mNo").onclick  = () => { b.remove(); resolve(false); };
    b.addEventListener("click", e => { if (e.target === b) { b.remove(); resolve(false); } });
  });
}