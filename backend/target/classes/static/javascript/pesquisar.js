const API_URL = "http://localhost:8080";

function obterIdUsuarioLogado() {
    let id = localStorage.getItem("idUsuarioLogado");
    if (!id) {
        id = prompt("Digite seu ID de usuário (simulação de login):");
        localStorage.setItem("idUsuarioLogado", id);
    }
    return parseInt(id);
}

const idUsuario = obterIdUsuarioLogado();

document.getElementById("botaoPesquisar").addEventListener("click", async function () {

    const texto = document.getElementById("inputTextoBusca").value;
    const formato = document.getElementById("inputTipoLivro").value;

    if (!texto) {
        alert("Digite o que você está procurando.");
        return;
    }

    const pesquisa = {
        id_usuario: idUsuario,
        texto: texto,
        filtros: {
            categorias: [formato]
        }
    };

    try {
        const resposta = await fetch(`${API_URL}/pesquisas`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(pesquisa)
        });

        if (resposta.ok) {
            document.getElementById("inputTextoBusca").value = "";
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
    const div = document.getElementById("listaHistorico");
     if (!div) return;

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

        historico.reverse();

        historico.forEach(item => {
            const data = new Date(item.data_hora).toLocaleString();

            let formatoExibicao = "sem formato";
            if (item.filtros && item.filtros.categorias && item.filtros.categorias.length > 0) {
                formatoExibicao = item.filtros.categorias[0];
            }

            div.innerHTML += `<p>"<strong>${item.texto}</strong>" (${formatoExibicao}) — <span style="color: gray; font-size: 12px;">${data}</span></p>`;
        });
    } catch (erro) {
        div.innerHTML = "<p>Erro ao conectar com o servidor.</p>";
    }
}
carregarHistorico();