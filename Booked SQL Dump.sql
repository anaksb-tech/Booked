create table usuario (
	id_usuario int not null,
	nome varchar(50) not null,
	email varchar(100) not null,
	genero varchar(10) not null,
	senha varchar(50) not null,
	ranque int not null,
	primary key (id_usuario),
	unique (email)
);


create table amizade (
	id_amizade int not null,
	id_usuario1 int not null,
	id_usuario2 int not null,
	primary key (id_amizade),
	foreign key (id_usuario1) references usuario (id_usuario),
	foreign key (id_usuario2) references usuario (id_usuario),
	unique (id_usuario1, id_usuario2)
);


create table ebook (
	id_ebook int not null,
	id_usuario int not null,
	titulo varchar(50) not null,
	sinopse varchar(300) not null,
	autor varchar (50) not null,
	preco decimal not null,
	data_hora datetime not null,
	primary key (id_ebook),
	foreign key (id_usuario) references usuario (id_usuario)
);


create table compra (
	id_compra int not null,
	id_usuario int not null,
	id_ebook int not null,
	valor decimal not null,
	data_hora datetime not null,
	primary key (id_compra),
	foreign key (id_usuario) references usuario (id_usuario),
	foreign key (id_ebook) references ebook (id_ebook),
	unique (id_usuario, id_ebook)
);


create table endereco (
	id_endereco int not null,
	id_usuario int not null,
	cidade varchar(50) not null,
	bairro varchar(50) not null,
	rua varchar(50) not null,
	numero int not null,
	primary key (id_endereco),
	foreign key (id_usuario) references usuario (id_usuario),
	unique (id_usuario, cidade, bairro, rua, numero)
);


create table genero (
	id_genero int not null,
	tipo varchar(20) not null,
	primary key (id_genero)
);


create table livro (
	id_livro int not null,
	titulo varchar(50) not null,
	descricao varchar(300) not null,
	autor varchar(50) not null,
	primary key (id_livro)
);


create table anuncio (
	id_anuncio int not null,
	id_usuario int not null,
	id_livro int not null,
	descricao varchar(300) not null,
	data_hora datetime not null,
	foto varchar(100) not null,
	primary key (id_anuncio),
	foreign key (id_usuario) references usuario (id_usuario),
	foreign key (id_livro) references livro (id_livro)
);


create table genero_ebook (
	id_genero_ebook int not null,
	id_genero int not null,
	id_ebook int not null,
	primary key (id_genero_ebook),
	foreign key (id_genero) references genero (id_genero),
	foreign key (id_ebook) references ebook (id_ebook),
	unique (id_genero, id_ebook)
);


create table genero_livro (
	id_genero_livro int not null,
	id_genero int not null,
	id_livro int not null,
	primary key (id_genero_livro),
	foreign key (id_genero) references genero (id_genero),
	foreign key (id_livro) references livro (id_livro),
	unique (id_genero, id_livro)
);


create table moderador (
	id_moderador int not null,
	nome varchar(50) not null,
	email varchar(100) not null,
	senha varchar(50) not null,
	genero varchar(10) not null ,
	primary key (id_moderador),
	unique (email)
);


create table denuncia (
	id_denuncia int not null,
	id_usuario int not null,
	id_anuncio int,
	id_ebook int,
	id_moderador int not null,
	motivo varchar(100) not null,
	data_hora datetime not null,
	primary key (id_denuncia),
	foreign key (id_usuario) references usuario (id_usuario),
	foreign key (id_anuncio) references anuncio (id_anuncio),
	foreign key (id_ebook) references ebook (id_ebook),
	foreign key (id_moderador) references moderador (id_moderador)
);


create table oferta (
	id_oferta int not null,
	id_usuario int not null,
	id_anuncio int not null,
	id_livro int not null,
	foto varchar(100) not null,
	data_hora datetime not null,
	primary key (id_oferta),
	foreign key (id_usuario) references usuario (id_usuario),
	foreign key (id_anuncio) references anuncio (id_anuncio),
	foreign key (id_livro) references livro (id_livro)
);


create table suspensao_ebook (
	id_suspensao_ebook int not null,
	id_ebook int not null,
	id_moderador int not null,
	motivo varchar(100) not null,
	data_hora datetime not null,
	primary key (id_suspensao_ebook),
	foreign key (id_ebook) references ebook (id_ebook),
	foreign key (id_moderador) references moderador (id_moderador)
);


