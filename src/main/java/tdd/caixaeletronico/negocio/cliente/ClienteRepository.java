package tdd.caixaeletronico.negocio.cliente;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface ClienteRepository extends Repository<Cliente, Long> {

	List<Cliente> findAll();

    Cliente findById(long id);

}
