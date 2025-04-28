  const axiosInstance = axios.create({
    baseURL: "http://10.110.12.75:5501",
    headers: {
      "Content-Type": "application/json",
    }
  });

  // Mostrar erro
  function mostrarErro(mensagem) {
    const erroDiv = document.getElementById('erroMensagem');
    erroDiv.textContent = mensagem;
    erroDiv.style.display = 'block';

    const sucessoDiv = document.getElementById('sucessoMensagem');
    sucessoDiv.textContent = '';
  }

  // Mostrar sucesso
  function mostrarSucesso(mensagem) {
    const sucessoDiv = document.getElementById('sucessoMensagem');
    sucessoDiv.textContent = mensagem;
    sucessoDiv.style.display = 'block';

    const erroDiv = document.getElementById('erroMensagem');
    erroDiv.textContent = '';
  }

  // Função de cadastro
  async function cadastrar(nome, email, senha) {
    try {
      const response = await axiosInstance.post('/professores', {
        nome: nome,
        email: email,
        senha: senha,
      });

      if (response.status === 200){
        console.log('Cadastro bem sucedido!');
        console.log('Dados retornados:', response.data);
        mostrarSucesso('Cadastro realizado com sucesso!');

        // Redirecionar para a tela de login após 3 segundos
        setTimeout(function () {
          window.location.href = "login.html";
        }, 3000);
        
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

  // Função de login (se quiser usar depois)
  async function login(email, senha) {
    try {
      const response = await axiosInstance.post('professores/login', {
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

  // Eventos dos botões
  document.getElementById("btnCadastrar").addEventListener("click", function () {
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    if (!nome || !email || !senha) {
      mostrarErro('Preencha todos os campos!');
      return;
    }

  });

  document.getElementById("btnVoltar").addEventListener("click", function () {
    window.location.href = "login.html";
  });
