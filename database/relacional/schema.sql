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
