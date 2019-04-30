package tdd.caixaeletronico.negocio.cliente;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import tdd.caixaeletronico.negocio.BobId;
import tdd.caixaeletronico.negocio.infra.domainid.DomainIdGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//@GenericGenerator(name = "g2", strategy = DomainIdGenerator.SEQUENCE, parameters = @Parameter(name = "sequence", value = "PUBLIC.CLIENTE_TRES_SEQ"))
public class ClienteTres implements Serializable {

    @Id
//    @GeneratedValue(generator = "g2", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
        name = "assigned-sequence",
        strategy = "tdd.caixaeletronico.negocio.infra.domainid.go.DomainIdSequenceStyleGenerator",
        parameters = @org.hibernate.annotations.Parameter(name = "sequence_name", value = "PUBLIC.CLIENTE_TRES_SEQ")
    )
    @GeneratedValue(generator = "assigned-sequence", strategy = GenerationType.SEQUENCE)
    private BobId id;


	@Column
	private String nome;

	protected ClienteTres() { }

    public ClienteTres(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "##33##{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            '}';
    }

    public void setId(BobId id) {
        this.id = id;
    }
}
