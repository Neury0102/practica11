package com.example;

import com.example.entidades.Usuario;
import com.example.repositorio.UsuarioRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@SpringBootApplication
public class Practica11Application {
	@Autowired
	//static UsuarioServices usuarioServices;

	public static void main(String[] args) {
		SpringApplication.run(Practica11Application.class, args);

		/*ApplicationContext applicationContext = SpringApplication.run(Practica11Application.class, args);
		String[] lista = applicationContext.getBeanDefinitionNames();
		UsuarioRepository usuarioRepository = (UsuarioRepository) applicationContext.getBean("usuarioRepository");
		Usuario usuario = new Usuario();
		usuario.setNombre("papazon");
		usuario.setApellido("peloton");
		usuario.setCorreo("peloton.ww@gog.com");
		usuario.setPassword("siiiiiiiiiiiii");
		usuario.setUsername("palomo123");
		usuarioRepository.save(usuario);*/
	}
/*	@Service
	public static class MyService {
		public String sayHi() {
			return "Hello Spring Initializr!";
		}

	}

	@Theme("valo")
	@SpringUI(path = "/login")
	public static class VaadinUI extends UI {

		@Autowired
		MyService myService;

		@Override
		protected void init(VaadinRequest request) {
			setContent(new Label(myService.sayHi()));
		}
	}*/
}
