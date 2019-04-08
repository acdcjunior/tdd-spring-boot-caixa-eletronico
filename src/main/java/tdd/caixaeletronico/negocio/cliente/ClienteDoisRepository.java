package tdd.caixaeletronico.negocio.cliente;

import org.springframework.data.repository.CrudRepository;
import tdd.caixaeletronico.negocio.BobId;

import java.util.List;

public interface ClienteDoisRepository extends CrudRepository<ClienteDois, BobId> {

	List<ClienteDois> findAll();

}
