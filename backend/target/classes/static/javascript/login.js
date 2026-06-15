document.getElementById("botaoConfirmar").onclick = async function() {

    // Pegar dados do formulário
    let email = document.getElementById("inputEmail").value;
    let senha = document.getElementById("inputSenha").value;

    // Enviar para o backend verificar os dados
    const resposta = await fetch("http://localhost:8080/usuario/login", {

        method: "POST",

        headers: {"Content-Type": "application/json"},

        body: JSON.stringify({
            email: email,
            senha: senha
        })

    });

    // Fazer login se o usuário existir no banco
    if(resposta === null) {

        window.alert("Este usuário não existe.");

    } else {

        const idUsuario = await resposta.json();
        localStorage.setItem("idUsuario", idUsuario);

    }
   localStorage.setItem("idUsuarioLogado", idDoUsuarioQueLogou);
}