package br.com.afi.web.rest.crud.endereco.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.repository.EnderecoUsuarioRepository;
import br.com.afi.web.rest.crud.endereco.repository.UsuarioRepository;
import br.com.afi.web.rest.crud.endereco.service.EnderecoUsuarioNotFoundException;
import br.com.afi.web.rest.crud.endereco.service.EnderecoUsuarioService;
import br.com.afi.web.rest.crud.endereco.service.UsuarioNotFoundException;
import br.com.afi.web.rest.crud.endereco.to.AlteraEnderecoUsuarioTO;
import br.com.afi.web.rest.crud.endereco.to.IncluiEnderecoUsuarioTO;

/**
 * Implementação das regras de negócio relacionadas aos endereços dos usuários.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@Service
public class EnderecoUsuarioServiceImpl implements EnderecoUsuarioService {
	private final Logger logger = LoggerFactory.getLogger(EnderecoUsuarioServiceImpl.class);
	
	@Autowired
	private EnderecoUsuarioRepository enderecoUsuarioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public void removeEndereco(Integer id) throws EnderecoUsuarioNotFoundException {
		final EnderecoUsuario endereco = enderecoUsuarioRepository.findOne(id);
		if(endereco==null){
			throw new EnderecoUsuarioNotFoundException(id);
		}
		enderecoUsuarioRepository.delete(endereco);
		logger.debug("Endereço {} removido",id);
	}

	@Override
	public EnderecoUsuario inclui(IncluiEnderecoUsuarioTO to) throws UsuarioNotFoundException {
		EnderecoUsuario enderecoUsuario = to.toEnderecoUsuario(usuarioRepository);		
		enderecoUsuario = enderecoUsuarioRepository.save(enderecoUsuario);
		
		logger.debug("Endereço {} incluído", enderecoUsuario.getId());
		return enderecoUsuario;
	}

	@Override
	public EnderecoUsuario altera(Integer id, AlteraEnderecoUsuarioTO to) throws UsuarioNotFoundException, EnderecoUsuarioNotFoundException {
		EnderecoUsuario enderecoUsuario = to.toEnderecoUsuario(id, enderecoUsuarioRepository, usuarioRepository);		
		enderecoUsuario = enderecoUsuarioRepository.save(enderecoUsuario);
		
		logger.debug("Endereço {} alterado", enderecoUsuario.getId());
		return enderecoUsuario;
	}
}
