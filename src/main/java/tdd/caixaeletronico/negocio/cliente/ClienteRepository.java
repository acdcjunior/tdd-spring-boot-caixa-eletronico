package tdd.caixaeletronico.negocio.cliente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends Repository<Cliente, Long> {

	List<Cliente> findAll();

    Cliente findById(long id);

    @Query("select c from Cliente c where "
        + "(:xcpf is null or c.cpf = :xcpf)")
    List<Cliente> fff(@Param("xcpf") Optional<String> xcpf);

    @Query("select c from Cliente c where "
        + "(c.cid = :a)")
    List<Cliente> xxx(@Param("a") ClienteId a);

    Cliente findByCid(ClienteId x);

}
