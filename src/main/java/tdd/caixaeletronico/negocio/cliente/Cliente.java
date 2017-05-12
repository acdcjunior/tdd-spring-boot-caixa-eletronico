package tdd.caixaeletronico.negocio.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@SuppressWarnings({"unused", "WeakerAccess"})
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String senha;

	protected Cliente() { }

    public Long getId() {
        return id;
    }
	public String getNome() {
		return this.nome;
    }
	public String getCpf() {
		return this.cpf;
	}
    public String getSenha() {
        return senha;
    }

}
