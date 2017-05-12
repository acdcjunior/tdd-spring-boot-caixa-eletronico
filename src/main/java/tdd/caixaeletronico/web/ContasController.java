package tdd.caixaeletronico.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tdd.caixaeletronico.negocio.cliente.ClienteRepository;
import tdd.caixaeletronico.negocio.conta.Conta;
import tdd.caixaeletronico.negocio.conta.ContaRepository;

import java.util.List;

@Controller
public class ContasController {

	private final ClienteRepository clienteRepository;
	private final ContaRepository contaRepository;

    @Autowired
    public ContasController(ClienteRepository clienteRepository, ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    @GetMapping("/cliente")
	@ResponseBody
	@Transactional(readOnly = true)
	public String cliente() {
		return this.clienteRepository.findByNomeContainingIgnoringCase("Jr").getNome();
	}

	@GetMapping("/contas")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Conta> contas() {
		return this.contaRepository.findAll();
	}

}
