window.onload = function() {
    const instance = axios.create({
        baseURL: 'http://10.110.12.6:8080',
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
                console.log(result.data);
            }
        } catch (err) {
            console.error('Erro ao buscar os alunos:', err);
        }
    }

 
    function imprimir(alunos) {
        const alunosTable = document.querySelector('.alunos-table tbody');
        alunosTable.innerHTML = ''; 

    
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
