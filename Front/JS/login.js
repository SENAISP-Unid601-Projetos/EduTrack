function Verificar() {
  let email = document.getElementById('emailLogin');
  let senha = document.getElementById('passwordLogin');

  fetch(`http://localhost:8080/professores/get/${email}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error('Não existe um usuário com esse email');
      }
      return response.json();
    })
    .then((professor) => {
      console.log(professor);
      Compara(professor, senha);
    })
    .catch((erro) => {
      console.error(erro);
    });
}

function Compara(professor, senha) {
  if (professor.senha === senha) {
    window.location.href = 'HTML/principal.html';
  } else {
    alert('Uma ou mais informações erradas.');
  }
}
