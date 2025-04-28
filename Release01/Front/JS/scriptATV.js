let atividades = [];
const baseURL = "http://10.110.12.65:8080";

async function carregarAtividades() {
  try {
    const resposta = await axios.get(`${baseURL}/atividades`);

    atividades = resposta.data;

    const tabela = document.getElementById('tabela-atividades');
    tabela.innerHTML = "";

    atividades.forEach((atividade, index) => {
      const linha = document.createElement("tr");
      linha.innerHTML = `
        <td>${atividade.nome}</td>
        <td>${atividade.descricao}</td>
        <td>${atividade.id_turma}</td>
        <td class="actions">
          <button class="botao" onclick="deleteAtividade(${atividade.id})">
            <i class="fas fa-trash fa-2x"></i>
          </button>
          <button class="botao" onclick="showModal(${index})">
            <i class="fas fa-eye fa-2x"></i>
          </button>
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
        descricao: document.getElementById('desc').value,
        nome: document.getElementById('nome').value,
        id_turma: parseInt(document.getElementById('tur').value)
    }

    try {
        const resposta = await axios.post(`${baseURL}/atividades`, atv, {
            headers: { "Content-Type": "application/json" }
        });

        carregarAtividades();

        Fechar();

    } catch (error) {
        if (error.response) {
            console.error("Erro ao adicionar atividade:", error.response.data);
            alert("Erro ao adicionar atividade: " + error.response.data.message);
        } else {
            console.error("Erro ao fazer requisição:", error);
            alert("Erro ao adicionar atividade.");
        }
    }
    
}

async function deleteAtividade(id) {
  if (confirm("Tem certeza que deseja deletar essa atividade?")) {
    try {
      const resposta = await axios.delete(`${baseURL}/atividades/deletar/${id}`);

      const resultado = resposta.data;
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

  modal.querySelector("h2").innerText = `${atividade.nome}`;
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
