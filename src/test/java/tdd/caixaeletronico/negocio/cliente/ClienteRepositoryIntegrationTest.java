package tdd.caixaeletronico.negocio.cliente;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryIntegrationTest {

	@Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void findAll__executa_consulta_ao_BD_corretamente() {
        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(2);
        assertThat(clientes.get(0).getSenha()).isEqualTo("1759");
        assertThat(clientes.get(1).getSenha()).isEqualTo("4463");
    }

    @Test
    public void findById__executa_consulta_ao_BD_corretamente() {
        Cliente cliente = clienteRepository.findById(2L);
        assertThat(cliente.getCpf()).isEqualTo("111.333.777-99");
    }

}
