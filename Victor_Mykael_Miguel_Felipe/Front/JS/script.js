const instance = axios.create({
  baseURL: 'http://10.110.12.52:8080',
  headers: {
    'Content-Type': 'application/json',
  },
});

window.onload = () => {
  const btnPost = document.querySelector('#botaoEnvio');
  getAlunos();
  btnPost ? btnPost.addEventListener('click', getInputs) : null;
};

function getInputs() {
  const sigla = document.querySelector('#sigla').value;
  const nome = document.querySelector('#nome').value;
  const termo = document.querySelector('#termo').value;
  console.log(sigla, nome, termo);
  postTurma(sigla, nome, termo);
}

async function postTurma(sigla, nome, termo) {
  console.log('passei')
  try {
    const data = { sigla, nome, termo };
    const response = await instance.post('/turmas', data);
    if (response.status === 201) {
      return response.data;
    }
  } catch (err) {
    console.error('error', err);
    throw err;
  }
}

async function getAlunos() {
  try {
    const response = await instance.get('/alunos');
    if (response.status === 200) {
      formatarLista(response.data)
    }
  } catch (err) {
    console.error('error', err);
  }
} 

function formatarLista(data) {
  const ul = document.querySelector('#list');
  data.forEach(aluno => {
    const li = document.createElement('li');
    li.textContent = aluno.nome; 
    li.classList.add('aluno-item');
    li.addEventListener('click', () => {
      li.classList.toggle('selected');
    });
    ul.appendChild(li);
  });
}

function moverSelecionados(origemId, destinoId) {
  const origem = document.getElementById(origemId);
  const destino = document.getElementById(destinoId);
  const selecionados = origem.querySelectorAll('.selected');

  selecionados.forEach(item => {
    item.classList.remove('selected');
    destino.appendChild(item);
  });
}

document.querySelector('.botoes-transferencia').children[1].addEventListener('click', () => {
  moverSelecionados('list', 'listSelected'); // botão >
});

document.querySelector('.botoes-transferencia').children[2].addEventListener('click', () => {
  moverSelecionados('listSelected', 'list'); // botão <
});

// Se quiser fazer o botão ">>" (todos):
document.querySelector('.botoes-transferencia').children[0].addEventListener('click', () => {
  moverTodos('list', 'listSelected');
});

// Se quiser fazer o botão "<<" (todos):
document.querySelector('.botoes-transferencia').children[3].addEventListener('click', () => {
  moverTodos('listSelected', 'list');
});

function moverTodos(origemId, destinoId) {
  const origem = document.getElementById(origemId);
  const destino = document.getElementById(destinoId);
  const todos = origem.querySelectorAll('li');

  todos.forEach(item => {
    item.classList.remove('selected');
    destino.appendChild(item);
  });
}
