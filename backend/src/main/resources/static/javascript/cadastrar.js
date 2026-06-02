document.getElementById("confirmarCadastro").onclick = async function() {

    // Pega os dados do formulário
    let nome = document.getElementById("inputNome").value;
    let genero = document.getElementById("inputGenero").value;
    let email = document.getElementById("inputEmail").value;
    let senha = document.getElementById("inputSenha").value;

    // Valida os dados do formulário
    if(nome === "") {

    } else if(genero === "") {

    } else if(email === "") {

    } else if(senha === "") {

    }

    // Cria o bloco de dados do usuário
    let usuario = {
        nome: nome,
        genero: genero,
        email: email,
        senha: senha
    };

    // Envia o usuário pro backend
    await fetch("http://localhost:8080/usuario/cadastrar-usuario", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(usuario)
    });

}