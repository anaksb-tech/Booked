window.onload = async function() {

    // Buscar ofertas associadas ao usuário
    const idUsuario = localStorage.getItem("idUsuario");
    let resposta = await fetch(`http://localhost:8080/oferta/ofertas-usuario/${idUsuario}`);
    let ofertas = await resposta.json();

    if(ofertas.length === 0) {
        window.alert("Você não possui nenhuma oferta de troca");
    } else {

        // Imprimir anúncios na tela
        ofertas.forEach((oferta, index) => {

            let livro = `Você ofereceu ${oferta.livro.titulo} por ${oferta.anuncio.livro.titulo} (Anunciado por ${oferta.anuncio.usuario.nome})`;
            let lista = document.getElementById("listaOfertas");
            let linha = document.createElement("li");
            linha.textContent = livro;
            lista.appendChild(linha);

        });

    }

}