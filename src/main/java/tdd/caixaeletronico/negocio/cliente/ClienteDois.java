package tdd.caixaeletronico.negocio.cliente;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import tdd.caixaeletronico.negocio.BobId;
import tdd.caixaeletronico.negocio.infra.domainid.DomainIdGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@IdClass(ClienteDoisPK.class)
//@SequenceGenerator(name="cliente_dois_sequ", sequenceName="cliente_dois_sequ", allocationSize=1)
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="cliente_dois_sequ")
@GenericGenerator(
    name = "generator",
    strategy = "tdd.caixaeletronico.negocio.infra.domainid.go.DomainIdSequenceStyleGenerator",
    parameters = @org.hibernate.annotations.Parameter(name = "sequence_name", value = "PUBLIC.cliente_dois_sequ")
)
public class ClienteDois implements Serializable {

    @Id
    @GeneratedValue(generator = "generator", strategy = GenerationType.SEQUENCE)
    // todo testar outra classe com o generator com nome igual a este
    private BobId id;


	@Column
	private String nome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DOIZ", nullable = false)
    private Set<ClienteTres> clientesTres;

	protected ClienteDois() { }

    public ClienteDois(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ClienteDois{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", clientesTres=" +clientesTres +
            '}';
    }

    public void setId(BobId id) {
        this.id = id;
    }

    public void add(ClienteTres clienteTres) {
        if (this.clientesTres == null) {
            this.clientesTres = new HashSet<>();
        }
        this.clientesTres.add(clienteTres);
    }
}
