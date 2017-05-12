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
        (_a = ["\n    <div class=\"logo\">\n        <h2>\n                <span>\n                    <img src=\"assets/img/banco-logo.png\" style=\"height: 80px;\"><br>\n                    Banco TDD<br>\n                    <em>Nunca Falha!</em>\n                </span>\n        </h2>\n    </div>\n    <div class=\"rodape blinker\">Para come\u00E7ar, insira o cart\u00E3o<br>no local indicado.</div>\n        "], _a.raw = ["\n    <div class=\"logo\">\n        <h2>\n                <span>\n                    <img src=\"assets/img/banco-logo.png\" style=\"height: 80px;\"><br>\n                    Banco TDD<br>\n                    <em>Nunca Falha!</em>\n                </span>\n        </h2>\n    </div>\n    <div class=\"rodape blinker\">Para come\u00E7ar, insira o cart\u00E3o<br>no local indicado.</div>\n        "], Tela.html(_a));
        var _a;
    };
    Tela.carregando = function (mensagem, aguarde) {
        if (mensagem === void 0) { mensagem = 'Recebendo informações da central.'; }
        if (aguarde === void 0) { aguarde = 'Aguarde...'; }
        Tela.html("\n    <div class=\"centro-tela\">\n            <span>\n                " + mensagem + "\n                <br>\n                <span class=\"blinker2\">" + aguarde + "</span>\n            </span>\n    </div>\n        ");
    };
    Tela.menu = function (operacao) {
        Tela.html("\n    <div>\n        <div id=\"menu-titulo\">" + operacao.titulo + "</div>\n        <div id=\"menu-opcao1\">" + (operacao.opcao1 && operacao.opcao1.label || '') + "</div>\n        <div id=\"menu-opcao2\">" + (operacao.opcao2 && operacao.opcao2.label || '') + "</div>\n        <div id=\"menu-opcao3\">" + (operacao.opcao3 && operacao.opcao3.label || '') + "</div>\n        <div id=\"menu-opcao4\">" + (operacao.opcao4 && operacao.opcao4.label || '') + "</div>\n        <div id=\"menu-opcao5\">" + (operacao.opcao5 && operacao.opcao5.label || '') + "</div>\n        <div id=\"menu-opcao6\">" + (operacao.opcao6 && operacao.opcao6.label || '') + "</div>\n        <div id=\"menu-opcao7\">" + (operacao.opcao7 && operacao.opcao7.label || '') + "</div>\n        <div id=\"menu-opcao8\">" + (operacao.opcao8 && operacao.opcao8.label || '') + "</div>\n    </div>\n        ");
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
        window.setTimeout(Tela.splash, 5000);
    };
    return Tela;
}());
var ATM = (function () {
    function ATM() {
    }
    ATM.prototype.iniciar = function (idCliente) {
        this.operacao("/iniciar/" + idCliente);
    };
    ATM.prototype.operacao = function (url) {
        var _this = this;
        Tela.carregando();
        console.log('going: ' + url);
        switch (url) {
            case '[SAIR]':
                Tela.carregando("Obrigado por usar TDD!<br>Operação finalizada com sucesso.", "");
                window.setTimeout(Tela.splash, 3000);
                return;
            case '[COMPROVANTE]':
                Tela.carregando("Seu comprovante está sendo impresso.");
                window.setTimeout(function () { return _this.operacao("[SAIR]"); }, 3000);
                return;
            default:
                this.carregarUrl(url);
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
        $('#opcao1').off('click').on('click', function () { return _this.operacao(operacao.opcao1.url); });
        $('#opcao2').off('click').on('click', function () { return _this.operacao(operacao.opcao2.url); });
        $('#opcao3').off('click').on('click', function () { return _this.operacao(operacao.opcao3.url); });
        $('#opcao4').off('click').on('click', function () { return _this.operacao(operacao.opcao4.url); });
        $('#opcao5').off('click').on('click', function () { return _this.operacao(operacao.opcao5.url); });
        $('#opcao6').off('click').on('click', function () { return _this.operacao(operacao.opcao6.url); });
        $('#opcao7').off('click').on('click', function () { return _this.operacao(operacao.opcao7.url); });
        $('#opcao8').off('click').on('click', function () { return _this.operacao(operacao.opcao8.url); });
    };
    return ATM;
}());
ATM.DELAY_REDE_MS = 2000 - 1900;
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
