window.onload = async function() {

    // Buscar compras associadas ao usuário
    const idUsuario = localStorage.getItem("idUsuario");
    let resposta = await fetch(`http://localhost:8080/compras/usuario/${idUsuario}`);
    let compras = await resposta.json();

    if(compras.length === 0) {
        window.alert("Você não possui nenhum eBook");
    } else {

        // Imprimir eBooks na tela
        compras.forEach((compra, index) => {

            let titulo = compra.ebook.titulo;
            let lista = document.getElementById("listaEbooks");
            let linha = document.createElement("li");
            linha.textContent = titulo;
            lista.appendChild(linha);

        });

    }

}