create table suspensao_usuario (
	id_suspensao_usuario int not null,
	id_usuario int not null,
	id_moderador int not null,
	motivo varchar(100) not null,
	data_hora datetime not null,
	primary key (id_suspensao_usuario),
	foreign key (id_usuario) references usuario (id_usuario),
	foreign key (id_moderador) references moderador (id_moderador)
);


create table lista_desejos_ebook (
	id_lista_ebook int not null,
	id_usuario int not null,
	id_ebook int not null,
	primary key (id_lista_ebook),
	foreign key (id_usuario) references usuario (id_usuario),
	foreign key (id_ebook) references ebook (id_ebook),
	unique (id_usuario, id_ebook)
);


create table lista_desejos_livro (
	id_lista_livro int not null,
	id_usuario int not null,
	id_livro int not null,
	primary key (id_lista_livro),
	foreign key (id_usuario) references usuario (id_usuario),
	foreign key (id_livro) references livro (id_livro),
	unique (id_usuario, id_livro)
);

insert into usuario values
(1,'Erick Gomes Mattos','erickgm@edu.unirio.br','outro','123456',9),
(2,'Jorge','jorge@edu.unirio.br','masc','12321343',8),
(3,'Marcio','marcio@edu.unirio.br','masc','43343432',8),
(4,'Maria','maria@edu.unirio.br','fem','5545425',5),
(5,'Marco','marco@edu.unirio.br','masc','324324',10),
(6,'Bento','bento@gmail.com','masc','bento1234',7),
(7,'Luciana','luciana@hotmail.com','fem','123luci321',6);


insert into amizade values
(21,1,2),
(22,2,3),
(23,3,4),
(24,4,5),
(25,5,1),
(26,1,4),
(27,5,2),
(28,6,7);


insert into endereco values
(11,1,'São Paulo','Tijuca','Missisipe',204),
(12,2,'São Paulo','Paulista','Ramos',122),
(13,3,'Belo Horizonte','Campinas','Peixoto',14),
(14,4,'Rio de Janeiro','Rio de Janeiro','Botafogo',302),
(15,5,'Rio de Janeiro','São Cristovão','Pedro',74),
(16,6,'Belo Horizonte','Lourdes','Contorno',17),
(17,7,'Rio de Janeiro','Botafogo','São Clemente',52);


insert into ebook values
(71,1,'O doutor da máfia','Riccardo Ferraro é o cirurgião que assume os casos que ninguém mais ousa tocar: os impossíveis, os arriscados, aqueles que exigem sangue frio e inteligência rápida.','Ary Nascimento',1.99,'2025-07-25 12:40:52'),
(72,2,'O Marido que me Renegou','Apollo Vasilakis. Advogado. Introspectivo. Sombrio. Letal. Membro da máfia grega Ta Korákia. Um grego obcecado pela esposa cujo coração ele partiu.','D. A. Lemoyne',2,'2025-02-12 00:21:50'),
(73,3,'Heartstopper','Charlie Spring e Nick Nelson não têm quase nada em comum. Charlie é um aluno dedicado e bastante inseguro por conta do bullying que sofre no colégio desde que se assumiu gay. Já Nick é superpopular, especialmente querido por ser um ótimo jogador de rúgbi.','Alice Oseman',19.99,'2026-01-08 20:09:22'),
(74,4,'Jogo de Devoção','Ela acreditava que a fé a salvaria. Ele transformou a sua devoção em um jogo. Aurora De Angelis cresceu acreditando que seu único propósito era entregar a vida a Deus. Religiosa, ingênua, criada para seguir regras, nunca para questioná-las. Mikhail Volkov acreditava que tinha o controle de tudo.','Dani Nascimento',1.5,'2026-03-19 21:13:56'),
(75,5,'Poeira, pólvora e fome','No Velho Oeste, não há lugar para mulheres que não sabem obedecer. Cassidy Boone vive à margem da lei e de todas as regras. É rápida com o revólver, implacável quando precisa e completamente incapaz de se permitir sentir.','Mónica Benítez',10.99,'2024-12-27 14:28:01'),
(76,6,'O Livro do Desassossego','Composta por anotações, excertos e textos soltos, a obra parece ilustrar a própria fragmentação do sujeito e contém grandes questionamentos sobre o indivíduo, a sua existência e o mundo que o rodeia.','Fernando Pessoa',35.99,'2025-08-23 15:27:32'),
(77,7,'Pantagruel','Com dois gigantes como personagens principais (pai e filho), a narrativa se caracteriza pelo tom cômico e satírico, recorrendo a inúmeros trocadilhos de duplo sentido e ao humor escatológico e macabro.','François Rabelais',25.50,'2026-03-11 17:22:54');


