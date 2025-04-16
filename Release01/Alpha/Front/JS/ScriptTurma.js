let emailRecebidoNaPrincipal = sessionStorage.getItem('email'); // pega o e-mail da sessão

window.onload = listarTurmasEatualizarSaudacao;

function listarTurmasEatualizarSaudacao() {
  let saudacao = document.getElementById('professorSaudacao');

  fetch(`http://localhost:8080/professores/get/${emailRecebidoNaPrincipal}`)
    .then((res) => {
      if (!res.ok) {
        throw new Error('Erro na requisição');
      }
      return res.json();
    })
    .then((dados) => {
      saudacao.textContent = `Olá, professor ${dados.nome}!`;
    })
    .catch((erro) => {
      console.error('Erro', erro);
    });

  fetch(`http://localhost:8080/turmas/get/${emailRecebidoNaPrincipal}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error('Erro na requisição');
      }
      return response.json();
    })
    .then((dados) => {
      console.log(dados);
      transformar(dados);
    })
    .catch((erro) => {
      console.error('Erro:', erro);
    });
}

function transformar(dados) {
  const tabela = document.getElementById('table');

  for (let i = 0; i < dados.length; i++) {
    let item = dados[i];

    let linha = document.createElement('tr');
    let numeroColuna = document.createElement('td');
    let nomeColuna = document.createElement('td');
    let acoesColuna = document.createElement('td');

    const botaoExcluir = document.createElement('input');
    botaoExcluir.className = 'delete';
    botaoExcluir.type = 'button';
    botaoExcluir.value = 'Excluir';
    botaoExcluir.onclick = () => deletarItem(item.id);

    const botaoView = document.createElement('input');
    botaoView.className = 'view';
    botaoView.type = 'button';
    botaoView.value = 'Visualizar';
    botaoView.onclick = () => outraPagina(item.id);

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
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Erro ao deletar');
      }
      return response.text();
    })
    .then((dados) => {
      alert('Item deletado com sucesso');
      location.reload();
    })
    .catch((erro) => {
      console.error('Erro:', erro);
    });
}

function cadastrar() {
  const nomeTurma = document.getElementById('emailLogin').value;

  if (!nomeTurma) {
    alert('Por favor, preencha o campo com o nome da turma.');
    return;
  }

  fetch(`http://localhost:8080/professores/get/${emailRecebidoNaPrincipal}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error('Erro na requisição');
      }
      return response.json();
    })
    .then((dados) => {
      let idProf = dados.id;
      return fetch('http://localhost:8080/turmas', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nome: nomeTurma, id_professor: idProf }),
      });
    })
    .then((response) => {
      if (!response.ok) {
        return response.text().then((err) => {
          throw new Error(err.message || 'Erro na resposta do servidor');
        });
      }
      return response.text();
    })
    .then(() => {
      alert('Turma cadastrada com sucesso!');
      window.location.href = 'principal.html';
    })
    .catch((error) => {
      console.error('Erro ao cadastrar turma:', error);
      alert(`Erro ao cadastrar turma: ${nomeTurma}`);
    });
}

function outraPagina(idTurma) {
  window.location.href = `atividades.html?idTurma=${idTurma}`;
}
