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
public class CaixaControllerSaldoTest {

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
    public void saldo__traz_cliente_e_conta__com_operacao_tipo_relatorio() {
        ResponseEntity<Operacao> entity = this.restTemplate.getForEntity("/saldo/" + clienteId, Operacao.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getTipo()).isEqualTo("relatorio");
        assertThat(entity.getBody().getCliente().getNome()).isEqualTo(cliente.getNome());
        assertThat(entity.getBody().getConta().getCodigo()).isEqualTo(conta.getCodigo());
    }

}
