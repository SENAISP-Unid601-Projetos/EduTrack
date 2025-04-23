const response = axios.create( {
  baseURL: "http://10.110.12.6:8080",
  headers: {
   "Content-Type": "application/json"
  }
  
})


async function login(username,senha) {
 try {
   const response = await response.post ('API', {
     username: username,
     senha: senha
   })

   if(response === 200  ) {
     console.log('Login Bem Sucedido!');
     console.log('Dados retornados:', response.data);
     
     return response.data;
   } else {
       console.log('algo deu errado:', response.status)
   }

 }catch(err) {
   console.error("Erro ao tentar login: ", err.response?.data || err.message)
 }
 
 
}
login('meuUsuario', 'minhaSenha')
 .then(data => {
   if (data) {
     // Faça algo com os dados do login
     alert(`Bem-vindo, ${data.usuario.nome}!`);
   }
 });
