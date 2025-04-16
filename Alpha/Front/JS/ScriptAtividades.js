window.addEventListener('DOMContentLoaded', listarAtividades);

const params = new URLSearchParams(window.location.search);
const idTurma = params.get('idTurma');
console.log(idTurma);

function listarAtividades() {
    let cadastroBtn = document.getElementById('cadastro_atividade');
    cadastroBtn.href = `/HTML/cadastro_atividade.html?idTurma=${idTurma}`
    console.log('ID da turma recebido:', idTurma); // Verifique se o ID está correto

    // Certifique-se de que idTurma é válido antes de fazer a requisição
    if (!idTurma) {
        console.error("ID da turma não encontrado na URL.");
        return;
    }

    fetch(`http://localhost:8080/atividades/get/${idTurma}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na requisição');
            }
            return response.json();
        })
        .then(dados => {
            console.log(dados);
            transformar(dados);
        })
        .catch(erro => {
            console.error('Erro:', erro);
        });
}


function transformar(dados) {
    const tabela = document.getElementById('table');

    for (let i = 0; i < dados.length; i++) {
        let item = dados[i];

        let linha = document.createElement("tr");
        let numeroColuna = document.createElement("td");
        let descricaoColuna = document.createElement("td");
        let acoesColuna = document.createElement("td");

        const botaoExcluir = document.createElement("input");
        botaoExcluir.className = "delete";
        botaoExcluir.type = "button";
        botaoExcluir.value = "Excluir";
        botaoExcluir.onclick = () => deletarItem(item.id);


        numeroColuna.textContent = item.id;
        descricaoColuna.textContent = item.descricao;

        acoesColuna.appendChild(botaoExcluir);
        linha.appendChild(numeroColuna);
        linha.appendChild(descricaoColuna);
        linha.append(acoesColuna);
        tabela.appendChild(linha);
    }
}

function deletarItem(id) {
    fetch(`http://localhost:8080/atividades/deletar/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao deletar');
            }
            return response.text();
        })
        .then(dados => {
            alert('Item deletado com sucesso:', dados);
            location.reload();
        })
        .catch(erro => {
            console.error('Erro:', erro);
        });
}

function cadastrarAtividades() {
    const params = new URLSearchParams(window.location.search);
    const idTurma = params.get('idTurma');
    
    const nomeAtividade = document.getElementById('emailLogin').value;

    if (!nomeAtividade) {
        alert('Por favor, preencha o campo com o nome da atividade.');
        return;
    }

    // Verifique se o idTurma está presente antes de enviar a requisição
    if (!idTurma) {
        alert('ID da turma não encontrado!');
        return;
    }

    console.log(JSON.stringify({
        descricao: nomeAtividade,
        turma_id: idTurma // Certifique-se de que está enviando apenas o idTurma
    }))

    fetch('http://localhost:8080/atividades', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            descricao: nomeAtividade,
            id_turma: idTurma // Certifique-se de que está enviando apenas o idTurma
        })
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(err => {
                throw new Error(err.message || 'Erro na resposta do servidor');
            });
        }
        return response.text();
    })
    .then(() => {
        alert("Atividade cadastrada com sucesso!");
        window.location.href = `atividades.html?idTurma=${idTurma}`; // Redireciona para a página de atividades
    })
    .catch(error => {
        console.error('Erro ao cadastrar atividade:', error);
        alert(`Erro ao cadastrar atividade: ${nomeAtividade}`);
    });
}


