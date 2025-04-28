const axiosInstance = axios.create({
  baseURL: "http://10.110.12.52:8080",
  headers: {
    "Content-Type": "application/json",
  }
});

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
    }

  } catch (err) {
    console.error("Erro ao tentar login: ", err.response?.data || err.message);
  }
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
    }
  } catch (err) {
    console.error("Erro ao tentar cadastrar: ", err.response?.data || err.message);
  }
}
