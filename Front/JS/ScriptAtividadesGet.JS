window.onload = listarAtividades();

function listarAtividades() {
    fetch('http://localhost:8080/atividades')
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
    fetch(`http://localhost:8080/turmas/deletar/${id}`, {
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

