const API_URL = "http://localhost:8080";

function obterIdUsuarioLogado() {
    let id = localStorage.getItem("idUsuarioLogado");
    if (!id) {
        id = prompt("Digite seu ID de usuário (simulação de login):");
        localStorage.setItem("idUsuarioLogado", id);
    }
    return id;
}

const meuId = obterIdUsuarioLogado();
let idAmigoAtual = null;

document.getElementById("botaoAbrirConversa").addEventListener("click", function () {
    const idAmigo = document.getElementById("inputIdAmigo").value;

    if (!idAmigo) {
        alert("Informe o ID do outro usuário.");
        return;
    }

    idAmigoAtual = idAmigo;
    carregarConversa();
});

document.getElementById("botaoEnviar").addEventListener("click", async function () {
    const texto = document.getElementById("inputTextoMensagem").value;

    if (!idAmigoAtual) {
        alert("Abra uma conversa antes de enviar mensagens.");
        return;
    }

    if (!texto) {
        alert("Digite uma mensagem.");
        return;
    }

    const mensagem = {
        id_usuario1: meuId,
        id_usuario2: idAmigoAtual,
        texto: texto
    };

    try {
        const resposta = await fetch(`${API_URL}/mensagens`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(mensagem)
        });

        if (resposta.ok) {
            document.getElementById("inputTextoMensagem").value = "";
            carregarConversa();
        } else {
            const erro = await resposta.text();
            alert("Erro: " + erro);
        }
    } catch (erro) {
        alert("Erro ao conectar com o servidor: " + erro);
    }
});

async function carregarConversa() {
    const caixa = document.getElementById("caixaConversa");
    caixa.innerHTML = "Carregando...";

    try {
        const resposta = await fetch(`${API_URL}/mensagens/conversa/${meuId}/${idAmigoAtual}`);

        if (!resposta.ok) {
            caixa.innerHTML = "<p>Não foi possível carregar a conversa.</p>";
            return;
        }

        const mensagens = await resposta.json();

        if (mensagens.length === 0) {
            caixa.innerHTML = "<p>Nenhuma mensagem ainda.</p>";
            return;
        }

        caixa.innerHTML = "";
        mensagens.forEach(msg => {
            const remetente = msg.id_usuario1 === meuId ? "Você" : "Outro usuário";
            caixa.innerHTML += `<p>${remetente}: ${msg.texto}</p>`;
        });
    } catch (erro) {
        caixa.innerHTML = "<p>Erro ao conectar com o servidor.</p>";
    }
}

setInterval(() => {
    if (idAmigoAtual) {
        carregarConversa();
    }
}, 5000);