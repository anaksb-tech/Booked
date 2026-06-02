import http from 'k6/http';

export const options = {
    vus: 200,
    duration: '30s',
};

export default function() {

    const id = `${__VU}-${__ITER}`;

    const payload = JSON.stringify({
        nome: 'Teste',
        email: `teste${id}@gmail.com`,
        genero: 'outro',
        senha: 'teste123'
    });

    http.post(
        'http://localhost:8080/usuario/cadastrar-usuario',
        payload,
        {
            headers: {'Content-Type': 'application/json'}
        }
    );

}