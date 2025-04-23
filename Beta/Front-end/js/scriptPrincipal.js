const URLTurma = 'http://localhost:8080/turma';
const URLProfessor = 'http://localhost:8080/professor';

window.onload = () => {
  getTeacher();
  getProducts();
};

function getTeacher() {
  fetch(`${URLProfessor}/get`)
  .then((response) => response.json())
  .then((data) => showName(data))
  .catch((error) => console.error('Erro:', error));
}

function showName(data) {
  console.log(data)
  const name = document.querySelector('#nomeLogin');
  if(!name instanceof HTMLTitleElement) {
    alert('h1 não instanciado');
    return;
  }
  data = data[0];
  name.textContent = `Olá, professor ${data.nome}`;
}


function getProducts() {
  fetch(`${URLTurma}/get`)
    .then((response) => response.json())
    .then((data) => transformTable(data))
    .catch((error) => console.error('Erro:', error));
}

function transformTable(data) {
  let tabelaProdutos = document.getElementById('tabela-produtos');
  for (let i = 0; i < data.length; i++) {
    let paraInserir =`
    <td>${data[i].numeroTurma}</td>
    <td>${data[i].nome}</td>
    <td>
      <button class="delete" type="button" data-id="${data[i].numeroTurma}">Excluir</button>
      <button class="view" type="button" data-id="${data[i].numeroTurma}">Visualizar</button>
    </td>
  `;
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
      window.location.href = '../html/atividades.html';
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
