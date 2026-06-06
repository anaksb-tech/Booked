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
    let texto = await resposta.text();

    if(texto === "") {

        window.alert("Endereço de email ou senha estão incorretos");

    } else {

        let usuario = JSON.parse(texto);
        localStorage.setItem("nomeUsuario", usuario.nome);
        localStorage.setItem("idUsuario", usuario.id);
        window.location.href = "/html/index.html";

    }


}