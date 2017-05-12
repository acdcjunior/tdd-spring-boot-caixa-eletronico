package tdd.caixaeletronico.web.viewmodel;

@SuppressWarnings("unused")
public class Opcao {

    public static final String SAIR = "[SAIR]";
    public static final String COMPROVANTE = "[COMPROVANTE]";

    private String label;
    private String url;

    public Opcao(String label, String url) {
        this.label = label;
        this.url = url;
    }

    public String getLabel() { return label; }
    public String getUrl() { return url; }

}
