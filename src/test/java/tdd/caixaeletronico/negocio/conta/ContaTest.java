package tdd.caixaeletronico.negocio.conta;

import org.junit.Before;
import org.junit.Test;
import tdd.caixaeletronico.negocio.cliente.Cliente;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ContaTest {

    private String agencia;
    private String codigo;
    private String tipo;
    private Cliente cliente;
    private BigDecimal saldo;
    private Conta conta;

    @Before
    public void setUp() throws Exception {
        codigo = "99999-9";
        tipo = "CC";
        cliente = new Cliente(0L, "nao importa", "nao importa", "nao importa");
        saldo = new BigDecimal(5000);
        agencia = "1144-X";

        conta = new Conta(agencia, codigo, tipo, cliente, saldo);
    }

    @Test
    public void construcao() {
        assertEquals(agencia, conta.getAgencia());
        assertEquals(codigo, conta.getCodigo());
        assertEquals(tipo, conta.getTipo());
        assertEquals(cliente, conta.getCliente());
        assertEquals(saldo, conta.getSaldo());
    }

    @Test
    public void saque_deve_subtrair_do_saldo() {
        conta.sacar(new BigDecimal(3000));
        assertEquals(new BigDecimal(2000), conta.getSaldo());
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void saque_deve_lancar_excecao__se_montante_sacado_foi_maior_que_saldo() {
        conta.sacar(new BigDecimal(9000));
    }

}
