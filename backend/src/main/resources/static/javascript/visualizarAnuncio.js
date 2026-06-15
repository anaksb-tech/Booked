window.onload = async function() {

    // Informações que vão aparecer na tela do Anúncio selecionado
    let titulo = document.getElementById("tituloLivro");
    let sinopse = document.getElementById("sinopseLivro");
    let autor = document.getElementById("autorLivro");
    let anunciador = document.getElementById("anunciador");
    let descricao = document.getElementById("descricaoAnuncio");

    // Pegar o id do Anúncio do url da página
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get("id");

    // Requisitar informações do Anúncio para o backend baseado no id
    const resposta = await fetch(`http://localhost:8080/anuncio/${id}`);
    const anuncio = await resposta.json();

    // Registrar os dados do Anúncio na página
    titulo.textContent = anuncio.livro.titulo;
    sinopse.textContent = anuncio.livro.descricao;
    autor.textContent = anuncio.livro.autor;
    anunciador.textContent = anuncio.usuario.nome;
    descricao.textContent = anuncio.descricao;

}

// Botão Fazer Oferta
document.getElementById("botaoFazerOferta").onclick = async function() {

    // Verificar se o usuário está loggado
    const nomeUsuario = localStorage.getItem("nomeUsuario");

    if(nomeUsuario === null) {
        window.location.href = "/html/login.html";
    } else {

        // Pegar o id do Anúncio do url da página
        const parametro = new URLSearchParams(window.location.search);
        const id = parametro.get("id");
        window.location.href = `/html/fazerOferta.html?id=${id}`;

    }

}