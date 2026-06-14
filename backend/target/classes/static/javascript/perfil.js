window.onload = async function() {

    // Informações do usuário que vão aparecer na tela
    const nome = document.getElementById("nome");
    const email = document.getElementById("email");
    const genero = document.getElementById("genero");
    const compras = document.getElementById("compras");


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

    //const spliting = usuario.compras.split(",,");
    //const result = spliting.reduce((acc, val, index) => {
        //    if (index > 0 && index % 5 === 0) {
    //        acc.push("\n"); // Use "<br>" if rendering in HTML
    //    }
//    acc.push(val);
//    return acc;
//}, []).join(",");

    let contador = 0;

    let resultado = usuario.compras.replace(/,,/g, () => {
        contador++;
        return contador % 5 === 0 ? "\n" : ",,";
    });
    compras.textContent = resultado.replaceAll(",,", "; ");
}