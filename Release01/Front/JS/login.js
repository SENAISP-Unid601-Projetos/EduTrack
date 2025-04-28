const response = axios.create( {
  baseURL: "http://10.110.12.52:8080",
  headers: {
   "Content-Type": "application/json"
  }
  
})


async function login(email,senha) {
 try {
   const response = await response.post ('API', {
     email: email,
     senha: senha
   })

   if(response === 200) {
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
login('Email', 'Senha')