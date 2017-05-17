package tdd.caixaeletronico.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import tdd.caixaeletronico.negocio.cliente.ClienteRepository;
import tdd.caixaeletronico.web.viewmodel.Opcao;
import tdd.caixaeletronico.web.viewmodel.Operacao;

@Controller
@Transactional(readOnly = true)
public class MenuController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public MenuController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:atm.html";
    }

    @GetMapping("/menu/{idCliente}")
    @ResponseBody
    public Operacao menu(@PathVariable long idCliente) {
        Operacao operacao = new Operacao();
        operacao.setTipo("menu");
        operacao.setTitulo("BANCO TDD<br>SELECIONE A OPERACAO DESEJADA");

        operacao.setCliente(clienteRepository.findById(idCliente));

        operacao.setOpcao3(new Opcao("Saldo", "/saldo/" + idCliente));
        operacao.setOpcao4(new Opcao("Saque", "/saque/" + idCliente));
        operacao.setOpcao7(new Opcao("Financiamento", "/financiamento/" + idCliente));
        operacao.setOpcao8(new Opcao("Sair", Opcao.SAIR));
        return operacao;
    }

}
