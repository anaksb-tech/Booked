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

        // Imprime no console (F12) para você ver o que o backend mandou
        console.log("Dados recebidos do backend:", usuario);

        // Tenta pegar o ID independente de como o backend gerou o nome do campo
        let idCorreto = usuario.id || usuario.idUsuario || usuario.id_usuario;

        if (!idCorreto) {
            window.alert("Erro: O backend não devolveu um ID válido.");
            return;
        }

        // Salva os dados no navegador
        localStorage.setItem("nomeUsuario", usuario.nome);
        localStorage.setItem("idUsuario", idCorreto);

        // Redireciona para a tela inicial
        window.location.href = "/html/index.html";
    }
}