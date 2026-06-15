window.onload = async function() {

    // Buscar anúncios associadas ao usuário
    const idUsuario = localStorage.getItem("idUsuario");
    let resposta = await fetch(`http://localhost:8080/anuncio/meus-anuncios/${idUsuario}`);
    let anuncios = await resposta.json();

    if(anuncios.length === 0) {
        window.alert("Você não possui nenhum anúncio de troca");
    } else {

        // Imprimir anúncios na tela
        anuncios.forEach((anuncio, index) => {

            let livro = `${anuncio.livro.titulo} --- `;

            let botao = document.createElement("button");
            botao.textContent = "Ver Ofertas";

            let link = document.createElement("a");
            link.href = `/html/ofertasDoAnuncio.html?id=${anuncio.id_anuncio}`;
            link.appendChild(botao);

            let lista = document.getElementById("listaAnuncios");
            let linha = document.createElement("li");
            linha.textContent = livro;
            linha.appendChild(link);
            lista.appendChild(linha);

        });

    }

}