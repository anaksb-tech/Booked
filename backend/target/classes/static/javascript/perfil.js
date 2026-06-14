window.onload = async function() {

    // Informações do usuário que vão aparecer na tela
    const nome = document.getElementById("nome");
    const email = document.getElementById("email");
    const genero = document.getElementById("genero");
    const compras = document.getElementById("compras");
    const trocas = document.getElementById("trocas");

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
        genero.textContent = "Outras";
    }

    let contador = 0;
    //é ultilizado uma varchar(255) "compras" unica no mysql com todos os nomes dos ebooks
    // tendo ,, em sua frente (exceto pela primeira) Ex: Casa de Papelão,,Maria, a Magia,,EXquecido
    // cada quinto ,, é substituido por um \n, para organização
    let resultado = usuario.compras.replace(/,,/g, () => {
        contador++;
        return contador % 3 === 0 ? ";\n" : ",,";
    });
    //então os ,, restantes são substituidos por ; para limpa
    compras.textContent = resultado.replaceAll(",,", "; ");
    contador = 0;
    resultado = usuario.trocas.replace(/,,/g, () => {
        contador++;
        return contador % 1 === 0 ? ";\n" : ",,";
    });
    trocas.textContent = resultado.replaceAll(",,", "; ");
}