package tdd.caixaeletronico.negocio.cliente;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import tdd.caixaeletronico.negocio.BobId;
import tdd.caixaeletronico.negocio.infra.domainid.DomainIdGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@IdClass(ClienteDoisPK.class)
//@SequenceGenerator(name="cliente_dois_sequ", sequenceName="cliente_dois_sequ", allocationSize=1)
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="cliente_dois_sequ")
@GenericGenerator(name = "generator", strategy = DomainIdGenerator.SEQUENCE, parameters = @Parameter(name = "sequence", value = "PUBLIC.cliente_dois_sequ"))
public class ClienteDois implements Serializable {

    @Id
    @GeneratedValue(generator = "generator")
    // todo testar outra classe com o generator com nome igual a este
    private BobId id;


	@Column
	private String nome;

	protected ClienteDois() { }

    public ClienteDois(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ClienteDois{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            '}';
    }

    public void setId(BobId id) {
        this.id = id;
    }
}
