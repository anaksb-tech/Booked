window.onload = async function() {

    // Informações do usuário que vão aparecer na tela
    const nome = document.getElementById("nome");
    const email = document.getElementById("email");
    const genero = document.getElementById("genero");

    // Buscar as informações do usuário loggado
    const idUsuario = localStorage.getItem("idUsuario");
    const resposta = await fetch(`http://localhost:8080/usuario/buscar/id/${idUsuario}`);
    const usuario = await resposta.json();

    // Printar na tela
    nome.textContent = localStorage.getItem("nomeUsuario");
    email.textContent = usuario.email;

    if(usuario.genero === "masc") {
        genero.textContent = "Masculino";
    } else if(usuario.genero === "fem") {
        genero.textContent = "Feminino";
    } else {
        genero.textContent = "Outro";
    }

}

// Botao biblioteca
document.getElementById("botaoBiblioteca").onclick = function() {
    window.location.href = "/html/bibliotecaEbook.html";
}