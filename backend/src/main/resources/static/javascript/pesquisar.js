const API_URL = "http://localhost:8080";

function obterIdUsuarioLogado() {
    const id = localStorage.getItem("idUsuario");
    if (!id || id === "null" || isNaN(parseInt(id))) {
        alert("Você precisa fazer login primeiro.");
        window.location.href = "/html/login.html";
        return null;
    }
    return parseInt(id);
}

const idUsuario = obterIdUsuarioLogado();

document.getElementById("botaoPesquisar").addEventListener("click", async function () {
    const texto = document.getElementById("inputTexto").value;
    const formato = document.getElementById("inputFormato").value;

    if (!texto) {
        alert("Digite o que você está procurando.");
        return;
    }

    const pesquisa = {
        id_usuario: idUsuario,
        texto: texto,
        formato: formato
    };

    try {
        const resposta = await fetch(`${API_URL}/pesquisas`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(pesquisa)
        });

        if (resposta.ok) {
            document.getElementById("inputTexto").value = "";
            carregarHistorico();
        } else {
            const erro = await resposta.text();
            alert("Erro: " + erro);
        }
    } catch (erro) {
        alert("Erro ao conectar com o servidor: " + erro);
    }
});

async function carregarHistorico() {
    const div = document.getElementById("historicoPesquisas");
    div.innerHTML = "Carregando...";

    try {
        const resposta = await fetch(`${API_URL}/pesquisas/historico/${idUsuario}`);

        if (!resposta.ok) {
            div.innerHTML = "<p>Não foi possível carregar o histórico.</p>";
            return;
        }

        const historico = await resposta.json();

        if (historico.length === 0) {
            div.innerHTML = "<p>Nenhuma pesquisa registrada ainda.</p>";
            return;
        }

        div.innerHTML = "";
        historico.forEach(item => {
            const data = new Date(item.data_hora).toLocaleString();
            div.innerHTML += `<p>"${item.texto}" (${item.formato || "sem formato"}) — ${data}</p>`;
        });
    } catch (erro) {
        div.innerHTML = "<p>Erro ao conectar com o servidor.</p>";
    }
}

carregarHistorico();