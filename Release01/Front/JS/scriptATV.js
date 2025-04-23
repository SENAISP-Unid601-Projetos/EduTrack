
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
        <td>${atividade.nome}</td>
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

async function AdicionarAtividadeEFechar() {
    const atv = {
        nome: document.getElementById('nome').value,
        descricao: document.getElementById('desc').value,
        turma_id: parseInt(document.getElementById('tur').value)
    }

    try {
        const resposta = await fetch('http://localhost:8080/atividades', {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(atv)
        });

        const resultado = await resposta.text();
        alert(resultado);
        carregarAtividades();
        Fechar();

    } catch (error) {
        console.error("Erro ao carregar:", error);
        alert("Erro ao postar atividades.");
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

function AdicionarAtividadeMostrar() {
    const div = document.getElementById('addAtv');
    div.style.display = "flex";
    div.style.flexDirection = "column";
}

function Fechar() {
    const div = document.getElementById('addAtv');
    div.style.display = "none";
}

window.onload = carregarAtividades;
