package tdd.caixaeletronico.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tdd.caixaeletronico.negocio.cliente.Cliente;
import tdd.caixaeletronico.negocio.cliente.ClienteRepository;
import tdd.caixaeletronico.negocio.conta.Conta;
import tdd.caixaeletronico.negocio.conta.ContaRepository;
import tdd.caixaeletronico.negocio.conta.SaldoInsuficienteException;
import tdd.caixaeletronico.web.viewmodel.Opcao;
import tdd.caixaeletronico.web.viewmodel.Operacao;

import java.math.BigDecimal;

@Controller
@Transactional
public class SaqueController {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    @Autowired
    public SaqueController(ClienteRepository clienteRepository, ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    @GetMapping("/saque/{idCliente}")
    @ResponseBody
    public Operacao saque(@PathVariable long idCliente, @RequestParam("valor") BigDecimal valor) {
        try {
            Cliente cliente = clienteRepository.findById(idCliente);
            Conta conta = contaRepository.findByCliente(cliente);
            conta.sacar(valor);

            Operacao operacao = new Operacao();
            operacao.setTipo("menu");
            operacao.setTitulo("BANCO TDD<br><br>Saque efetuado com sucesso.<br>Retire o dinheiro na saida indicada.");
            operacao.setOpcao4(new Opcao("Imprimir<br>Comprovante", Opcao.COMPROVANTE));
            operacao.setOpcao8(new Opcao("Sair", Opcao.SAIR));
            return operacao;
        } catch (SaldoInsuficienteException e) {
            Operacao operacao = new Operacao();
            operacao.setTipo("informacao");
            operacao.setTitulo("Saldo insuficiente.<br>Saque nao realizado.<br><br>Operacao cancelada com sucesso.");
            return operacao;
        }
    }

}
