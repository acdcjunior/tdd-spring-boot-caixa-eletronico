var HOST = 'http://127.0.0.1:8080';
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
    Tela.carregando = function () {
        (_a = ["\n    <div class=\"centro-tela\">\n            <span>\n                Recebendo informa\u00E7\u00F5es da central.\n                <br>\n                <span class=\"blinker2\">Aguarde...</span>\n            </span>\n    </div>\n        "], _a.raw = ["\n    <div class=\"centro-tela\">\n            <span>\n                Recebendo informa\u00E7\u00F5es da central.\n                <br>\n                <span class=\"blinker2\">Aguarde...</span>\n            </span>\n    </div>\n        "], Tela.html(_a));
        var _a;
    };
    Tela.menu = function (menu) {
        Tela.html("\n    <div class=\"centro-tela\">\n            <span>\n                Menu\n                <br>\n                " + menu.titulo + "\n            </span>\n    </div>\n        ");
    };
    return Tela;
}());
var ATM = (function () {
    function ATM() {
    }
    ATM.prototype.iniciar = function (idCliente) {
        $.getJSON(HOST + "/iniciar/" + idCliente, function (menu) {
            Tela.menu(menu);
        });
    };
    return ATM;
}());
var Menu = (function () {
    function Menu(titulo, opcoes) {
        this.titulo = titulo;
        this.opcoes = opcoes;
    }
    return Menu;
}());
var OpcaoMenu = (function () {
    function OpcaoMenu() {
    }
    return OpcaoMenu;
}());
var Url = (function () {
    function Url() {
    }
    return Url;
}());
