package tdd.caixaeletronico.negocio.cliente;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClienteTest {

    @Test
    public void construcao() {
        Long id = 99L;
        String nome = "Jose Silva";
        String cpf = "333.666.999-00";
        String senha = "1234";

        Cliente cliente = new Cliente(id, nome, cpf, senha);

        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(senha, cliente.getSenha());
    }

}
