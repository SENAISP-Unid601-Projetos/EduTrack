
let atividades = [];

async function carregarAtividades() {
  try {
    const resposta = await fetch('http://localhost:8080/atividades');
    if (!resposta.ok) throw new Error("Erro ao buscar atividades");

    atividades = await resposta.json();

    const tabela = document.getElementById('tabela-atividades');
    tabela.innerHTML = "";

    atividades.forEach((atividade, index) => {
      const linha = document.createElement("tr");
      linha.innerHTML = `
        <td>${atividade.id}</td>
        <td>${atividade.descricao}</td>
        <td>${atividade.id_turma}</td>
        <td class="actions">
          <button class="botao" onclick="deleteAtividade(${atividade.id})">🗑️</button>
          <button class="botao" onclick="showModal(${index})">👁️</button>
        </td>
      `;
      tabela.appendChild(linha);
    });

  } catch (erro) {
    console.error("Erro ao carregar:", erro);
    alert("Erro ao carregar atividades.");
  }
}

async function deleteAtividade(id) {
  if (confirm("Tem certeza que deseja deletar essa atividade?")) {
    try {
      const resposta = await fetch(`http://localhost:8080/atividades/deletar/${id}`, {
        method: 'DELETE'
      });

      const resultado = await resposta.text();
      alert(resultado);
      carregarAtividades();

    } catch (erro) {
      console.error("Erro ao deletar:", erro);
      alert("Erro ao deletar atividade.");
    }
  }
}

function showModal(index) {
  const atividade = atividades[index];
  const modal = document.getElementById("atividadeModal");

  modal.querySelector("h2").innerText = `Atividade #${atividade.id}`;
  modal.querySelector("p").innerText = atividade.descricao;
  modal.querySelector("strong").innerText = `Turma ID: ${atividade.id_turma}`;
  modal.style.display = "block";
}

function closeModal() {
  document.getElementById("atividadeModal").style.display = "none";
}

window.onload = carregarAtividades;