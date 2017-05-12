package tdd.caixaeletronico.web;

import tdd.caixaeletronico.negocio.cliente.Cliente;
import tdd.caixaeletronico.negocio.conta.Conta;

@SuppressWarnings({"WeakerAccess", "unused"})
class Operacao {
    private String tipo;

    private String titulo;

    private Conta conta;
    private Cliente cliente;

    private Opcao opcao1;
    private Opcao opcao2;
    private Opcao opcao3;
    private Opcao opcao4;
    private Opcao opcao5;
    private Opcao opcao6;
    private Opcao opcao7;
    private Opcao opcao8;

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Opcao getOpcao1() { return opcao1; }
    public void setOpcao1(Opcao opcao1) { this.opcao1 = opcao1; }
    public Opcao getOpcao2() { return opcao2; }
    public void setOpcao2(Opcao opcao2) { this.opcao2 = opcao2; }
    public Opcao getOpcao3() { return opcao3; }
    public void setOpcao3(Opcao opcao3) { this.opcao3 = opcao3; }
    public Opcao getOpcao4() { return opcao4; }
    public void setOpcao4(Opcao opcao4) { this.opcao4 = opcao4; }
    public Opcao getOpcao5() { return opcao5; }
    public void setOpcao5(Opcao opcao5) { this.opcao5 = opcao5; }
    public Opcao getOpcao6() { return opcao6; }
    public void setOpcao6(Opcao opcao6) { this.opcao6 = opcao6; }
    public Opcao getOpcao7() { return opcao7; }
    public void setOpcao7(Opcao opcao7) { this.opcao7 = opcao7; }
    public Opcao getOpcao8() { return opcao8; }
    public void setOpcao8(Opcao opcao8) { this.opcao8 = opcao8; }
}

@SuppressWarnings("unused")
class Opcao {
    static final String SAIR = "[SAIR]";
    static final String COMPROVANTE = "[COMPROVANTE]";

    private String label;
    private String url;
    Opcao(String label, String url) {
        this.label = label;
        this.url = url;
    }
    public String getLabel() { return label; }
    public String getUrl() { return url; }
}
