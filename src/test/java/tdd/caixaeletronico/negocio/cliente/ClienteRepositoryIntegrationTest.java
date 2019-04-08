package tdd.caixaeletronico.negocio.cliente;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tdd.caixaeletronico.negocio.BobId;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryIntegrationTest {

	@Autowired
    private ClienteRepository clienteRepository;

	@Autowired
    private ClienteDoisRepository clienteDoisRepository;

    @Test
    public void findAll__executa_consulta_ao_BD_corretamente() {
        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(2);
        assertThat(clientes.get(0).getSenha()).isEqualTo("1759");
        assertThat(clientes.get(1).getSenha()).isEqualTo("4463");
    }

    @Test
    public void x() {
        System.out.println("### -> " + clienteRepository.findById(1L));
        System.out.println("### -> " + clienteRepository.findByCid(new ClienteId(22L)));
        System.out.println("### -> " + clienteRepository.xxx(new ClienteId(22L)));

        System.out.println("### -> " + clienteDoisRepository.findAll());
        System.out.println("### -> " + clienteDoisRepository.findById(new BobId(2L)));
//        System.out.println("### -> " + clienteDoisRepository.findById(2L));

        ClienteDois bob = new ClienteDois("bob");
        bob.setId(new BobId(986L));
        clienteDoisRepository.save(bob);
        System.out.println("### 2 -> " + clienteDoisRepository.findAll());

        clienteDoisRepository.save(new ClienteDois("bob8888"));
        System.out.println("### 3 -> " + clienteDoisRepository.findAll());
    }

    @Test
    public void findById__executa_consulta_ao_BD_corretamente() {
        System.out.println(clienteRepository.fff(Optional.of("111")).toString());
        System.out.println(clienteRepository.fff(Optional.of("222")).toString());
        //System.out.println(clienteRepository.findByCpf(Optional.of(null)).toString());
        System.out.println(clienteRepository.fff(Optional.empty()).toString());
    }

}
