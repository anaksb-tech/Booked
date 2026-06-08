<h2>Nome do Serviço: Carregar eBooks na Página Inicial</h2>


Tipo de operação: Leitura



Arquivos envolvidos:
<ul>
  <li>Ebook.java</li>
  <li>EbookController.java</li>
  <li>EbookRepository.java</li>
</ul>

Arquivos com o código fonte de medição do SLA:
<ul>
  <li>teste-cadastro.js</li>
</ul>

Data de medição: 07/06/2026

Descrição das configurações:
<ul>
  <li>Ambiente da aplicação: Projeto Java (v21) Spring Boot (v4.0.6) utilizando servidor Tomcat sendo rodado localmente na porta 8080</li>
  <li>Persistência: Banco de dados local MySQL (v8.0.46) utilizando JPA/Hibernate (v7.2.12) e pool de conexões HikariCP</li>
  <li>Ambiente de teste: Sistema operacional Linux Mint 22.1, testes realizados localmente pelo terminal, utilizando Grafana k6</li>
</ul>

Testes de carga (SLA):
<ul>
  <li>Latência (tempo de resposta médio - p95): 773,48 ms</li>
  <li>Vazão: 1553 req/s (Total de 1864843 requisições realizadas)</li>
  <li>Concorrência: Até 3000 usuários (VUs) simulados simultaneamente</li>
  <li>Taxa de erros: 0,00% (Sucesso total em todas as respostas)</li>
</ul>

<img width="1758" height="749" alt="image" src="https://github.com/user-attachments/assets/8c442462-9aed-4f5c-8cc9-b1c4b02d3b04" />
<img width="939" height="976" alt="image" src="https://github.com/user-attachments/assets/0e14cbbe-b74f-4c78-bea3-62dc20bc31e1" />

<br>
<br>

Levantamento de Hipoteses:
<ol>
  <li>
    Tráfego desnecessáro de dados: Há um volume excessivo de dados sendo transportados do MySQL para o frontend, pois para cada eBook registrado no banco de dados, é feita a solicitação do objeto eBook por inteiro, sendo que são utilizados apenas dois de seus atributos para a exibição na página inicial (título e id).
  </li>
  <br>
  <li>
    Número de conexões HikariCP: Com o aumento linear da latência das respostas, porém nenhum erro ocorrendo ao decorrer do teste, há a possibilidade do tamanho máximo da pool de conexões do HikariCP com o banco de dados ser insuficiente para a carga simulada no teste, assim limitando a performance do sistema.
  </li>
  <br>
  <li>
    Threads do servidor Tomcat: Similarmente ao item anterior, também é possível que a quantidade de threads disponíveis ao servidor Tomcat seja insuficiente para a carga do teste, assim causando um aumento gradual na latência das respostas, porém mantendo a estabilidade do sistema; um comportamento normalmente associado à filas de requisições esperando serem atendidas.
  </li>
</ol>


