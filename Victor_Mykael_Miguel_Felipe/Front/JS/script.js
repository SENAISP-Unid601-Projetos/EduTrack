import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://10.110.12.6:8080',
    headers: {
        "Content-Type": "application/json"
    }
});

