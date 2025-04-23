const URLTurma = 'http://localhost:8080/atividade';
const URLProfessor = 'http://localhost:8080/professor';

window.onload = () => {
  getProducts();
};


function getProducts() {
  fetch(`${URLTurma}/get`)
    .then((response) => response.json())
    .then((data) => transformTable(data))
    .catch((error) => console.error('Erro:', error));
}

function transformTable(data) {
  let tabelaProdutos = document.getElementById('tabela-produtos');
  for (let i = 0; i < data.length; i++) {
    let paraInserir = `
    <td>${data[i].numeroAtividade}</td>
    <td>${data[i].nome}</td>
    <td>${data[i].descricao}</td>
    <td>
      <button class="delete" type="button" data-id="${data[i].numeroAtividade}">Excluir</button>
      <button class="view" type="button" data-id="${data[i].numeroAtividade}">Visualizar</button>
    </td>
  `;
  ;
    tabelaProdutos.innerHTML += paraInserir;
  }
  getIdButtons();
}

function getIdButtons() {
  const botoesExcluir = document.querySelectorAll('.delete');
  botoesExcluir.forEach((botao) => {
    botao.addEventListener('click', (event) => {
      const id = event.target.getAttribute('data-id');
      deleteProduct(id);
    });
  });

  const botoesVisualizar = document.querySelectorAll('.view'); //Ainda nao finalizado no relacionamento
  botoesVisualizar.forEach((botao) => {
    botao.addEventListener('click', (event) => {
      window.location.href = '../html/atividade.html';
    });
  });

  // const botoesVisualizar = document.querySelectorAll('.view'); //Ainda nao finalizado no relacionamento
  // botoesVisualizar.forEach((botao) => {
  //   botao.addEventListener('click', (event) => {
  //     const id = event.target.getAttribute('data-id');
  //     console.log('ID para visualizar:', id);

  //   });
  // });
}

function deleteProduct(id) {
  console.log('ID do produto a ser excluído:', id);
  fetch(`${URLTurma}/delete/${id}`, {
    method: 'DELETE',
  })
    .then((response) => {
      if (response.ok) {
        console.log('Produto excluído com sucesso');
        location.reload();
        getProducts();
      } else {
        console.error('Erro ao excluir o produto');
      }
    })
    .catch((error) => console.error('Erro:', error));
}
