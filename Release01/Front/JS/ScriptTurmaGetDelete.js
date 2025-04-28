const instance = axios.create({
    baseURL: "http://10.110.12.65:8080",
    headers: {
        "Content-Type": "application/json"
    }
});

async function fetchTurmas() {
  try {
      const response = await instance.get('/turmas');
      const turmas = response.data;
      const turmasBody = document.getElementById('turmasBody');
      
      turmasBody.innerHTML = '';

      turmas.forEach(turmas => {
          const row = document.createElement('tr');
          row.innerHTML = `
              <td>${turmas.sigla}</td>
              <td>${turmas.nome}</td>
              <td>${turmas.termo}</td>
              <td>
                  <button class="action-button delete" onclick="deleteTurma('${turmas.sigla}')">
                      <img src="../Recursos/delete.png" alt="Delete">
                  </button>
                  <button class="action-button visualize" onclick="visualizeTurma('${turmas.sigla}')">
                      <img src="../Recursos/visualize.png" alt="Visualize">
                  </button>
              </td>
          `;
          turmasBody.appendChild(row);
      });
  } catch (error) {
      console.error('Erro ao buscar turmas:', error);
      alert('Não foi possível carregar as turmas. Tente novamente.');
  }
}



async function fetchTurmas() {
    try {
        const response = await instance.get('/turmas');
        const turmas = response.data;
        const turmasBody = document.getElementById('turmasBody');
        
        turmasBody.innerHTML = '';  // Limpa a tabela antes de adicionar os dados

        turmas.forEach(turmas => {
            const row = document.createElement('tr');
            
            // Atribuindo o ID da turma no atributo 'data-id' da linha, mas não o mostrando na interface
            row.setAttribute('data-id', turmas.id);

            row.innerHTML = `
                <td>${turmas.sigla}</td>
                <td>${turmas.nome}</td>
                <td>${turmas.termo}</td>
                <td>
                    <button class="action-button delete" onclick="deleteTurma(this)">
                        <img src="../Recursos/delete.png" alt="Delete">
                    </button>
                    <button class="action-button visualize" onclick="visualizeTurma('${turmas.sigla}')">
                        <img src="../Recursos/visualize.png" alt="Visualize">
                    </button>
                </td>
            `;
            turmasBody.appendChild(row);
        });
    } catch (error) {
        console.error('Erro ao buscar turmas:', error);
        alert('Não foi possível carregar as turmas. Tente novamente.');
    }
}

async function deleteTurma(button) {
    const row = button.closest('tr');  
    const id = row.getAttribute('data-id'); 

    if (confirm(`Deseja realmente excluir a turma com ID ${id}?`)) {
        try {
            const response = await axios.delete(`/turmas/deletar/${id}`);
            if (response.status === 200) {
                
                row.remove();
                alert(`Turma com ID ${id} excluída com sucesso.`);
            } else {
                alert('Erro ao excluir a turma. Tente novamente.');
            }
        } catch (error) {
            console.error('Erro ao excluir a turma:', error);
            alert('Não foi possível excluir a turma. Tente novamente.');
        }
    }
}
document.addEventListener('DOMContentLoaded', fetchTurmas);
async function visualizarTurma(button) {
    
}