insert into livro values
(61,'Ao Farol','Ao Farol, lançado em maio de 1927, é considerado um dos livros mais importantes do movimento modernista e também um dos melhores romances de sempre. A narrativa segue a família Ramsay e os verões passados na sua casa de praia, na ilha de Skye.','Virginia Woolf'),
(62,'Memórias Póstumas de Brás Cubas','Aqui acompanhamos Brás Cubas, um sujeito vindo de uma família rica que leva uma vida bastante controversa, com opiniões e comportamentos pouco nobres.','Machado de Assis'),
(63,'Cem Anos de Solidão','A narrativa é passada na aldeia fictícia de Macondo e segue várias gerações da família Buendía–Iguarána, explorando temas como a memória e o esquecimento.','Gabriel García Márquez'),
(64,'Moby Dick','Passado no mar, o livro conta a história de um navio baleeiro que persegue e ataca várias vezes um cachalote sem conseguir matá-lo. Com descrições sobre a caça de baleias e a obsessão pelo animal, a obra também problematiza temas como a divisão de classes e até a existência de Deus','Herman Melville'),
(65,'A Divina Comédia','O poema renascentista, de inspiração épica e temática religiosa, está dividido em três partes, que correspondem aos três espaços do pós-vida: Inferno, Purgatório e Paraíso. O protagonista da narrativa é o próprio Dante.','Dante Alighieri'),
(66,'A casa dos espíritos','É uma narrativa envolta em realismo fantástico que nos faz compreender parte da história de seu país. É acompanhando os integrantes da família Trueba que mergulhamos em três gerações que precisam lidar com os conflitos, amores, ideologias políticas, traições, lutos e tudo o que a vida reserva.','Isabel Allende'),
(67,'O Rei Lear','O Rei Lear é uma tragédia inspirada no folclore britânico que conta a história de um rei que foi traído por duas de suas filhas, motivadas pela ganância. Pensando questões intemporais como as relações familiares, a loucura e o poder, a obra se tornou uma enorme influência na história da literatura.','William Shakespeare');


insert into genero values
(51,'drama'),
(52,'fantasia'),
(53,'sci-fi'),
(54,'horror'),
(55,'lgbt+'),
(56,'romance'),
(57,'suspense'),
(58,'comédia');


insert into genero_ebook values
(754,51,74),
(755,51,75),
(753,55,73),
(751,56,71),
(752,56,72),
(756,56,73),
(757,51,76),
(758,52,77),
(759,56,77),
(760,58,77);


insert into genero_livro values
(651,51,61),
(652,52,62),
(654,52,64),
(656,56,61),
(657,56,62),
(653,56,63),
(655,58,65),
(658,56,66),
(659,51,67);


insert into lista_desejos_ebook values
(-75,5,75),
(-74,4,74),
(-73,3,73),
(-72,2,72),
(-71,1,71),
(-76,5,71),
(-77,2,75);


insert into lista_desejos_livro values
(-65,5,65),
(-64,4,64),
(-63,3,63),
(-62,2,62),
(-61,1,61),
(-66,3,64),
(-67,1,62);


insert into moderador values
(-5,'Alberto','alberto@gmail.com','1234','masc'),
(-4,'Nicole','nicole@gmail.com','abobora912','fem'),
(-3,'Wilson','wilson@gmail.com','01928382102','masc'),
(-2,'Débora','debora@gmail.com','123senha321','fem'),
(-1,'Reginaldo','reginaldo@gmail.com','4321','masc'),
(-6,'Michael','michael@bol.com.br','19181716','masc'),
(-7,'William','william@hotmail.com','192010354','masc');


