window.onload = function () {
  const instance = axios.create({
    baseURL: 'http://10.110.12.65:8080', // URL da API
    headers: {
      'Content-Type': 'application/json',
    },
  });

  // Captura o formulário de cadastro
  const formAluno = document.getElementById('formAluno');

  // Adiciona o evento de envio de formulário
  formAluno.addEventListener('submit', function (event) {
    event.preventDefault(); // Impede o envio do formulário tradicional
    cadastrarAluno(); // Chama a função para cadastrar o aluno
  });

  // Função para cadastrar o aluno
  async function cadastrarAluno() {
    const cpf = document.getElementById('cpf').value;
    const nome = document.getElementById('nome').value;
    const turma = parseInt(document.getElementById('turma').value);

    // Validação simples para garantir que os campos não estão vazios
    if (!cpf || !nome || isNaN(turma)) {
      alert('Por favor, preencha todos os campos.');
      return;
    }

    const alunoData = {
      cpf: cpf,
      nome: nome,
      id_turma: turma,
    };

    try {
      // Envia os dados para o servidor via POST
      const result = await instance.post('/alunos', alunoData);
      console.log('Resposta completa da API:', result);

      if (result.status === 200) {
        alert('Aluno cadastrado com sucesso!');
        formAluno.reset(); // Limpar os campos do formulário após o cadastro
      } else {
        alert('Erro ao cadastrar o aluno!');
      }
    } catch (err) {
      console.error('Erro ao cadastrar aluno:', err);

      if (err.response) {
        console.error('Resposta de erro da API:', err.response.data);
        console.error('Status da resposta:', err.response.status);
        console.error('Headers da resposta:', err.response.headers);
        alert(
          `Erro do servidor: ${err.response.status} - ${JSON.stringify(
            err.response.data
          )}`
        );
      } else if (err.request) {
        console.error('Erro na requisição:', err.request);
        alert('Erro ao enviar requisição para o servidor.');
      } else {
        console.error('Erro ao configurar a requisição:', err.message);
        alert('Erro desconhecido: ' + err.message);
      }
    }
  }
};
