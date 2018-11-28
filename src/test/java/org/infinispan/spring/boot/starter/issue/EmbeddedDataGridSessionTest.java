package org.infinispan.spring.boot.starter.issue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { DataGridSessionAutoConfiguration.class })
@EnableAutoConfiguration
public class EmbeddedDataGridSessionTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void remembersGreetingsName() {
        ResponseEntity<String> response = this.testRestTemplate.getForEntity("/greeting?name=John", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Hello John");

		response = this.testRestTemplate.getForEntity("/greeting", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Hello John");
	}

}
