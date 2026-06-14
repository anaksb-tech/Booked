import http from 'k6/http';
import {check} from 'k6';
import {sleep} from 'k6';
import {group} from 'k6';

export const options = {

    stages: [
        {duration: '5m', target: 300},
        {duration: '10m', target: 300},
        {duration: '5m', target: 0},
    ],

    thresholds: {
        checks: ['rate>0.99'],
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<1000'],
    },

};

export default function() {

    group('Cadastro Usuário', () => {

        const id = `${__VU}-${__ITER}`;
        const email = `teste${id}@gmail.com`;

        // Verificar se existe um cadastro com o email fornecido
        const respostaValidacao = http.get(`http://localhost:8080/usuario/existe/${encodeURIComponent(email)}`);

        const payload = JSON.stringify({
            nome: 'Teste',
            email: email,
            genero: 'outro',
            senha: 'teste123'
        });

        // Realizar o cadastro no banco
        const respostaCadastro = http.post(
            'http://localhost:8080/usuario/cadastrar-usuario',
            payload,
            {
                headers: {'Content-Type': 'application/json'}
            }
        );

        // Fazer login do usuário
        const respostaLogin = http.get(`http://localhost:8080/usuario/buscar/${encodeURIComponent(email)}`);

        check(respostaValidacao, {
            'status 200 validacao OK': (r) => r.status === 200,
        });

        check(respostaCadastro, {
            'status 200 cadastro OK': (r) => r.status === 200,
        });

        check(respostaLogin, {
            'status 200 login OK': (r) => r.status === 200,
        });

    });

    sleep(1);

}