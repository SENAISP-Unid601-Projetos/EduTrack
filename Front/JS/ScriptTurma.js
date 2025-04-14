function cadastrar() {
 
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
        body: JSON.stringify({nome: nomeTurma})
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(err => {
                throw new Error(err.message || 'Erro na resposta do servidor');
            });
        }
        return response.text();
    })
    .then(() => {
        alert("Turma cadastrada com sucesso!");
        window.location.href = 'principal.html';
    })
    .catch(error => {
        console.error('Erro ao cadastrar turma:', error);
        alert(`Erro ao cadastrar turma: ${nomeTurma}`);
    });
}