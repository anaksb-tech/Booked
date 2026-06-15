// Botão publicar anúncio
document.getElementById("botaoPublicar").onclick = async function() {

    // Pegar dados do formulário
    const titulo = document.getElementById("inputTitulo").value;
    const sinopse = document.getElementById("inputSinopse").value;
    const autor = document.getElementById("inputAutor").value;
    const descricao = document.getElementById("inputDescricao").value;

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

    // Registrar anúncio no banco de dados
    const anuncio = {
        usuario: usuario,
        livro: livro,
        descricao: descricao,
        foto: "undefined"
    };

    resposta = await fetch("http://localhost:8080/anuncio/publicar", {

        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(anuncio)

    });

    if(resposta.ok) {
        window.alert("Anúncio publicado com sucesso");
        window.location.href = "/html/index.html";
    } else {
        window.alert("Algo deu errado");
    }

}