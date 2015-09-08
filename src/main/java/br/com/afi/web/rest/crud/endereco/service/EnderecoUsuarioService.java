package br.com.afi.web.rest.crud.endereco.service;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.integration.BuscaCepIntegrationException;
import br.com.afi.web.rest.crud.endereco.integration.InvalidCepException;
import br.com.afi.web.rest.crud.endereco.to.AlteraEnderecoUsuarioTO;
import br.com.afi.web.rest.crud.endereco.to.IncluiEnderecoUsuarioTO;


/**
 * Regras de negócio relacionadas aos endereços dos usuários.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public interface EnderecoUsuarioService {

	/**
	 * Remove o endereço com id informado.
	 * 
	 * @param id Id do endereço
	 * @throws EnderecoUsuarioNotFoundException Caso o endereço não seja encontrado.
	 */
	void remove(Integer id) throws EnderecoUsuarioNotFoundException;
	
	/**
	 * Inclui o endereço informado.
	 * 
	 * @param to Dados do endereço
	 * @return Endereço incluído
	 * @throws UsuarioNotFoundException Caso o usuário informado não exista.
	 * @throws InvalidCepException Caso o CEP não seja válido
	 * @throws BuscaCepIntegrationException Caso não seja possível se comunicar com o serviço de consulta de CEP
	 * @throws CepNotFoundException Caso o CEP não seja encontrado
	 */
	EnderecoUsuario inclui(IncluiEnderecoUsuarioTO to) throws UsuarioNotFoundException, InvalidCepException, BuscaCepIntegrationException, CepNotFoundException;
	
	/**
	 * Altera o endereço informado.
	 * 
	 * @param id Id do endereço
	 * @param to Dados do endereço
	 * @return Endereço alterado
	 * @throws EnderecoUsuarioNotFoundException Caso o id não seja de um endereço existente
	 * @throws UsuarioNotFoundException Caso o usuário informado não exista.
	 * @throws InvalidCepException Caso o CEP não seja válido
	 * @throws BuscaCepIntegrationException Caso não seja possível se comunicar com o serviço de consulta de CEP
	 * @throws CepNotFoundException Caso o CEP não seja encontrado
	 */
	EnderecoUsuario altera(Integer id, AlteraEnderecoUsuarioTO to) throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, InvalidCepException, BuscaCepIntegrationException, CepNotFoundException;
	
	/**
	 * Consulta o endereço através do Id.
	 * 
	 * @param id Id do endereço
	 * @return Endereço, ou null caso nenhum endereço seja encontrado
	 */
	EnderecoUsuario consulta(Integer id);
}
