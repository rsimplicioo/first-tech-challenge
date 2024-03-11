package br.com.squad3.contrato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ContratoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContratoApplication.class, args);
	}

}
