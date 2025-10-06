const API_BASE_URL = "/api";

// Função genérica para requisições fetch (sem alterações)
async function apiRequest(endpoint, method = "GET", body = null) {
  const options = { method, headers: {} };
  if (body) {
    options.headers["Content-Type"] = "application/json";
    options.body = JSON.stringify(body);
  }
  const response = await fetch(`${API_BASE_URL}${endpoint}`, options);
  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Falha na requisição para ${endpoint}: ${response.status} ${errorText}`);
  }
  const contentType = response.headers.get("content-type");
  if (contentType && contentType.indexOf("application/json") !== -1) {
    return response.json();
  }
  return null;
}

// Preenche uma tabela com dados e botões de ação (sem alterações)
function fillTable(tbody, rows, cols, type) {
  tbody.innerHTML = "";
  rows.forEach(row => {
    const tr = document.createElement("tr");
    cols.forEach(c => {
      const td = document.createElement("td");
      td.textContent = row[c] ?? "";
      tr.appendChild(td);
    });
    const tdActions = document.createElement("td");
    tdActions.className = "actions";
    const btnEdit = document.createElement("button");
    btnEdit.className = "btn-action";
    btnEdit.textContent = "Editar";
    btnEdit.onclick = () => handleEdit(type, row);
    tdActions.appendChild(btnEdit);
    const btnDelete = document.createElement("button");
    btnDelete.className = "btn-action";
    btnDelete.textContent = "Excluir";
    btnDelete.onclick = () => handleDelete(type, row.id);
    tdActions.appendChild(btnDelete);
    tr.appendChild(tdActions);
    tbody.appendChild(tr);
  });
}

// Funções handleCreate, handleEdit, handleDelete (sem alterações)
async function handleDelete(type, id) { if (!confirm(`Tem certeza que deseja excluir este item?`)) return; try { await apiRequest(`/${type}/${id}`, "DELETE"); loadAll(); } catch (e) { alert(e.message); console.error(e); } }
async function handleEdit(type, item) { const fields = document.querySelector(`.btn-add[data-type="${type}"]`).dataset.fields.split(','); const updatedItem = { ...item }; let hasChanged = false; for (const field of fields) { if (field === 'id') continue; const newValue = prompt(`Editar ${field}:`, item[field] ?? ""); if (newValue === null) return; if (newValue !== item[field]) { updatedItem[field] = newValue; hasChanged = true; } } if (hasChanged) { try { await apiRequest(`/${type}/${item.id}`, "PUT", updatedItem); loadAll(); } catch (e) { alert(e.message); console.error(e); } } }
async function handleCreate(type, fields) { const newItem = {}; for (const field of fields) { const value = prompt(`Digite o valor para ${field}:`); if (value === null) return; newItem[field] = value; } try { await apiRequest(`/${type}`, "POST", newItem); loadAll(); } catch (e) { alert(e.message); console.error(e); } }

// Carrega todos os dados das APIs e preenche as tabelas
async function loadAll() {
  try {
    const [beneficiarios, doadoresInd, doadoresCorp, medicamentos, doacoes] = await Promise.all([
      apiRequest("/beneficiarios"),
      apiRequest("/doadores-individuais"),
      apiRequest("/doadores-corporativos"),
      apiRequest("/medicamentos"),
      apiRequest("/doacoes")
    ]);

    // LÓGICA DE CRUZAMENTO DE DADOS (sem alterações)
    const beneficiariosMap = new Map(beneficiarios.map(b => [b.id, b.nomeCompleto]));
    const medicamentosMap = new Map(medicamentos.map(m => [m.id, m.nome]));
    const doadoresIndMap = new Map(doadoresInd.map(i => [i.id, i.nome]));
    const doadoresCorpMap = new Map(doadoresCorp.map(c => [c.id, c.razaoSocial]));
    const doacoesEnriquecidas = doacoes.map(doacao => {
      const beneficiarioNome = beneficiariosMap.get(doacao.beneficiarioId) || doacao.beneficiarioId;
      const medicamentoNome = medicamentosMap.get(doacao.medicamentoId) || doacao.medicamentoId;
      const doadorNome = doadoresIndMap.get(doacao.doadorId) || doadoresCorpMap.get(doacao.doadorId) || doacao.doadorId;
      return {
        ...doacao,
        beneficiarioNome: beneficiarioNome,
        medicamentoNome: medicamentoNome,
        doadorNome: doadorNome,
      };
    });

    fillTable(document.querySelector("#tbl-beneficiarios tbody"), beneficiarios, ["nomeCompleto", "cpf", "email", "telefone", "medicamentoSolicitado"], "beneficiarios");
    fillTable(document.querySelector("#tbl-doadores-ind tbody"), doadoresInd, ["nome", "email", "telefone"], "doadores-individuais");

    // 👇 CORREÇÃO AQUI: A variável correta é "doadoresCorp"
    fillTable(document.querySelector("#tbl-doadores-corp tbody"), doadoresCorp, ["razaoSocial", "nomeFantasia", "cnpj", "statusConta"], "doadores-corporativos");

    fillTable(document.querySelector("#tbl-medicamentos tbody"), medicamentos, ["nome", "principioAtivo", "status", "quantidade"], "medicamentos");
    fillTable(document.querySelector("#tbl-doacoes tbody"), doacoesEnriquecidas, ["beneficiarioNome", "medicamentoNome", "doadorNome", "status", "quantidade", "dataSolicitacao"], "doacoes");

  } catch (e) {
    alert(e.message);
    console.error(e);
  }
}

// Event listener (sem alterações)
document.addEventListener("DOMContentLoaded", () => {
  loadAll();
  document.querySelectorAll(".btn-add").forEach(button => {
    const type = button.dataset.type;
    const fields = button.dataset.fields.split(',');
    button.onclick = () => handleCreate(type, fields);
  });
});