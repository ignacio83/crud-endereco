package br.com.afi.web.rest.crud.endereco.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.integration.BuscaCepClient;
import br.com.afi.web.rest.crud.endereco.integration.BuscaCepIntegrationException;
import br.com.afi.web.rest.crud.endereco.integration.EnderecoTO;
import br.com.afi.web.rest.crud.endereco.integration.InvalidCepException;
import br.com.afi.web.rest.crud.endereco.repository.EnderecoUsuarioRepository;
import br.com.afi.web.rest.crud.endereco.repository.UsuarioRepository;
import br.com.afi.web.rest.crud.endereco.service.CepNotFoundException;
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
	
	@Autowired
	private BuscaCepClient buscaCepClient;

	@Override
	@Transactional
	public void remove(Integer id) throws EnderecoUsuarioNotFoundException {
		final EnderecoUsuario endereco = enderecoUsuarioRepository.findOne(id);
		if(endereco==null){
			throw new EnderecoUsuarioNotFoundException(id);
		}
		enderecoUsuarioRepository.delete(endereco);
		logger.debug("Endereço {} removido",id);
	}

	@Override
	@Transactional
	public EnderecoUsuario inclui(IncluiEnderecoUsuarioTO to) throws UsuarioNotFoundException, InvalidCepException, BuscaCepIntegrationException, CepNotFoundException {
		final String cep = to.getCep();
		validarCep(cep);
		
		EnderecoUsuario enderecoUsuario = to.toEnderecoUsuario(usuarioRepository);		
		enderecoUsuario = enderecoUsuarioRepository.save(enderecoUsuario);
		
		logger.debug("Endereço {} incluído", enderecoUsuario.getId());
		return enderecoUsuario;
	}

	@Override
	@Transactional
	public EnderecoUsuario altera(Integer id, AlteraEnderecoUsuarioTO to) throws UsuarioNotFoundException, EnderecoUsuarioNotFoundException, InvalidCepException, BuscaCepIntegrationException, CepNotFoundException {
		final String cep = to.getCep();
		validarCep(cep);
		
		EnderecoUsuario enderecoUsuario = to.toEnderecoUsuario(id, enderecoUsuarioRepository, usuarioRepository);	
		enderecoUsuario = enderecoUsuarioRepository.save(enderecoUsuario);
		
		logger.debug("Endereço {} alterado", enderecoUsuario.getId());
		return enderecoUsuario;
	}

	/**
	 * Valida o CEP.
	 * 
	 * @param cep CEP
	 * @throws InvalidCepException Caso o CEP seja inválido
	 * @throws BuscaCepIntegrationException Caso não seja possível se comunicar com o serviço de consulta de CEP
	 * @throws CepNotFoundException Caso o CEP não seja encontrado
	 */
	private void validarCep(final String cep) throws InvalidCepException, BuscaCepIntegrationException, CepNotFoundException {
		final EnderecoTO endereco = buscaCepClient.consultaCep(cep);
		if(endereco==null){
			throw new CepNotFoundException(cep);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public EnderecoUsuario consulta(Integer id) {
		 return enderecoUsuarioRepository.findOne(id);
	}
}
