package tdd.caixaeletronico.fimafim;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import tdd.caixaeletronico.negocio.cliente.Cliente;
import tdd.caixaeletronico.negocio.cliente.ClienteRepository;
import tdd.caixaeletronico.negocio.conta.Conta;
import tdd.caixaeletronico.negocio.conta.ContaRepository;

import java.math.BigDecimal;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaixaFimAFimTest {

    @LocalServerPort
    private int port;

    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ContaRepository contaRepository;

    private int espera;

    @Before
    public void setUp() {
        String nomeCliente = "Joao Silva";
        String cpfCliente = "555.888.999-33";
        Cliente cliente = new Cliente(99L, nomeCliente, cpfCliente, "9528");
        given(this.clienteRepository.findById(1)).willReturn(cliente);
        Conta conta = new Conta("1144-X", "99999-9", "CC", cliente, new BigDecimal(5000));
        given(contaRepository.findByCliente(cliente)).willReturn(conta);

//        $.driver().useChrome();
        this.espera = 3000;
        $.url("http://127.0.0.1:" + port + "/");
    }

    @After
    public void tearDown() {
        $.driver().quit();
    }

    @Test
    public void saldo_eh_exibido_corretamente() {
        inserirCartao();
        aguardarMenu();
        selecionarOpcaoSaldo();
        aguardarTelaSenha();
        digitarSenha();
        aguardarSaldoEmTela();

        assertThat(textoEmTela()).contains("SALDO", "JOAO SILVA", "R$ 5000");
    }

    private void aguardarSaldoEmTela() {
        $("#relatorio").waitUntil().is(":visible");
        $.pause(espera);
    }

    private void inserirCartao() {
        $("#cartao").waitUntil().is(":visible").then().click();
        $.pause(espera);
    }

    private void aguardarMenu() {
        $("#menu-titulo").waitUntil().is(":visible");
        $.pause(espera);
    }

    private void selecionarOpcaoSaldo() {
        $("#opcao3").click();
        $.pause(espera);
    }

    private void aguardarTelaSenha() {
        $("#senha").waitUntil().is(":visible");
        $.pause(espera);
    }

    private void digitarSenha() {
        $("#b9").click();
        $.pause(espera/2);
        $("#b5").click();
        $.pause(espera/2);
        $("#b2").click();
        $.pause(espera/2);
        $("#b8").click();
        $.pause(espera/2);
        $("#bEnter").click();
    }

    private String textoEmTela() {
        $.pause(espera*2);
        return $("#tela").text();
    }

}

