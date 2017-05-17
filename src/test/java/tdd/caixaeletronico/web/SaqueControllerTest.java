package tdd.caixaeletronico.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import tdd.caixaeletronico.negocio.cliente.Cliente;
import tdd.caixaeletronico.negocio.cliente.ClienteRepository;
import tdd.caixaeletronico.negocio.conta.Conta;
import tdd.caixaeletronico.negocio.conta.ContaRepository;
import tdd.caixaeletronico.web.viewmodel.Operacao;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SaqueControllerTest {

    @Autowired
	private TestRestTemplate restTemplate;

    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ContaRepository contaRepository;

    private final long clienteId = 99L;
    private Cliente cliente;
    private Conta conta;

    @Before
    public void setUp() {
        String nomeCliente = "Joao Silva";
        String cpfCliente = "555.888.999-33";
        cliente = new Cliente(clienteId, nomeCliente, cpfCliente, "9528");
        given(this.clienteRepository.findById(clienteId)).willReturn(cliente);
        conta = new Conta("1144-X", "99999-9", "CC", cliente, new BigDecimal(5000));
        given(contaRepository.findByCliente(cliente)).willReturn(conta);
    }

    @Test
    public void saque_de_valor_menor_que_o_saldo_traz_menu_com_mensagem_de_sucesso() {
        ResponseEntity<Operacao> entity = this.restTemplate.getForEntity("/saque/" + clienteId + "?valor=1000", Operacao.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getTipo()).isEqualTo("menu");
        assertThat(entity.getBody().getTitulo()).contains("sucesso");
    }

    @Test
    public void saque_de_valor_MAIOR_que_o_saldo_traz_informacao_com_mensagem_de_cancelamento() {
        ResponseEntity<Operacao> entity = this.restTemplate.getForEntity("/saque/" + clienteId + "?valor=9999", Operacao.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getTipo()).isEqualTo("informacao");
        assertThat(entity.getBody().getTitulo()).contains("insuficiente", "cancelada");
    }

}
