import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://10.110.12.52:8080',
  headers: {
    'Content-Type': 'application/json',
  },
});

window.onload(() => {
  console.log('passou')
  getAlunos();
});

async function postTurma(nome, idProfessor) {
  try {
    const data = { nome, idProfessor };
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
  data.forEach(list => {
      const ul = document.querySelector('#list');
      const li = document.createElement('li');
      li.textContent = list.email; 
      ul.appendChild(li);

  });
}




console.log(getAlunos());
