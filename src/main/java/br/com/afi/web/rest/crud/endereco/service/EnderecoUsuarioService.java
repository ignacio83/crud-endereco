package br.com.afi.web.rest.crud.endereco.service;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
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
	 */
	EnderecoUsuario inclui(IncluiEnderecoUsuarioTO to) throws UsuarioNotFoundException;
	
	/**
	 * Altera o endereço informado.
	 * 
	 * @param id Id do endereço
	 * @param to Dados do endereço
	 * @return Endereço alterado
	 * @throws EnderecoUsuarioNotFoundException Caso o id não seja de um endereço existente
	 * @throws UsuarioNotFoundException Caso o usuário informado não exista.
	 */
	EnderecoUsuario altera(Integer id, AlteraEnderecoUsuarioTO to) throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException;
	
	/**
	 * Consulta o endereço através do Id.
	 * 
	 * @param id Id do endereço
	 * @return Endereço, ou null caso nenhum endereço seja encontrado
	 */
	EnderecoUsuario consulta(Integer id);
}
