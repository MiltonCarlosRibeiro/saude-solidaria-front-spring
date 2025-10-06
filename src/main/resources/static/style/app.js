async function json(url) {
  const r = await fetch(url);
  if (!r.ok) throw new Error(`Falha ao carregar ${url}: ${r.status}`);
  return r.json();
}
function fillTable(tbody, rows, cols) {
  tbody.innerHTML = "";
  rows.forEach(r => {
    const tr = document.createElement("tr");
    cols.forEach(c => {
      const td = document.createElement("td");
      td.textContent = r[c] ?? "";
      tr.appendChild(td);
    });
    tbody.appendChild(tr);
  });
}
async function loadAll() {
  try {
    const [beneficiarios, ind, corp, medicamentos, doacoes] = await Promise.all([
      json("/api/beneficiarios"),
      json("/api/doadores-individuais"),
      json("/api/doadores-corporativos"),
      json("/api/medicamentos"),
      json("/api/doacoes")
    ]);
    fillTable(document.querySelector("#tbl-beneficiarios tbody"), beneficiarios, ["nome","documento","telefone"]);
    fillTable(document.querySelector("#tbl-doadores-ind tbody"), ind, ["nome","email","telefone"]);
    fillTable(document.querySelector("#tbl-doadores-corp tbody"), corp, ["razaoSocial","cnpj","contato"]);
    fillTable(document.querySelector("#tbl-medicamentos tbody"), medicamentos, ["nome","fabricante","lote","validade"]);
    fillTable(document.querySelector("#tbl-doacoes tbody"), doacoes, ["tipoDoador","doadorId","beneficiarioId","medicamentoId","quantidade","data"]);
  } catch (e) {
    alert(e.message);
    console.error(e);
  }
}
document.addEventListener("DOMContentLoaded", loadAll);
