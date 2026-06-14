window.onload = async function() {

    // Informações que vão aparecer na tela do eBook selecionado
    let titulo = document.getElementById("tituloEbook");
    let preco = document.getElementById("precoEbook");
    let sinopse = document.getElementById("sinopseEbook");
    let autor = document.getElementById("autorEbook");
    let dataPublicacao = document.getElementById("dataPublicacao");

    // Pegar o id do eBook do url da página
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get("id");

    // Requisitar informações do eBook para o backend baseado no id
    const resposta = await fetch(`http://localhost:8080/ebook/${id}`);
    const ebook = await resposta.json();

    // Registrar os dados do eBook na página
    titulo.textContent = ebook.titulo;
    preco.textContent = ebook.preco;
    sinopse.textContent = ebook.sinopse;
    autor.textContent = ebook.autor;
    dataPublicacao.textContent = ebook.data_hora;

}
