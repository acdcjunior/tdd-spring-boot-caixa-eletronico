package tdd.caixaeletronico.negocio.conta;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;
import tdd.caixaeletronico.negocio.cliente.Cliente;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaRepositoryIntegrationTest {

	@Autowired
    private ContaRepository contaRepository;

	@Test
	public void findAll__executa_consulta_ao_BD_corretamente() {
        List<Conta> contas = contaRepository.findAll();
		assertThat(contas).hasSize(2);
	}

}
