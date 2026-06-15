// Botão Comprar
document.getElementById("botaoComprar").onclick = async function() {

    // Pegar entradas dos inputs
    const numeroCartao = document.getElementById("inputNumeroCartao").value;
    const nomeCartao = document.getElementById("inputNomeCartao").value;
    const codigoCodigoSeguranca = document.getElementById("inputCodigoSeguranca").value;

    // Buscar usuário comprador
    const idUsuario = localStorage.getItem("idUsuario");
    let resposta = await fetch(`http://localhost:8080/usuario/buscar-usuario/${idUsuario}`);
    const usuario = await resposta.json();

    // Pegar o id do eBook do url da página
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get("id");

    // Buscar eBook
    resposta = await fetch(`http://localhost:8080/ebook/${id}`);
    const ebook = await resposta.json();

    // Criar bloco de dados da compra
    const compra = {
        usuario: usuario,
        ebook: ebook,
        valor: ebook.preco,
    }

    console.log(compra);

    // Registrar no banco de dados
    resposta = await fetch("http://localhost:8080/compras/comprar", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(compra)
    });

    if(resposta.ok) {
        window.alert("eBook comprado com sucesso");
        window.location.href = "/html/index.html";
    } else {
        window.alert("Algo deu errado");
    }

}