package br.com.afi.web.rest.crud.endereco.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.integration.BuscaCepIntegrationException;
import br.com.afi.web.rest.crud.endereco.service.EnderecoUsuarioNotFoundException;
import br.com.afi.web.rest.crud.endereco.service.EnderecoUsuarioService;
import br.com.afi.web.rest.crud.endereco.service.InvalidCepException;
import br.com.afi.web.rest.crud.endereco.service.UsuarioNotFoundException;
import br.com.afi.web.rest.crud.endereco.to.AlteraEnderecoUsuarioTO;
import br.com.afi.web.rest.crud.endereco.to.EnderecoUsuarioTO;
import br.com.afi.web.rest.crud.endereco.to.IncluiEnderecoUsuarioTO;
import br.com.afi.web.rest.crud.endereco.to.ValidationErrorTO;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Controlador REST que provê os serviços de inclusão, alteração, remoção e consulta de endereços dos usuários.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@RestController
public class EnderecoUsuarioController {

	@Autowired
	private EnderecoUsuarioService enderecoUsuarioService;
	
	/**
	 * Obtém o endereço do usuário através do Id.
	 * 
	 * @param id Id do endereço
	 * @return Endereço do usuário
	 */
	@RequestMapping(value="/usuario/endereco/{id}", method=RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ApiOperation(value="Obtém o endereço através do Id informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=EnderecoUsuario.class ,message = "Consulta realizada com sucesso")
	})
	@Transactional(readOnly=true)
    public @ResponseBody EnderecoUsuarioTO consulta(
    		@ApiParam(value="Id do endereço", required=true) 
    		@PathVariable("id") Integer id) {

        final EnderecoUsuario enderecoUsuario = enderecoUsuarioService.consulta(id);
		return enderecoUsuario!=null ? new EnderecoUsuarioTO(enderecoUsuario) : null;
    }
	
	/**
	 * Apaga o endereço do usuário.
	 * 
	 * @param id Id do endereço
	 * @return Endereço do usuário
	 * @throws EnderecoUsuarioNotFoundException Caso o endereço não seja encontrado
	 */
	@RequestMapping(value="/usuario/endereco/{id}", method=RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ApiOperation(value="Remove o endereço através do Id informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Endereço removido"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_ENDERECO_NOT_FOUND, message = "Endereço não encontrado")
	})
	@Transactional
    public @ResponseBody void remove(
    		@ApiParam(value="Id do endereço", required=true) 
    		@PathVariable("id") Integer id) throws EnderecoUsuarioNotFoundException {

		enderecoUsuarioService.remove(id);
    }
	
	/**
	 * Altera o endereço do usuário.
	 * 
	 * @param id Id do endereço
	 * @return Endereço alterado
	 * @throws UsuarioNotFoundException Caso o usuário não exista 
	 * @throws EnderecoUsuarioNotFoundException Caso o id informado para o endereço não exista
	 * @throws InvalidCepException Caso o CEP seja inválido
	 * @throws BuscaCepIntegrationException Caso não seja possível se comunicar com o serviço de consulta de CEP
	 */
	@RequestMapping(value="/usuario/endereco/{id}", method=RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE,
	        consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ApiOperation(value="Altera o endereço informado", notes="O CEP informado deve ser válido e o id do endereço deve existir.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Endereço alterado com sucesso"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_VALIDATION_ERROR, response=ValidationErrorTO.class, message = "Os dados informados não são válidos"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_ENDERECO_NOT_FOUND, message = "Endereço não encontrado"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_INVALID_CEP, message = "CEP não encontrado"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_USUARIO_NOT_FOUND, message = "Usuário não encontrado"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_INTEGRATION_FAILED, message = "Falha de comunicação com o serviço de consulta de CEP")
	})
	@Transactional
    public @ResponseBody EnderecoUsuarioTO altera(
    		@ApiParam(value="Id do endereço", required=true) 
    		@PathVariable("id") Integer id,
    		@RequestBody AlteraEnderecoUsuarioTO enderecoUsuario) throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, InvalidCepException, BuscaCepIntegrationException {

		final EnderecoUsuario enderecoAlterado = enderecoUsuarioService.altera(id, enderecoUsuario);
		return new EnderecoUsuarioTO(enderecoAlterado);
    }
	
	/**
	 * Inclui o endereço do usuário.
	 * 
	 * @param enderecoUsuario Dados do endereço
	 * @return Endereço recém incluído
	 * @throws UsuarioNotFoundException Caso o usuário não exista 
	 * @throws InvalidCepException Caso o CEP seja inválido
	 * @throws BuscaCepIntegrationException Caso não seja possível se comunicar com o serviço de consulta de CEP
	 */
	@RequestMapping(value="/usuario/endereco", method=RequestMethod.POST,
	        produces = MediaType.APPLICATION_JSON_VALUE,
	        consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ApiOperation(value="Inclui o endereço informado", notes="O CEP informado deve ser válido.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Endereço incluído com sucesso"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_VALIDATION_ERROR, response=ValidationErrorTO.class, message = "Os dados informados não são válidos"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_INVALID_CEP, message = "CEP não encontrado"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_USUARIO_NOT_FOUND, message = "Usuário não encontrado"),
			@ApiResponse(code = ExceptionResolver.STATUS_CODE_INTEGRATION_FAILED, message = "Falha de comunicação com o serviço de consulta de CEP")
	})
	@Transactional
    public @ResponseBody EnderecoUsuarioTO inclui( 
    		@RequestBody @Valid IncluiEnderecoUsuarioTO enderecoUsuario) throws UsuarioNotFoundException, InvalidCepException, BuscaCepIntegrationException {

		final EnderecoUsuario enderecoIncluido = enderecoUsuarioService.inclui(enderecoUsuario);
		return new EnderecoUsuarioTO(enderecoIncluido);
    }

}
