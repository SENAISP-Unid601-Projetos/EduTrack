const URL = 'http://localhost:8080/professor';

async function login() {
    const emailInput = document.querySelector('#emailLogin');
    const passwordInput = document.querySelector('#passwordLogin');

    if (!(emailInput instanceof HTMLElement) || !(passwordInput instanceof HTMLElement)) {
        alert('Erro ao fazer requisição');
        return;
    }
    
    const email = emailInput.value;
    const password = passwordInput.value;
    const data = { email: email, senha: password };
    
    try {
        const response = await fetch(`${URL}/login`, {
            method: 'POST',
            headers: { 
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        });
    
        if (response.ok) {
            const responseData = await response.json();
            console.log(responseData);
            window.location.href = 'html/principal.html';
        } else {
            const errorText = await response.text();
            alert("Erro: " + errorText);
            console.error("Erro do servidor:", errorText);
        }
    } catch (error) {
        alert("Erro de rede ou credenciais inválidas.");
        console.error("Erro na requisição:", error);
    }

    
}