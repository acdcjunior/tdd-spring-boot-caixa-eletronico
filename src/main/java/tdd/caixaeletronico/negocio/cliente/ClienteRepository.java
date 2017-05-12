package tdd.caixaeletronico.negocio.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface ClienteRepository extends Repository<Cliente, Long> {

    Cliente findById(long id);

	Page<Cliente> findAll(Pageable pageable);

	Cliente findByNomeContainingIgnoringCase(String nome);

}
