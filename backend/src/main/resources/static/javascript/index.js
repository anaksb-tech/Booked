window.onload = function() {

    carregarEbooks();

    let header = document.getElementById("bemVindo");
    let botaoCadastrar = document.getElementById("botaoCadastrar");
    let botaoLogin = document.getElementById("botaoLogin");
    let botaoPerfil = document.getElementById("botaoVerPerfil");
    let botaoSair = document.getElementById("botaoSair");

    // Verificar se o usuário está loggado ou não para formatar a tela
    if(localStorage.getItem("nomeUsuario") !== null) {

        let nomeUsuario = localStorage.getItem("nomeUsuario");
        header.textContent = `Bem-vindo ao Booked, ${nomeUsuario}`;
        botaoCadastrar.style.display = "none";
        botaoLogin.style.display = "none";
        botaoPerfil.style.display = "";
        botaoSair.style.display = "";

    } else {

        header.textContent = `Bem-Vindo ao Booked`;
        botaoCadastrar.style.display = "";
        botaoLogin.style.display = "";
        botaoPerfil.style.display = "none";
        botaoSair.style.display = "none";

    }

}

// Botão Ver eBooks
document.getElementById("botaoVerEbooks").onclick = carregarEbooks;

async function carregarEbooks() {

    // Requisita os eBooks do banco de dados
    const resposta = await fetch("http://localhost:8080/ebook/carregar-ebooks");
    const ebooks = await resposta.json();
    document.getElementById("listaProdutos").innerHTML = "";
    document.getElementById("catalogoProdutos").textContent = "Catálogo de eBooks:";

    // Formata os eBooks na página
    ebooks.forEach((ebook, index) => {

        const lista = document.getElementById("listaProdutos");
        const linha = document.createElement("li");
        const link = document.createElement("a");
        link.href = `/html/ebook.html?id=${ebook.id}`;
        link.textContent = ebook.titulo;
        linha.appendChild(link);
        lista.appendChild(linha);

    });

}

// Botão Ver Livros
document.getElementById("botaoVerLivros").onclick = async function() {

    // Requisita os anúncios do banco de dados
    const resposta = await fetch("http://localhost:8080/anuncio/carregar-anuncios");
    const anuncios = await resposta.json();
    document.getElementById("listaProdutos").innerHTML = "";
    document.getElementById("catalogoProdutos").textContent = "Anúncios de Troca de Livro:";

    // Formata os anúncios na página
    anuncios.forEach((anuncio, index) => {

        const lista = document.getElementById("listaProdutos");
        const linha = document.createElement("li");
        const link = document.createElement("a");
        link.href = `/html/visualizarAnuncio.html?id=${anuncio.id_anuncio}`;
        link.textContent = anuncio.livro.titulo;
        linha.appendChild(link);
        lista.appendChild(linha);

    })

}

// Botão Sair
botaoSair.onclick = function() {

    let resposta = confirm("Tem certeza de que quer sair da sua conta?");

    if(resposta) {
        localStorage.clear();
        window.location.href = "/html/index.html";
    }

}

// Botão Ver Perfil
document.getElementById("botaoVerPerfil").onclick = function() {

    window.location.href = "/html/perfil.html";

}

// Botão Publicar eBook
document.getElementById("botaoPublicarEbook").onclick = async function() {

    // Verificar se o usuário está loggado
    const nomeUsuario = localStorage.getItem("nomeUsuario");

    if(nomeUsuario === null) {

        window.alert("Faça login ou cadastre-se para acessar esta funcionalidade");

    } else {

        window.location.href = "/html/publicarEbook.html";

    }

}