package tdd.caixaeletronico.negocio.cliente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cpf;

	protected Cliente() { }

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

    public Long getId() {
        return id;
    }

	public String getNome() {
		return this.nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	@Override
	public String toString() {
		return getNome() + " (" + getCpf() + ")";
	}

}
