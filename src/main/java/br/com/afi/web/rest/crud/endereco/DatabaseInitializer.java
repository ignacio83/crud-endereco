package br.com.afi.web.rest.crud.endereco;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.domain.Usuario;
import br.com.afi.web.rest.crud.endereco.repository.EnderecoUsuarioRepository;
import br.com.afi.web.rest.crud.endereco.repository.UsuarioRepository;

/**
 * Responsável por popular o banco de dados com os dados iniciais.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@Component
public class DatabaseInitializer {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnderecoUsuarioRepository enderecoUsuarioRepository;
	
	@PostConstruct
	@Transactional
	public void setup() {
		final Usuario usuario1 = new Usuario("João da Silva");
		final Usuario usuario2 = new Usuario("Maria Benedita");
		
		usuarioRepository.save(usuario1);
		usuarioRepository.save(usuario2);
		
		final EnderecoUsuario e1 = new EnderecoUsuario(usuario1, "01001001", "Praça da Sé", "1", "São Paulo", "SP");
		final EnderecoUsuario e2 = new EnderecoUsuario(usuario1, "01002001", "Rua Direita", "20", "AP 22", "Sé", "São Paulo", "SP");
		final EnderecoUsuario e3 = new EnderecoUsuario(usuario2, "01001001", "Praça da Sé", "2", "Sé","São Paulo", "SP");
		
		enderecoUsuarioRepository.save(e1);
		enderecoUsuarioRepository.save(e2);
		enderecoUsuarioRepository.save(e3);
	}
}
