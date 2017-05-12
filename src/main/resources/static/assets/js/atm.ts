declare const $: any;
declare const window: any;

const HOST = window.location.href.indexOf('_ijt') !== -1 ? 'http://127.0.0.1:8080/' : '/';

function zeroPad(minute: number) {
    return minute < 10 ? "0" + minute : minute;
}
class Tela {
    static html(novoHtml) {
        $("#tela").html(novoHtml);
    }
    static splash() {
        Tela.html`
    <div id="splash" class="logo">
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
        <div id="menu-subtitulo">${operacao.cliente ? (operacao.cliente.nome as any).toUpperCase() + "(" + (operacao.cliente.cpf as any).toUpperCase() + ")" : ""}</div>
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
    <div id="relatorio">
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
        window.setTimeout(Tela.splash, ATM.DELAY_TELAS_INFORMACAO * 2);
    }
    static configurarTeclado() {
        function up(numero) {
            $("#senha").val( (($("#senha").val()+numero) || '').substr(0, 4) );
            $("#valor").val( ($("#valor").val()+numero) );
        }
        $("#b1").off('click').on('click', () => up(1));
        $("#b2").off('click').on('click', () => up(2));
        $("#b3").off('click').on('click', () => up(3));
        $("#b4").off('click').on('click', () => up(4));
        $("#b5").off('click').on('click', () => up(5));
        $("#b6").off('click').on('click', () => up(6));
        $("#b7").off('click').on('click', () => up(7));
        $("#b8").off('click').on('click', () => up(8));
        $("#b9").off('click').on('click', () => up(9));
        $("#b0").off('click').on('click', () => up(0));
        $("#bClear").off('click').on('click', () => $("#senha").val(''));
    }
    static senha(atm: ATM, cliente: Cliente, urlSucesso: string) {
        $("#bCancel").off('click').on('click', () => {
            $("#bCancel").off('click');
            atm.executar("[CANCELAR]", cliente);
        });
        $("#bEnter").off('click').on('click', () => {
            $("#bEnter").off('click');
            const senhaDigitada = $("#senha").val();
            Tela.carregando();
            if (senhaDigitada === cliente.senha) {
                atm.carregarUrl(urlSucesso);
            } else {
                window.setTimeout(() => {
                    Tela.carregando("Senha incorreta.<br>Operação cancelada com sucesso.", "");
                    window.setTimeout(Tela.splash, ATM.DELAY_TELAS_INFORMACAO);
                }, ATM.DELAY_REDE_MS);
            }
        });

        Tela.html(`
    <div class="centro-tela">
            <span>
                Digite sua senha para prosseguir.
                <br>
                <br>
                <input id="senha" type="text" style="width: 31%; font-size: 300%;">
                <br>
                <br>
                Pressione ENTER para continuar ou CANCEL para cancelar.
            </span>
    </div>
        `);
    }
    static entradaValor(atm: ATM, cliente: Cliente, urlSucesso: string) {
        $("#bCancel").off('click').on('click', () => {
            $("#bCancel").off('click');
            atm.executar("[CANCELAR]", cliente);
        });
        $("#bEnter").off('click').on('click', () => {
            $("#bEnter").off('click');
            const valorDigitado = $("#valor").val();
            Tela.carregando();
            if (!(+valorDigitado > 0)) {
                window.setTimeout(() => {
                    Tela.carregando("Número fornecido inválido.<br>Operação cancelada com sucesso.", "");
                    window.setTimeout(Tela.splash, ATM.DELAY_TELAS_INFORMACAO);
                }, ATM.DELAY_REDE_MS);
            } else {
                window.setTimeout(() => Tela.senha(atm, cliente, urlSucesso + "?valor=" + valorDigitado), ATM.DELAY_REDE_MS);
            }
        });

        Tela.html(`
    <div class="centro-tela">
            <span>
                Digite o valor desejado.
                <br>
                <br>
                <span style="font-size: 300%;">R$ <input id="valor" type="text" style="width: 41%; font-size: 100%;">,00</span>
                <br>
                <br>
                Pressione ENTER para continuar ou CANCEL para cancelar.
            </span>
    </div>
        `);
    }
}

class ATM {
    static readonly REDUCAO: number = .5;
    static readonly DELAY_REDE_MS: number = 2000 * ATM.REDUCAO;
    static readonly DELAY_TELAS_INFORMACAO: number = 3000 * ATM.REDUCAO;

    public menu(idCliente: number) {
        this.executar("/menu/" + idCliente);
    }
    public executar(url: string, cliente?: Cliente) {
        Tela.carregando();

        switch ((url as any).replace(/[\/0-9]+/g, '')) {
            case '[CANCELAR]':
                Tela.carregando("Obrigado por usar TDD!<br>Operação cancelada com sucesso.", "");
                window.setTimeout(Tela.splash, ATM.DELAY_TELAS_INFORMACAO);
                return;
            case '[SAIR]':
                Tela.carregando("Obrigado por usar TDD!<br>Operação finalizada com sucesso.", "");
                window.setTimeout(Tela.splash, ATM.DELAY_TELAS_INFORMACAO);
                return;
            case '[COMPROVANTE]':
                Tela.carregando("Seu comprovante está sendo impresso.");
                window.setTimeout(() => this.executar("[SAIR]", cliente), ATM.DELAY_TELAS_INFORMACAO*2);
                return;
            case 'saldo':
                window.setTimeout(() => Tela.senha(this, cliente, url), ATM.DELAY_REDE_MS);
                return;
            default:
                this.carregarUrl(url);
        }
    }

    carregarUrl(url) {
        const urlSemBarrasNoInicio = url.replace(/^\/+/, '');
        window.setTimeout(() => {
            $.ajax({
                dataType: "json",
                url: HOST + urlSemBarrasNoInicio,
                success: (operacao: Operacao) => {
                    this.configurarBotoes(operacao);
                    switch (operacao.tipo) {
                        case 'menu':
                            Tela.menu(operacao);
                            return;
                        case 'relatorio':
                            Tela.relatorio(operacao);
                            return;
                        case 'informacao':
                            Tela.carregando(operacao.titulo, "");
                            window.setTimeout(Tela.splash, ATM.DELAY_TELAS_INFORMACAO*2);
                            return;
                    }
                },
                error: Tela.erro
            });
        }, ATM.DELAY_REDE_MS);
    }
    private configurarBotoes(operacao: Operacao) {
        $('#opcao1').off('click').on('click', () => this.executar(operacao.opcao1.url, operacao.cliente));
        $('#opcao2').off('click').on('click', () => this.executar(operacao.opcao2.url, operacao.cliente));
        $('#opcao3').off('click').on('click', () => this.executar(operacao.opcao3.url, operacao.cliente));
        $('#opcao4').off('click').on('click', () => this.executar(operacao.opcao4.url, operacao.cliente));
        $('#opcao5').off('click').on('click', () => this.executar(operacao.opcao5.url, operacao.cliente));
        $('#opcao6').off('click').on('click', () => this.executar(operacao.opcao6.url, operacao.cliente));
        $('#opcao7').off('click').on('click', () => this.executar(operacao.opcao7.url, operacao.cliente));
        $('#opcao8').off('click').on('click', () => this.executar(operacao.opcao8.url, operacao.cliente));
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
    senha: string;
}
