document.getElementById("confirmarCadastro").addEventListener("click", async function () {

    const nome = document.getElementById("inputNome").value;
    const genero = document.getElementById("inputGenero").value;
    const email = document.getElementById("inputEmail").value;
    const senha = document.getElementById("inputSenha").value;

    if (nome === "") {
        window.alert("Nome é um campo obrigatório");
        return;
    }
    if (genero === "") {
        window.alert("Gênero é um campo obrigatório");
        return;
    }
    if (email === "") {
        window.alert("Email é um campo obrigatório");
        return;
    }
    if (senha === "") {
        window.alert("Senha é um campo obrigatório");
        return;
    }

    const usuario = {
        nome: nome,
        genero: genero,
        email: email,
        senha: senha
    };

    try {
        const resposta = await fetch("http://localhost:8080/usuario/cadastrar-usuario", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(usuario)
        });

        if (resposta.ok) {
            alert("Cadastro realizado com sucesso! Faça login.");
            window.location.href = "/html/login.html";
        } else {
            const erro = await resposta.text();
            alert("Erro ao cadastrar: " + erro);
        }
    } catch (erro) {
        alert("Erro ao conectar com o servidor: " + erro);
    }
});