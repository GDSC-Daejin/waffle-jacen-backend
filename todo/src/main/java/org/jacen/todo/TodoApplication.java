package org.jacen.todo;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {
	@Value("${my.server.url}")
	private String serverUrl;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
	public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
		Info info = new Info()
				.title("waffle todo")
				.version(springdocVersion)
				.description("api for waffle todo");
		System.out.println(serverUrl);
		return new OpenAPI()
				.components(new Components())
				.info(info)
				.addServersItem(new Server().url(serverUrl));
	}
}
