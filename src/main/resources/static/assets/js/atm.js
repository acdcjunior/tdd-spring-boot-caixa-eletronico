var HOST = 'http://127.0.0.1:8080/';
function zeroPad(minute) {
    return minute < 10 ? "0" + minute : minute;
}
var Tela = (function () {
    function Tela() {
    }
    Tela.html = function (novoHtml) {
        $("#tela").html(novoHtml);
    };
    Tela.splash = function () {
        (_a = ["\n    <div id=\"splash\" class=\"logo\">\n        <h2>\n                <span>\n                    <img src=\"assets/img/banco-logo.png\" style=\"height: 80px;\"><br>\n                    Banco TDD<br>\n                    <em>Nunca Falha!</em>\n                </span>\n        </h2>\n    </div>\n    <div class=\"rodape blinker\">Para come\u00E7ar, insira o cart\u00E3o<br>no local indicado.</div>\n        "], _a.raw = ["\n    <div id=\"splash\" class=\"logo\">\n        <h2>\n                <span>\n                    <img src=\"assets/img/banco-logo.png\" style=\"height: 80px;\"><br>\n                    Banco TDD<br>\n                    <em>Nunca Falha!</em>\n                </span>\n        </h2>\n    </div>\n    <div class=\"rodape blinker\">Para come\u00E7ar, insira o cart\u00E3o<br>no local indicado.</div>\n        "], Tela.html(_a));
        var _a;
    };
    Tela.carregando = function (mensagem, aguarde) {
        if (mensagem === void 0) { mensagem = 'Recebendo informações da central.'; }
        if (aguarde === void 0) { aguarde = 'Aguarde...'; }
        Tela.html("\n    <div class=\"centro-tela\">\n            <span>\n                " + mensagem + "\n                <br>\n                <span class=\"blinker2\">" + aguarde + "</span>\n            </span>\n    </div>\n        ");
    };
    Tela.menu = function (operacao) {
        Tela.html("\n    <div>\n        <div id=\"menu-titulo\">" + operacao.titulo + "</div>\n        <div id=\"menu-subtitulo\">" + operacao.cliente.nome.toUpperCase() + " (" + operacao.cliente.cpf.toUpperCase() + ")</div>\n        <div id=\"menu-opcao1\">" + (operacao.opcao1 && operacao.opcao1.label || '') + "</div>\n        <div id=\"menu-opcao2\">" + (operacao.opcao2 && operacao.opcao2.label || '') + "</div>\n        <div id=\"menu-opcao3\">" + (operacao.opcao3 && operacao.opcao3.label || '') + "</div>\n        <div id=\"menu-opcao4\">" + (operacao.opcao4 && operacao.opcao4.label || '') + "</div>\n        <div id=\"menu-opcao5\">" + (operacao.opcao5 && operacao.opcao5.label || '') + "</div>\n        <div id=\"menu-opcao6\">" + (operacao.opcao6 && operacao.opcao6.label || '') + "</div>\n        <div id=\"menu-opcao7\">" + (operacao.opcao7 && operacao.opcao7.label || '') + "</div>\n        <div id=\"menu-opcao8\">" + (operacao.opcao8 && operacao.opcao8.label || '') + "</div>\n    </div>\n        ");
    };
    Tela.relatorio = function (operacao) {
        var d = new window.Date();
        var curr_date = zeroPad(d.getDate());
        var curr_month = zeroPad(d.getMonth() + 1); //Months are zero based
        var curr_year = d.getFullYear().toString().substr(2);
        var data = curr_date + "-" + curr_month + "-" + curr_year;
        var hour = zeroPad(d.getHours());
        var minute = zeroPad(d.getMinutes());
        var second = zeroPad(d.getSeconds());
        var hora = hour + ":" + minute + ":" + second;
        Tela.html("\n    <div>\n        <div class=\"center\">BANCO TDD</div>\n        <div class=\"center\">" + data + " - AUTO-ATENDIMENTO - " + hora + "</div><br><br>\n        <div class=\"center\">SALDO PARA SIMPLES CONFERENCIA</div><br><br>\n        <div>\n            <div style=\"float:left;\">AGENCIA: " + operacao.conta.agencia + "</div>\n            <div style=\"float:right;\">CONTA: " + operacao.conta.codigo + "</div>\n        </div>\n        <div style=\"float:left;\">CLIENTE: " + operacao.cliente.nome.toUpperCase() + "</div>\n        <br><br>\n        <br><br>\n        <div class=\"center\">SALDO: R$ " + operacao.conta.saldo.toFixed(2) + "</div>\n        \n        <div id=\"menu-opcao4\" style=\"text-align: right; margin-top:-10px\">Imprimir<br>Comprovante</div>\n        <div id=\"menu-opcao8\">Sair</div>\n    </div>\n        ");
    };
    Tela.erro = function () {
        Tela.carregando("Operação indisponível no momento.<br><br>Por favor, tente novamente mais tarde.", "");
        window.setTimeout(Tela.splash, ATM.DELAY_TELAS_INFORMACAO * 2);
    };
    Tela.configurarTeclado = function () {
        function up(numero) {
            $("#senha").val(($("#senha").val() + numero).substr(0, 4));
        }
        $("#b1").off('click').on('click', function () { return up(1); });
        $("#b2").off('click').on('click', function () { return up(2); });
        $("#b3").off('click').on('click', function () { return up(3); });
        $("#b4").off('click').on('click', function () { return up(4); });
        $("#b5").off('click').on('click', function () { return up(5); });
        $("#b6").off('click').on('click', function () { return up(6); });
        $("#b7").off('click').on('click', function () { return up(7); });
        $("#b8").off('click').on('click', function () { return up(8); });
        $("#b9").off('click').on('click', function () { return up(9); });
        $("#b0").off('click').on('click', function () { return up(0); });
        $("#bClear").off('click').on('click', function () { return $("#senha").val(''); });
    };
    Tela.senha = function (atm, cliente, urlSucesso) {
        $("#bCancel").off('click').on('click', function () {
            $("#bCancel").off('click');
            atm.executar("[CANCELAR]", cliente);
        });
        $("#bEnter").off('click').on('click', function () {
            $("#bEnter").off('click');
            var senhaDigitada = $("#senha").val();
            Tela.carregando();
            if (senhaDigitada === cliente.senha) {
                atm.carregarUrl(urlSucesso);
            }
            else {
                window.setTimeout(function () {
                    Tela.carregando("Senha incorreta.<br>Operação cancelada com sucesso.", "");
                    window.setTimeout(Tela.splash, ATM.DELAY_TELAS_INFORMACAO);
                }, ATM.DELAY_REDE_MS);
            }
        });
        Tela.html("\n    <div class=\"centro-tela\">\n            <span>\n                Digite sua senha para continuar.\n                <br>\n                <br>\n                <input id=\"senha\" type=\"text\" style=\"width: 31%; font-size: 300%;\">\n                <br>\n                <br>\n                Pressione ENTER para continuar ou CANCEL para cancelar.\n            </span>\n    </div>\n        ");
    };
    return Tela;
}());
var ATM = (function () {
    function ATM() {
    }
    ATM.prototype.iniciar = function (idCliente) {
        this.executar("/iniciar/" + idCliente);
    };
    ATM.prototype.executar = function (url, cliente) {
        var _this = this;
        var telaAtualEhMenu = $("#menu-titulo").length;
        Tela.carregando();
        switch (url) {
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
                window.setTimeout(function () { return _this.executar("[SAIR]", cliente); }, ATM.DELAY_TELAS_INFORMACAO);
                return;
            default:
                if (telaAtualEhMenu) {
                    window.setTimeout(function () { return Tela.senha(_this, cliente, url); }, ATM.DELAY_REDE_MS);
                }
                else {
                    this.carregarUrl(url);
                }
        }
    };
    ATM.prototype.carregarUrl = function (url) {
        var _this = this;
        window.setTimeout(function () {
            $.ajax({
                dataType: "json",
                url: HOST + url,
                success: function (operacao) {
                    _this.configurarBotoes(operacao);
                    switch (operacao.tipo) {
                        case 'menu':
                            Tela.menu(operacao);
                            return;
                        case 'relatorio':
                            Tela.relatorio(operacao);
                    }
                },
                error: Tela.erro
            });
        }, ATM.DELAY_REDE_MS);
    };
    ATM.prototype.configurarBotoes = function (operacao) {
        var _this = this;
        $('#opcao1').off('click').on('click', function () { return _this.executar(operacao.opcao1.url, operacao.cliente); });
        $('#opcao2').off('click').on('click', function () { return _this.executar(operacao.opcao2.url, operacao.cliente); });
        $('#opcao3').off('click').on('click', function () { return _this.executar(operacao.opcao3.url, operacao.cliente); });
        $('#opcao4').off('click').on('click', function () { return _this.executar(operacao.opcao4.url, operacao.cliente); });
        $('#opcao5').off('click').on('click', function () { return _this.executar(operacao.opcao5.url, operacao.cliente); });
        $('#opcao6').off('click').on('click', function () { return _this.executar(operacao.opcao6.url, operacao.cliente); });
        $('#opcao7').off('click').on('click', function () { return _this.executar(operacao.opcao7.url, operacao.cliente); });
        $('#opcao8').off('click').on('click', function () { return _this.executar(operacao.opcao8.url, operacao.cliente); });
    };
    return ATM;
}());
ATM.REDUCAO = .5;
ATM.DELAY_REDE_MS = 2000 * ATM.REDUCAO;
ATM.DELAY_TELAS_INFORMACAO = 3000 * ATM.REDUCAO;
var Operacao = (function () {
    function Operacao() {
    }
    return Operacao;
}());
var Opcao = (function () {
    function Opcao() {
    }
    return Opcao;
}());
var Conta = (function () {
    function Conta() {
    }
    return Conta;
}());
var Cliente = (function () {
    function Cliente() {
    }
    return Cliente;
}());
