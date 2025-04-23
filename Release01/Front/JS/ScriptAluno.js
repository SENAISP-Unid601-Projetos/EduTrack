window.onload = function() {
    const instance = axios.create({
        baseURL: 'http://10.110.12.6:8080',
        headers: {
            "Content-Type": "application/json"
        }
    });

    // Chama a função FindAluno para buscar os alunos ao carregar a página
    FindAluno();

    async function FindAluno() {
        try {
            const result = await instance.get('/alunos'); // Faz a requisição para obter os alunos

            if (result.status === 200) {
                imprimir(result.data); 
                console.log(result.data);// Passa os dados para a função imprimir
            }
        } catch (err) {
            console.error('Erro ao buscar os alunos:', err);
        }
    }

    // Função para exibir os alunos na tabela
    function imprimir(alunos) {
        const alunosTable = document.querySelector('.alunos-table tbody');
        alunosTable.innerHTML = ''; // Limpa a tabela antes de adicionar os alunos

        // Percorre o array de alunos e cria uma linha para cada um
        alunos.forEach(aluno => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${aluno.nome}</td>
                <td>${aluno.cpf}</td>
                <td>${aluno.id_turma}</td>
                <td>
                    <button class="action-btn delete"><i class="fas fa-trash"></i></button>
                    <button class="action-btn view"><i class="fas fa-eye"></i></button>
                </td>
            `;
            alunosTable.appendChild(row);
        });
    }
};
