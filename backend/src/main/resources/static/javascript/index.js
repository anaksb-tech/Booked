window.onload = carregarEbooks();

async function carregarEbooks() {

    // Requisita os eBooks do banco de dados
    const resposta = await fetch("http://localhost:8080/ebook/carregar-ebooks");
    const ebooks = await resposta.json();
    //console.log(ebooks);

    // Formata os eBooks na página
    ebooks.forEach((ebook, index) => {

        /*
        const linha = document.createElement("li");
        linha.textContent = ebook.titulo;
        const lista = document.getElementById("listaProdutos");
        lista.appendChild(linha);
         */

        const lista = document.getElementById("listaProdutos");
        const linha = document.createElement("li");
        const link = document.createElement("a");
        link.href = `/html/ebook.html?id=${ebook.id_ebook}`;
        link.textContent = ebook.titulo;
        linha.appendChild(link);
        lista.appendChild(linha);

    });

}