insert into anuncio values
(91,1,61,'Gostaria de trocar esse livro por algum de ação ou suspense','2026-03-20 21:32:08','https://booked.com/troca91.jpg'),
(92,2,62,'Queria um livro desse mesmo autor se alguém tiver','2026-02-14 00:12:52','https://booked.com/troca92.jpg'),
(93,3,63,'Se alguém tiver livros de sci-fi, aceitaria','2026-05-02 07:29:33','https://booked.com/troca93.jpg'),
(94,4,64,'Só aceito livros de romance por favor. De preferência não muito longos','2025-11-13 10:17:52','https://booked.com/troca94.jpg'),
(95,5,65,'Aceito qualquer livro desde que seja bem avaliado','2024-06-29 12:58:19','https://booked.com/troca95.jpg'),
(96,6,66,'Quero livros de comédia bem mantidos por favor','2026-04-27 13:30:28','https://booked.com/troca96.jpg'),
(97,7,67,'Gosto de literaturas mais antigas, pode ser drama ou romance','2026-05-04 16:23:14','https://booked.com/troca97.jpg');


insert into compra values
(81,1,71,1.99,'2025-11-10'),
(82,2,72,2,'2026-02-09'),
(83,3,73,19.99,'2026-01-11'),
(84,4,74,1.5,'2025-12-04'),
(85,5,75,10.99,'2026-04-01'),
(86,6,76,29,'2026-05-09 20:24:00'),
(87,7,77,13,'2026-05-11 14:11:35');


insert into denuncia values
(101,1,NULL,71,-1,'conteudo NSFW.','2026-01-08 21:19:22'),
(102,2,92,NULL,-2,'é conteudo SCAMoso.','2025-04-12 13:45:21'),
(103,3,NULL,73,-3,'conteudo de tom ofensivo.','2026-04-05 22:39:31'),
(104,4,94,NULL,-4,'não existe de vdd esse produto.','2026-01-11 08:42:00'),
(105,5,95,NULL,-5,'a postagem não parece bem intencionada.','2025-11-11 09:29:00'),
(106,6,95,NULL,-6,'a postagem apresenta más intenções','2025-11-13 13:20:32'),
(107,3,NULL,75,-7,'não gostei do conteúdo','2026-03-25 19:53:26');


insert into oferta values
(991,1,91,61,'https://booked.com/oferta991.jpg','2026-05-01 15:23:07'),
(992,2,92,62,'https://booked.com/oferta992.jpg','2026-04-30 17:03:34'),
(993,3,93,63,'https://booked.com/oferta993.jpg','2026-05-02 19:43:00'),
(994,4,94,64,'https://booked.com/oferta994.jpg','2026-04-29 23:56:30'),
(995,5,95,65,'https://booked.com/oferta995.jpg','2026-05-04 08:19:41'),
(996,6,95,66,'https://booked.com/oferta996.jpg','2026-05-02 20:47:21'),
(997,7,96,67,'https://booked.com/oferta997.jpg','2026-05-10 00:43:39');


insert into suspensao_ebook values
(771,71,-1,'Contém conteúdos inapropriados para a plataforma','2026-03-30 22:32:19'),
(772,72,-2,'Contém conteúdos inapropriados para a plataforma','2025-12-22 19:21:54'),
(773,73,-3,'Informações sobre o eBook eram enganosas','2026-05-04 11:48:29'),
(774,74,-4,'Contém conteúdos inapropriados para a plataforma','2026-02-27 09:02:37'),
(775,75,-5,'O arquivo publicado é uma cópia não oficial da obra','2025-11-14 02:59:02'),
(776, 76, -6, 'Contém conteúdos inapropriados para a plataforma','2026-04-23 23:34:45'),
(778, 75, -7, 'O arquivo publicado é uma cópia não oficial da obra', '2025-11-16 12:51:41');


insert into suspensao_usuario values
(771,1,-1,'Não pode conteúdo explícito na plataforma.','2026-09-11 10:34:10'),
(772,2,-2,'Não pode apologia a racismo.','2026-02-02 23:16:09'),
(773,3,-3,'Não pode mandar foto de arma do google imagens ameaçando o outro.','2026-03-07 12:59:21'),
(774,4,-4,'Xingar outro usuário desconhecido é difamação.','2026-11-10 09:51:41'),
(775,5,-5,'Tentar extorquir outro usuário é errado.','2025-09-11 17:45:52'),
(776,3,-6,'Não é permitido comportamento agressivo na plataforma.','2026-05-06 11:23:58'),
(777,7,-3,'Não pode comportamento inapropriado na plataforma.','2026-04-27 21:25:08');

