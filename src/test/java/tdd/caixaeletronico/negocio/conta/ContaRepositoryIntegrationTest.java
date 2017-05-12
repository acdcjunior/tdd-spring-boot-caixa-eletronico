package tdd.caixaeletronico.negocio.conta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tdd.caixaeletronico.negocio.cliente.ClienteRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaRepositoryIntegrationTest {

	@Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

	@Test
	public void findAll__executa_consulta_ao_BD_corretamente() {
        List<Conta> contas = contaRepository.findAll();
		assertThat(contas).hasSize(2);
		assertThat(contas.get(0).getCodigo()).isEqualTo("65893-0");
		assertThat(contas.get(1).getCodigo()).isEqualTo("51112-0");
	}

	@Test
	public void findByCliente__executa_consulta_ao_BD_corretamente() {
        Conta conta = contaRepository.findByCliente(clienteRepository.findById(1L));
		assertThat(conta.getCodigo()).isEqualTo("65893-0");
	}

}
