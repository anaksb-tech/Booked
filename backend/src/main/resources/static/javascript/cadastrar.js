window.onload = function() {

    document.getElementById("confirmarCadastro").onclick = async function(event) {

        event.preventDefault();

        // Pega os dados do formulário
        let nome = document.getElementById("inputNome").value;
        let genero = document.getElementById("inputGenero").value;
        let email = document.getElementById("inputEmail").value;
        let senha = document.getElementById("inputSenha").value;

        // Valida os dados do formulário
        if(nome === "") {
            window.alert("Nome é um campo obrigatório");
        } else if(genero === "") {
            window.alert("Gênero é um campo obrigatório");
        } else if(email === "") {
            window.alert("Email é um campo obrigatório");
        } else if(senha === "") {
            window.alert("Senha é um campo obrigatório");
        } else {

            // Verifica se já existe esse usuário no banco
            let url = `http://localhost:8080/usuario/existe/${encodeURIComponent(email)}`
            let resposta = await fetch(url);
            let existe = await resposta.json();

            if(existe) {
                window.alert("Este endereço de email já está em uso");
            } else {

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

                // Faz login e retorna à página inicial
                if(resposta.ok) {

                    resposta = await fetch(`http://localhost:8080/usuario/buscar/${encodeURIComponent(email)}`);
                    let usuario = await resposta.json();
                    let idUsuario = await usuario.id;
                    let nomeUsuario = await usuario.nome;

                    if(resposta.ok) {
                        localStorage.setItem("idUsuario", idUsuario);
                        localStorage.setItem("nomeUsuario", nomeUsuario)
                        window.location.href=`/html/index.html`;
                    }

                }

            }

        }

    }

}

