let randomNum = gerarNumeroAleatorioSemRepeticao();
let tentativasRestantes = 8;
let historico = [];

document.getElementById("enviarBtn").addEventListener("click", function() {
    const input = document.getElementById("numeroInput").value;
    
    if (input.length !== 4) {
        mostrarMensagem("Por favor, insira um número de 4 dígitos.");
        return;
    }

    tentativasRestantes--;
    atualizarTentativas();

    let corretosNaPosicao = 0;
    let corretosForaDaPosicao = 0;

    let usadoNoAleatorio = [false, false, false, false];
    let usadoNoInput = [false, false, false, false];

    // Verifica dígitos corretos na posição correta
    for (let i = 0; i < 4; i++) {
        if (input[i] === randomNum[i]) {
            corretosNaPosicao++;
            usadoNoAleatorio[i] = true;
            usadoNoInput[i] = true;
        }
    }

    // Verifica dígitos corretos fora da posição
    for (let i = 0; i < 4; i++) {
        if (!usadoNoInput[i]) {
            for (let j = 0; j < 4; j++) {
                if (!usadoNoAleatorio[j] && input[i] === randomNum[j]) {
                    corretosForaDaPosicao++;
                    usadoNoAleatorio[j] = true;
                    break;
                }
            }
        }
    }

    if (corretosNaPosicao === 4) {
        mostrarMensagem("PARABÉNS, VOCÊ ACERTOU TODOS OS NÚMEROS E VENCEU O JOGO!!!");
        desabilitarJogo();
    } 
    // Salva a tentativa no histórico
    adicionarAoHistorico(input, corretosNaPosicao, corretosForaDaPosicao);

    if (tentativasRestantes === 0 && corretosNaPosicao !== 4) {
        mostrarMensagem(`Fim de jogo! O número correto era: ${randomNum}`);
        desabilitarJogo();
    }
});

function gerarNumeroAleatorioSemRepeticao() {
    let numAleatorio = "";
    while (numAleatorio.length < 4) {
        let digito = Math.floor(Math.random() * 10).toString();
        if (!numAleatorio.includes(digito)) {
            numAleatorio += digito;
        }
    }
    return numAleatorio;
}

function mostrarMensagem(mensagem) {
    document.getElementById("mensagem").innerText = mensagem;
}

function atualizarTentativas() {
    document.getElementById("tentativas").innerText = `Tentativas restantes: ${tentativasRestantes}`;
}

function desabilitarJogo() {
    document.getElementById("numeroInput").disabled = true;
    document.getElementById("enviarBtn").disabled = true;
}

function adicionarAoHistorico(numero, corretosNaPosicao, corretosForaDaPosicao) {
    const historicoUl = document.getElementById("historico");
    const novoItem = document.createElement("li");
    
    novoItem.textContent = `Número: ${numero}, Certos na posição: ${corretosNaPosicao}, Certos fora da posição: ${corretosForaDaPosicao}`;
    historicoUl.appendChild(novoItem);
}
