const axiosInstance = axios.create({
  baseURL: "http://10.110.12.52:8080",
  headers: {
    "Content-Type": "application/json",
  }
});

// Função para mostrar a mensagem de erro
function mostrarErro(mensagem) {
  const erroDiv = document.getElementById('erroMensagem');
  erroDiv.textContent = mensagem; 
  erroDiv.style.color = 'red';  
  erroDiv.style.fontSize = '14px'; 
  erroDiv.style.marginTop = '10px'; 
}

// Função de cadastro com nome
async function cadastrar(nome, email, senha) {
  try {
    const response = await axiosInstance.post('API/cadastro', {
      nome: nome,
      email: email,
      senha: senha,
    });

    if (response.status === 201) {
      console.log('Cadastro bem sucedido!');
      console.log('Dados retornados:', response.data);
      return response.data;
    } else {
      console.log('Erro no cadastro:', response.status);
      mostrarErro('Erro no cadastro. Verifique os dados e tente novamente.');
    }
  } catch (err) {
    console.error("Erro ao tentar cadastrar: ", err.response?.data || err.message);
    mostrarErro('Erro ao tentar cadastrar. Tente novamente mais tarde.');
  }
}


// Função de login
async function login(email, senha) {
  try {
    const response = await axiosInstance.post('API/login', {
      email: email,
      senha: senha
    });

    if (response.status === 200) {
      console.log('Login Bem Sucedido!');
      console.log('Dados retornados:', response.data);
      return response.data;
    } else {
      console.log('Algo deu errado:', response.status);
      mostrarErro('Erro no login. Verifique suas credenciais.');
    }

  } catch (err) {
    console.error("Erro ao tentar login: ", err.response?.data || err.message);
    mostrarErro('Erro ao tentar login. Tente novamente mais tarde.');
  }
}
