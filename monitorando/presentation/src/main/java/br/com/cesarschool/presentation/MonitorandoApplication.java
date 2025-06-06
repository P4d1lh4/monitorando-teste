package br.com.cesarschool.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.cesarschool")
@EntityScan(basePackages = "br.com.cesarschool.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "br.com.cesarschool.infrastructure.repository")
public class MonitorandoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MonitorandoApplication.class, args);
	}
}
