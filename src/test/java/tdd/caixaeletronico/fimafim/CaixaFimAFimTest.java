package tdd.caixaeletronico.fimafim;

import org.assertj.core.api.Assertions;
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
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaixaFimAFimTest {

    @LocalServerPort
    private int port;

    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ContaRepository contaRepository;

    @Before
    public void setUp() {
        // $.driver().useChrome(); // para o chrome funcionar, eh preciso o chromedriver.exe no path
    }

    @After
    public void tearDown() {
        $.driver().quit();
    }

    @Test
    public void menu_contem_dados_do_cliente() {
        // setup
        String nomeCliente = "Joao Silva";
        String cpfCliente = "555.888.999-33";
        Cliente cliente = new Cliente(99L, nomeCliente, cpfCliente, "9988");
        given(this.clienteRepository.findById(1)).willReturn(cliente);
        Conta conta = new Conta("1144-X", "99999-9", "CC", cliente, new BigDecimal(5000));
        given(contaRepository.findByCliente(cliente)).willReturn(conta);
        // execute
        $.url("http://127.0.0.1:" + port + "/");
        $("#cartao").waitUntil().is(":visible").then().click();
        $("#menu-titulo").waitUntil().is(":visible");
        String textoEmTela = $("#tela").text();
        // verify
        Assertions.assertThat(textoEmTela).contains("SELECIONE A OPERACAO DESEJADA", nomeCliente.toUpperCase(), cpfCliente);
    }

}

