declare const $: any;

const HOST = 'http://127.0.0.1:8080';

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
    static carregando() {
        Tela.html`
    <div class="centro-tela">
            <span>
                Recebendo informações da central.
                <br>
                <span class="blinker2">Aguarde...</span>
            </span>
    </div>
        `
    }
    static menu(menu: Menu) {
        Tela.html(`
    <div class="centro-tela">
            <span>
                Menu
                <br>
                ${menu.titulo}
            </span>
    </div>
        `);
    }
}

class ATM {
    public iniciar(idCliente: number) {
        $.getJSON(HOST + "/iniciar/" + idCliente, (menu: Menu) => {
            Tela.menu(menu);
        });
    }
}

class Menu {
    constructor(public titulo, public opcoes: string[]) {
    }
}
class OpcaoMenu {
    titulo: string;
    operacao: Url;
}
class Url {
    href: string;
}
