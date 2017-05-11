package tdd.caixaeletronico.negocio.conta;

import org.springframework.data.repository.Repository;
import tdd.caixaeletronico.negocio.cliente.Cliente;

import java.util.List;

public interface ContaRepository extends Repository<Conta, Long> {

	List<Conta> findAll();

	Conta findByClienteAndCodigo(Cliente cliente, String codigo);

}
