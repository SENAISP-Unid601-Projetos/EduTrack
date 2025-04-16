function login() {
  const email = document.getElementById('emailLogin').value;
  const senha = document.getElementById('passwordLogin').value;

  fetch('http://localhost:8080/professores/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ email, senha }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.status === 'success') {
        alert(data.message);

        // Salva o e-mail na sessão
        sessionStorage.setItem('email', email);

        // Redireciona na mesma aba
        window.location.href = 'HTML/principal.html';
      } else {
        alert(data.message);
      }
    })
    .catch((error) => {
      alert('Erro ao fazer login');
      console.log(error);
    });
}
