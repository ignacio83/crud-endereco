package br.com.afi.web.rest.crud.endereco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.afi.web.rest.crud.endereco.repository.EnderecoUsuarioRepository;
import br.com.afi.web.rest.crud.endereco.service.UsuarioService;

/**
 * Implementação das regras de negócio relacionadas ao usuário e aos endereços do mesmo.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private EnderecoUsuarioRepository enderecoUsuarioRepository;

	

}
