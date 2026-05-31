window.onload = carregarEbooks();
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
        link.href = `/html/ebook.html?id=${ebook.id_ebook}`;
        link.textContent = ebook.titulo;
        linha.appendChild(link);
        lista.appendChild(linha);

    });

}

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
