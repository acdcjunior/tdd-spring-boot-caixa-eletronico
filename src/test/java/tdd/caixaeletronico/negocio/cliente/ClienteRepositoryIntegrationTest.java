package tdd.caixaeletronico.negocio.cliente;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryIntegrationTest {

	@Autowired
    private ClienteRepository clienteRepository;

	@Test
	public void traz_primeira_pagina_de_clientes() {
		Page<Cliente> clientes = this.clienteRepository.findAll(new PageRequest(0, 1));
		assertThat(clientes.getTotalElements()).isGreaterThan(1L);
	}

}
