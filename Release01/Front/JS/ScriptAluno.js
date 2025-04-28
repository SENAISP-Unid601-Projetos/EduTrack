
window.onload = function() {
    const instance = axios.create({
        baseURL: 'http://10.110.12.65:8080',  
        headers: {
            "Content-Type": "application/json"
        }
    });

  FindAluno();

  async function FindAluno() {
    try {
      const result = await instance.get('/alunos');

      if (result.status === 200) {
        imprimir(result.data);
      }
    } catch (err) {
      console.error('Erro ao buscar os alunos:', err);
    }
  }

  function imprimir(alunos) {
    const alunosTable = document.querySelector('.alunos-table tbody');
    alunosTable.innerHTML = '';

    alunos.forEach((aluno) => {
      const row = document.createElement('tr');
      row.setAttribute('data-id', aluno.id);

      row.innerHTML = `
                <td>${aluno.cpf}</td>
                <td>${aluno.nome}</td>
                <td>${aluno.id_turma}</td>
                <td>
                    <button class="action-btn delete"><i class="fas fa-trash"></i></button>
                    <button class="action-btn view"><i class="fas fa-eye"></i></button>
                </td>
            `;

      // Adicionando o evento de deleção à linha
      const deleteButton = row.querySelector('.delete');
      deleteButton.addEventListener('click', function () {
        deleteAluno(aluno.id); // Passa o ID do aluno para a função
      });

      alunosTable.appendChild(row);
    });
  }

  // Função para excluir o aluno
  async function deleteAluno(alunoId) {
    console.log('clicou');
    try {
      // Confirmação de exclusão
      const confirmDelete = confirm(
        'Tem certeza que deseja excluir este aluno?'
      );
      if (!confirmDelete) return; // Se o usuário cancelar, nada acontece

      // Faz a requisição DELETE para excluir o aluno
      const result = await instance.delete(`/alunos/${alunoId}`);

      if (result.status === 200) {
        alert('Aluno excluído com sucesso!');

        // Remove a linha da tabela
        const row = document.querySelector(`tr[data-id="${alunoId}"]`);
        if (row) row.remove();
      } else {
        alert('Erro ao excluir o aluno!');
      }
    } catch (err) {
      console.error('Erro ao excluir aluno:', err);
      alert('Erro ao excluir o aluno!');
    }
  }
};
