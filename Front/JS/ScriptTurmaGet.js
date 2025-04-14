window.onload = listarTurmas();

function listarTurmas() {
    fetch('http://localhost:8080/turmas')
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
        let nomeColuna = document.createElement("td");
        let acoesColuna = document.createElement("td");

        const botaoExcluir = document.createElement("input");
        botaoExcluir.className = "delete";
        botaoExcluir.type = "button";
        botaoExcluir.value = "Excluir";
        botaoExcluir.onclick = () => deletarItem(item.id);

        const botaoView = document.createElement("input");
        botaoView.className = "view";
        botaoView.type = "button";
        botaoView.value = "Vizualizar";
        botaoView.onclick = () => outraPagina();

        numeroColuna.textContent = item.id;
        nomeColuna.textContent = item.nome;

        acoesColuna.appendChild(botaoExcluir);
        acoesColuna.appendChild(botaoView);
        linha.appendChild(numeroColuna);
        linha.appendChild(nomeColuna);
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

function outraPagina(){
    window.location.href = 'atividades.html';
  
}
