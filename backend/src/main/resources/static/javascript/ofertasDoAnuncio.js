window.onload = async function() {

    // Pegar o id do anúncio do URL da página
    const parametro = new URLSearchParams(window.location.search);
    const idAnuncio = parametro.get("id");

    // Pegar todas as ofertas feitas em cima deste anúncio
    let resposta = await fetch(`http://localhost:8080/oferta/anuncio/${idAnuncio}`);
    const ofertas = await resposta.json();

    if(ofertas.length === 0) {
        window.alert("Este anúncio não possui ofertas");
    } else {

        ofertas.forEach((oferta, index) => {
            let livro = `${oferta.usuario.nome} ofereceu ${oferta.livro.titulo} --- `;

            let lista = document.getElementById("listaOfertas");
            let linha = document.createElement("li");

            let botaoAceitar = document.createElement("button");
            botaoAceitar.textContent = "Aceitar";

            let botaoRejeitar = document.createElement("button");
            botaoRejeitar.textContent = "Rejeitar";

            linha.textContent = livro;
            linha.appendChild(botaoAceitar);
            linha.appendChild(botaoRejeitar);
            lista.appendChild(linha);
        });

    }

}