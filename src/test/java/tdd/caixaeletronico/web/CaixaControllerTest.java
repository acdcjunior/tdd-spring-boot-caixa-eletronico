package tdd.caixaeletronico.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CaixaControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testMustacheTemplate() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("TDD + Caixa");
	}

	@Test
	public void testMustacheErrorTemplate() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("/does-not-exist", HttpMethod.GET, requestEntity, String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseEntity.getBody())
				.contains("Algo deu errado: 404 Not Found");
	}

    @Test
    public void contas() throws Exception {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/contas", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("\"saldo\":50.00");
    }

}
