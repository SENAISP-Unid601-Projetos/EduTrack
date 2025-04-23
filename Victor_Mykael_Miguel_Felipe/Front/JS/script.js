import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://10.110.12.6:8080',
    headers: {
        "Content-Type": "application/json"
    }
});

async function postTurma(sigla, nome, termo) {
    try {
        const data = { sigla, nome, termo };
        const response = await instance.post('/turmas', data);
        if (response.status === 200) {
            return response.data;
        }
    } catch (err) {
        console.error("error", err);
        throw err;
    }
}