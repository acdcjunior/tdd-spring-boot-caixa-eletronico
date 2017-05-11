package tdd.caixaeletronico.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
public class CaixaController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "atm";
	}

    @GetMapping("/iniciar/{idCliente}")
    @ResponseBody
    @Transactional(readOnly = true)
    public Menu contas(@PathVariable long idCliente) {
        Menu menu = new Menu();
        menu.setTitulo("Menu do cliente "+ idCliente);
        menu.addOpcao(new OpcaoMenu("Saldo", new Url("/saldo")));
        menu.addOpcao(new OpcaoMenu("Saque", new Url("/saque")));
        menu.addOpcao(new OpcaoMenu("Financiamento", new Url("/financiamento")));
        return menu;
    }

	@RequestMapping("/bang")
	public String bang() {
		throw new RuntimeException("Boom");
	}

}
