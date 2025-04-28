###**URL BASE**
URL: http://10.110.12.52:8080

### 📘 **Turma Controller**

- **PUT** `/turmas/atualizar/{id}`  
  Atualiza uma turma com base no ID.

- **GET** `/turmas`  
  Lista todas as turmas.

- **POST** `/turmas`  
  Cadastra uma nova turma.

- **GET** `/turmas/get/{email}`  
  Busca turmas por e-mail do professor.

- **DELETE** `/turmas/deletar/{id}`  
  Deleta uma turma com base no ID.

---

### 📙 **Professor Controller**

- **PUT** `/professores/atualizar/{id}`  
  Atualiza um professor com base no ID.

- **GET** `/professores`  
  Lista todos os professores.

- **POST** `/professores`  
  Cadastra um novo professor.

- **POST** `/professores/login`  
  Realiza o login de um professor.

- **GET** `/professores/get/{email}`  
  Busca um professor por e-mail.

- **DELETE** `/professores/deletar/{id}`  
  Deleta um professor com base no ID.

## 📌 Atividade Controller

- **PUT** `/atividades/atualizar/{id}`  
  Atualiza uma atividade com base no ID.

- **GET** `/atividades`  
  Lista todas as atividades.

- **POST** `/atividades`  
  Cadastra uma nova atividade.

- **GET** `/atividades/get/{id}`  
  Busca uma atividade pelo ID.

- **DELETE** `/atividades/deletar/{id}`  
  Deleta uma atividade com base no ID.

---

## 📒 Aluno Controller

- **PUT** `/alunos/{id}`  
  Atualiza um aluno com base no ID.

- **DELETE** `/alunos/{id}`  
  Deleta um aluno com base no ID.

- **GET** `/alunos`  
  Lista todos os alunos.

- **POST** `/alunos`  
  Cadastra um novo aluno.
