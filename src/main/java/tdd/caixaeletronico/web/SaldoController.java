package tdd.caixaeletronico.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import tdd.caixaeletronico.negocio.cliente.Cliente;
import tdd.caixaeletronico.negocio.cliente.ClienteRepository;
import tdd.caixaeletronico.negocio.conta.ContaRepository;
import tdd.caixaeletronico.web.viewmodel.Opcao;
import tdd.caixaeletronico.web.viewmodel.Operacao;

@Controller
@Transactional
public class SaldoController {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    @Autowired
    public SaldoController(ClienteRepository clienteRepository, ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    @GetMapping("/saldo/{idCliente}")
    @ResponseBody
    public Operacao saldo(@PathVariable long idCliente) {
        Operacao operacao = new Operacao();
        operacao.setTipo("relatorio");

        Cliente cliente = clienteRepository.findById(idCliente);
        operacao.setCliente(cliente);
        operacao.setConta(contaRepository.findByCliente(cliente));

        operacao.setOpcao4(new Opcao("Imprimir<br>Comprovante", Opcao.COMPROVANTE));
        operacao.setOpcao8(new Opcao("Sair", Opcao.SAIR));
        return operacao;
    }

}
