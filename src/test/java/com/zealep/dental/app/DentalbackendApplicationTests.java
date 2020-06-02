package com.zealep.dental.app;

import com.zealep.dental.app.model.entities.Usuario;
import com.zealep.dental.app.model.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DentalbackendApplicationTests {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder enconder;

	/*
	@Test
	void creteUser() {
		Usuario u = new Usuario();
		u.setUsername("zealep");
		u.setPassword(enconder.encode("1234"));
		u.setRol("ADMIN");
		u.setEstado("A");
		Usuario retorno = usuarioRepository.save(u);
		assertTrue(retorno.getPassword().equalsIgnoreCase(u.getPassword()));
	}
*/
}
