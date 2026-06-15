// Botão Fazer Oferta
document.getElementById("botaoFazerOferta").onclick = async function() {

    // Pegar dados do formulário
    const titulo = document.getElementById("inputTitulo").value;
    const sinopse = document.getElementById("inputSinopse").value;
    const autor = document.getElementById("inputAutor").value;

    // Registrar o livro no banco de dados
    let livro = {
        titulo: titulo,
        descricao: sinopse,
        autor: autor
    };
    let resposta = await fetch("http://localhost:8080/livro/registrar", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(livro)
    });
    livro = await resposta.json();

    // Buscar usuário no banco
    const idUsuario = localStorage.getItem("idUsuario");
    resposta = await fetch(`http://localhost:8080/usuario/buscar-usuario/${idUsuario}`);
    const usuario = await resposta.json();

    // Pegar o id do Anúncio do url da página
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get("id");

    // Buscar anúncio no banco
    resposta = await fetch(`http://localhost:8080/anuncio/${id}`);
    const anuncio = await resposta.json();

    // Registrar oferta no banco de dados
    const oferta = {
        usuario: usuario,
        anuncio: anuncio,
        livro: livro,
        foto: "undefined"
    };
    resposta = await fetch(`http://localhost:8080/oferta/registrar`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(oferta)
    });

    if(resposta.ok) {
        window.alert("Oferta publicada com sucesso");
        window.location.href = "/html/index.html";
    } else {
        window.alert("Algo deu errado");
    }

}