package tdd.caixaeletronico.negocio.conta;

import tdd.caixaeletronico.negocio.cliente.Cliente;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@SuppressWarnings({"unused", "WeakerAccess"})
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

    @Column(nullable = false)
    private String agencia;

	@Column(nullable = false)
	private String codigo;

	@Column(nullable = false)
	private String tipo;

    @ManyToOne(optional = false)
    private Cliente cliente;

	@Column(nullable = false)
	private BigDecimal saldo;

	protected Conta() { }

    public Conta(String agencia, String codigo, String tipo, Cliente cliente, BigDecimal saldo) {
        this.agencia = agencia;
        this.codigo = codigo;
        this.tipo = tipo;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public String getAgencia() {
        return this.agencia;
    }

	public String getCodigo() {
		return this.codigo;
	}

    public String getTipo() {
        return tipo;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

	public BigDecimal getSaldo() {
		return this.saldo;
	}

    public void sacar(BigDecimal montanteSacado) {
        BigDecimal novoSaldo = this.saldo.subtract(montanteSacado);
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException();
        }
        this.saldo = novoSaldo;
    }
}
