package tdd.caixaeletronico.web;

import java.util.ArrayList;
import java.util.List;

class Menu {
    private String titulo;
    private List<OpcaoMenu> opcoes = new ArrayList<>();

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<OpcaoMenu> getOpcoes() { return opcoes; }
    public void addOpcao(OpcaoMenu opcao) { this.opcoes.add(opcao); }
}

class OpcaoMenu {
    private String titulo;
    private Url operacao;
    OpcaoMenu(String titulo, Url operacao) {
        this.titulo = titulo;
        this.operacao = operacao;
    }
    public String getTitulo() { return titulo; }
    public Url getOperacao() { return operacao; }
}

class Url {
    private String href;
    public Url(String href) {
        this.href = href;
    }
    public String getHref() { return href; }
}
