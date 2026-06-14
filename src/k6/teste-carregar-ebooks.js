import http from 'k6/http';
import {check} from 'k6';
import {sleep} from 'k6';

export const options = {

    stages: [
        {duration: '5m', target: 3000},
        {duration: '10m', target: 3000},
        {duration: '5m', target: 0},
    ],

    thresholds: {
        checks: ['rate>0.99'],
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<1000'],
    },

};

export default function() {

    const resposta = http.get('http://localhost:8080/ebook/carregar-ebooks');

    check(resposta, {
        'status 200 OK': (r) => r.status === 200,
    });

    sleep(1);

}