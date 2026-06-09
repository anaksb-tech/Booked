<h2>Nome do Serviço: Carregar eBooks na Página Inicial</h2>


Tipo de operação: Leitura



Arquivos envolvidos:
<ul>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/java/com/booked/backend/entity/Ebook.java">Ebook.java</a>
  </li>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/java/com/booked/backend/controller/EbookController.java">EbookController.java</a>
  </li>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/java/com/booked/backend/repository/EbookRepository.java">EbookRepository.java</a>
  </li>
</ul>

Arquivos com o código fonte de medição do SLA:
<ul>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/k6/teste-carregar-ebooks.js">teste-carregar-ebooks.js</a>
  </li>
</ul>

<h3>Medição 1</h3>

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


<h3>Medição 2</h3>

Data da medição: 08/06/2026

Testes de carga (SLA):
<ul>
  <li>Latência (tempo de resposta médio - p95): 7,37 ms</li>
  <li>Vazão: 2242 req/s (Total de 2692060 requisições realizadas)</li>
  <li>Concorrência: Até 3000 usuários (VUs) simulados simultaneamente</li>
  <li>Taxa de erros: 0,00% (Sucesso total em todas as respostas)</li>
</ul>

<img width="1781" height="764" alt="image" src="https://github.com/user-attachments/assets/c44b300c-32eb-4a0b-ab2f-acbe61133be0" />
<img width="946" height="913" alt="image" src="https://github.com/user-attachments/assets/71e002f6-6629-4883-9a01-5b046ac7aa97" />

<br>
<br>

Melhorias/otimizações:
<ul>
  <li>
    O tráfego de dados entre o frontend e o banco de dados foi alterado para que apenas os dados necessários dos eBooks (título e id) sejam enviados na requisição. Isso é feito através do uso de um Data Transfer Object baseado no eBook criado com apenas os atributos necessários para esta requisição.
  </li>
  <li>
    O número de conexões do pool do HikariCP foi expandido para 50, permitindo que mais requisições ocorram sem gerar filas de espera devido à falta de conexões disponíveis.
  </li>
  <li>
    Também foi expandido o número de threads do servidor Tomcat, chegando agora em um total de 300 threads simultâneas, colaborando para a minimização de filas de requisições.
  </li>
</ul>

Arquivos modificados:
<ul>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/java/com/booked/backend/controller/EbookController.java">EbookController.java</a>
  </li>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/resources/static/javascript/index.js">index.js</a>
  </li>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/resources/application.properties">application.properties</a>
  </li>
</ul>

<br>

<h2>Nome do Serviço: Fazer Cadastro de Usuário</h2>

Tipo de operação: Inserção e Leitura

Arquivos envolvidos:
<ul>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/java/com/booked/backend/entity/Usuario.java">Usuario.java</a>
  </li>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/java/com/booked/backend/controller/UsuarioController.java">UsuarioController.java</a>
  </li>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/java/com/booked/backend/repository/UsuarioRepository.java">UsuarioRepository.java</a>
  </li>
</ul>

Arquivos com o código fonte de medição do SLA:
<ul>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/k6/teste-cadastro.js">teste-cadastro.js</a>
  </li>
</ul>

<h3>Medição 1</h3>

Data de medição: 07/06/2026

Descrição das configurações:
<ul>
  <li>Ambiente da aplicação: Projeto Java (v21) Spring Boot (v4.0.6) utilizando servidor Tomcat sendo rodado localmente na porta 8080</li>
  <li>Persistência: Banco de dados local MySQL (v8.0.46) utilizando JPA/Hibernate (v7.2.12) e pool de conexões HikariCP</li>
  <li>Ambiente de teste: Sistema operacional Linux Mint 22.1, testes realizados localmente pelo terminal, utilizando Grafana k6</li>
</ul>

Testes de carga (SLA):
<ul>
  <li>Latência (tempo de resposta médio - p95): 3.32 ms</li>
  <li>Vazão: 668 req/s (Total de  requisições realizadas)</li>
  <li>Concorrência: Até 300 usuários (VUs) simulados simultaneamente</li>
  <li>Taxa de erros: 0,00% (Sucesso total em todas as respostas)</li>
</ul>

<img width="1783" height="772" alt="image" src="https://github.com/user-attachments/assets/c666b25a-ee0f-452b-bed5-de7946c2e4ff" />
<img width="950" height="740" alt="image" src="https://github.com/user-attachments/assets/3d5ff260-89af-4575-89e8-581f4e746bbc" />

<br>
<br>

Levantamento de hipoteses:
<ol>
  <li>
    Requisição de dados desnecessários: Após realizar o cadastro do usuário, o sistema requisita ao banco de dados todas as informações do usuário para automaticamente fazer login no site, sendo que para ativar o login, o frontend precisa apenas do nome e id do usuário; porém o frontend já possui o nome do usuário, então apenas o id precisa ser requisitado. 
  </li>
  <br>
  <li>
    Pools de conexão HikariCP: Com uma carga elevada de VUs, pode ocorrer que o número de conexões disponíveis ao HikariCP seja insuficiente, causando aumento gradual na latência das respostas do sistema, devido à criação de filas para o atendimento de requisições.
  </li>
</ol>


<h3>Medição 2</h3>

Data da medição: 08/06/2026

Testes de carga (SLA):
<ul>
  <li>Latência (tempo de resposta médio - p95): 8,12 ms</li>
  <li>Vazão: 669 req/s (Total de 803847 requisições realizadas)</li>
  <li>Concorrência: Até 300 usuários (VUs) simulados simultaneamente</li>
  <li>Taxa de erros: 0,00% (Sucesso total em todas as respostas)</li>
</ul>

<img width="1778" height="765" alt="image" src="https://github.com/user-attachments/assets/64f35bd3-7732-4082-bc99-4a1a5518c019" />
<img width="902" height="677" alt="image" src="https://github.com/user-attachments/assets/1dd93736-6690-484c-889f-9208ab9e3323" />

<br>
<br>

Melhorias/otimizações:
<ul>
  <li>
    A comunicação com o banco de dados na hora de fazer o login automático após o cadastro foi otimizada. Onde antes era requisitado um objeto Usuário inteiro para fazer login, agora é requisitado apenas o id do usuário, dado que o frontend já possui o nome fornecido no cadastro, e o único outro dado necessário é o id.
  </li>
  <li>
    O número de conexões do pool do HikariCP foi expandido para 50, permitindo que mais requisições ocorram sem gerar filas de espera devido à falta de conexões disponíveis.
  </li>
  <li>
    Também foi expandido o número de threads do servidor Tomcat, chegando agora em um total de 300 threads simultâneas, colaborando para a minimização de filas de requisições.
  </li>
</ul>

Arquivos modificados:
<ul>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/java/com/booked/backend/controller/UsuarioController.java">UsuarioController.java</a>
  </li>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/resources/static/javascript/cadastrar.js">cadastrar.js</a>
  </li>
  <li>
    <a href="https://github.com/anaksb-tech/Booked/blob/main/backend/src/main/resources/application.properties">application.properties</a>
  </li>
</ul>
