document.getElementById("botaoPublicar").onclick = async function() {

    // Pegar valores dos inputs
    const titulo = document.getElementById("inputTitulo").value;
    const sinopse = document.getElementById("inputSinopse").value;
    const autor = document.getElementById("inputAutor").value;
    const preco = document.getElementById("inputPreco").value;

    // Buscar informações do usuário
    const idUsuario = localStorage.getItem("idUsuario");
    const resposta = await fetch(`http://localhost:8080/usuario/buscar-usuario/${idUsuario}`);
    const usuario = await resposta.json();

    // Criar bloco de dados do eBook
    const ebook = {
        usuario: usuario,
        titulo: titulo,
        sinopse: sinopse,
        autor: autor,
        preco: preco
    };

    // Enviar o eBook para o banco de dados
    const respostaPublicacao = await fetch("http://localhost:8080/ebook/publicar-ebook", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(ebook)
    });

    if(respostaPublicacao.ok) {
        window.alert("eBook publicado com sucesso");
        window.location.href="/html/index.html";
    }

}