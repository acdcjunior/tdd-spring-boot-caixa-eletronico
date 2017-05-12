declare const $: any;
declare const window: any;
declare const console: any;

const HOST = 'http://127.0.0.1:8080/';

function zeroPad(minute: number) {
    return minute < 10 ? "0" + minute : minute;
}
class Tela {
    static html(novoHtml) {
        $("#tela").html(novoHtml);
    }
    static splash() {
        Tela.html`
    <div class="logo">
        <h2>
                <span>
                    <img src="assets/img/banco-logo.png" style="height: 80px;"><br>
                    Banco TDD<br>
                    <em>Nunca Falha!</em>
                </span>
        </h2>
    </div>
    <div class="rodape blinker">Para começar, insira o cartão<br>no local indicado.</div>
        `
    }
    static carregando(mensagem = 'Recebendo informações da central.', aguarde = 'Aguarde...') {
        Tela.html(`
    <div class="centro-tela">
            <span>
                ${mensagem}
                <br>
                <span class="blinker2">${aguarde}</span>
            </span>
    </div>
        `)
    }

    static menu(operacao: Operacao) {
        Tela.html(`
    <div>
        <div id="menu-titulo">${operacao.titulo}</div>
        <div id="menu-opcao1">${operacao.opcao1 && operacao.opcao1.label || ''}</div>
        <div id="menu-opcao2">${operacao.opcao2 && operacao.opcao2.label || ''}</div>
        <div id="menu-opcao3">${operacao.opcao3 && operacao.opcao3.label || ''}</div>
        <div id="menu-opcao4">${operacao.opcao4 && operacao.opcao4.label || ''}</div>
        <div id="menu-opcao5">${operacao.opcao5 && operacao.opcao5.label || ''}</div>
        <div id="menu-opcao6">${operacao.opcao6 && operacao.opcao6.label || ''}</div>
        <div id="menu-opcao7">${operacao.opcao7 && operacao.opcao7.label || ''}</div>
        <div id="menu-opcao8">${operacao.opcao8 && operacao.opcao8.label || ''}</div>
    </div>
        `);
    }

    static relatorio(operacao: Operacao) {
        let d = new window.Date();
        let curr_date = zeroPad(d.getDate());
        let curr_month = zeroPad(d.getMonth() + 1); //Months are zero based
        let curr_year = d.getFullYear().toString().substr(2);
        const data = `${curr_date}-${curr_month}-${curr_year}`;

        let hour = zeroPad(d.getHours());
        let minute = zeroPad(d.getMinutes());
        let second = zeroPad(d.getSeconds());
        const hora = `${hour}:${minute}:${second}`;

        Tela.html(`
    <div>
        <div class="center">BANCO TDD</div>
        <div class="center">${data} - AUTO-ATENDIMENTO - ${hora}</div><br><br>
        <div class="center">SALDO PARA SIMPLES CONFERENCIA</div><br><br>
        <div>
            <div style="float:left;">AGENCIA: ${operacao.conta.agencia}</div>
            <div style="float:right;">CONTA: ${operacao.conta.codigo}</div>
        </div>
        <div style="float:left;">CLIENTE: ${(operacao.cliente.nome as any).toUpperCase()}</div>
        <br><br>
        <br><br>
        <div class="center">SALDO: R$ ${(operacao.conta.saldo as any).toFixed(2)}</div>
        
        <div id="menu-opcao4" style="text-align: right; margin-top:-10px">Imprimir<br>Comprovante</div>
        <div id="menu-opcao8">Sair</div>
    </div>
        `);
    }
    static erro() {
        Tela.carregando("Operação indisponível no momento.<br><br>Por favor, tente novamente mais tarde.", "");
        window.setTimeout(Tela.splash, 5000);
    }
}

class ATM {
    static readonly DELAY_REDE_MS: number = 2000 - 1900;

    public iniciar(idCliente: number) {
        this.operacao("/iniciar/" + idCliente);
    }
    public operacao(url) {
        Tela.carregando();
        console.log('going: ' + url);
        switch (url) {
            case '[SAIR]':
                Tela.carregando("Obrigado por usar TDD!<br>Operação finalizada com sucesso.", "");
                window.setTimeout(Tela.splash, 3000);
                return;
            case '[COMPROVANTE]':
                Tela.carregando("Seu comprovante está sendo impresso.");
                window.setTimeout(() => this.operacao("[SAIR]"), 3000);
                return;
            default:
                this.carregarUrl(url);
        }
    }

    private carregarUrl(url) {
        window.setTimeout(() => {
            $.ajax({
                dataType: "json",
                url: HOST + url,
                success: (operacao: Operacao) => {
                    this.configurarBotoes(operacao);
                    switch (operacao.tipo) {
                        case 'menu':
                            Tela.menu(operacao);
                            return;
                        case 'relatorio':
                            Tela.relatorio(operacao)
                    }
                },
                error: Tela.erro
            });
        }, ATM.DELAY_REDE_MS);
    }
    private configurarBotoes(operacao: Operacao) {
        $('#opcao1').off('click').on('click', () => this.operacao(operacao.opcao1.url));
        $('#opcao2').off('click').on('click', () => this.operacao(operacao.opcao2.url));
        $('#opcao3').off('click').on('click', () => this.operacao(operacao.opcao3.url));
        $('#opcao4').off('click').on('click', () => this.operacao(operacao.opcao4.url));
        $('#opcao5').off('click').on('click', () => this.operacao(operacao.opcao5.url));
        $('#opcao6').off('click').on('click', () => this.operacao(operacao.opcao6.url));
        $('#opcao7').off('click').on('click', () => this.operacao(operacao.opcao7.url));
        $('#opcao8').off('click').on('click', () => this.operacao(operacao.opcao8.url));
    }
}

class Operacao {
    tipo: string;

    titulo: string;

    conta: Conta;
    cliente: Cliente;

    opcao1: Opcao;
    opcao2: Opcao;
    opcao3: Opcao;
    opcao4: Opcao;
    opcao5: Opcao;
    opcao6: Opcao;
    opcao7: Opcao;
    opcao8: Opcao;
}
class Opcao {
    label: string;
    url: string;
}
class Conta {
    agencia: string;
    codigo: string;
    saldo: number;
}
class Cliente {
    nome: string;
    cpf: string;
}
