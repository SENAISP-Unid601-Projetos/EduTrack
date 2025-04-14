function cadastrarPaciente(event) {
    event.preventDefault();
 
    const nomeTurma = document.getElementById('emailLogin').value;

    if (!nomeTurma) {
        alert('Por favor, preencha o campo com o nome da turma.');
        return;
    }

    fetch('http://localhost:8080/turmas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nomeTurma })
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(err => {
                throw new Error(err.message || 'Erro na resposta do servidor');
            });
        }
        return response.json();
    })
    .then(() => {
        alert("Turma cadastrada com sucesso!");
    })
    .catch(error => {
        console.error('Erro ao cadastrar turma:', error);
        alert(`Erro ao cadastrar turma: ${error.message || 'Erro desconhecido.'}`);
    });
}
