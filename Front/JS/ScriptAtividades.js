function cadastrarAtividades() {
 
    const nomeAtividade = document.getElementById('loginButton').value;

    if (!nomeAtividade) {
        alert('Por favor, preencha o campo com o nome da atividade.');
        return;
    }

    fetch('http://localhost:8080/atividades', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({descricao: nomeAtividade})
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
        alert("Atividade cadastrada com sucesso!");
        window.location.href = 'atividades.html';
    })
    .catch(error => {
        console.error('Erro ao cadastrar atividade:', error);
        alert(`Erro ao cadastrar atividade: ${nomeAtividade}`);
    });
}