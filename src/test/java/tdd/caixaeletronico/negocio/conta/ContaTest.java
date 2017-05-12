package tdd.caixaeletronico.negocio.conta;

import org.junit.Test;
import tdd.caixaeletronico.negocio.cliente.Cliente;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ContaTest {

    @Test
    public void construcao() {
        String agencia = "1144-X";
        String codigo = "99999-9";
        String tipo = "CC";
        Cliente cliente = new Cliente(0L, "nao importa", "nao importa", "nao importa");
        BigDecimal saldo = new BigDecimal(5000);

        Conta conta = new Conta(agencia, codigo, tipo, cliente, saldo);

        assertEquals(agencia, conta.getAgencia());
        assertEquals(codigo, conta.getCodigo());
        assertEquals(tipo, conta.getTipo());
        assertEquals(cliente, conta.getCliente());
        assertEquals(saldo, conta.getSaldo());
    }

}
