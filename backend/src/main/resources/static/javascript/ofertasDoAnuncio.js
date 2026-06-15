window.onload = async function() {

    // Pegar o id do anúncio do URL da página
    const parametro = new URLSearchParams(window.location.search);
    const idAnuncio = parametro.get("id");

    // Pegar todas as ofertas feitas em cima deste anúncio
    let resposta = await fetch(`http://localhost:8080`);

}