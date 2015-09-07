package br.com.afi.web.rest.crud.endereco.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.repository.EnderecoUsuarioRepository;
import br.com.afi.web.rest.crud.endereco.service.UsuarioService;

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
	private UsuarioService usuarioService;
	
	@Autowired
	private EnderecoUsuarioRepository enderecoUsuarioRepository;
	
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
			@ApiResponse(code = 200, response=EnderecoUsuario.class ,message = "Consulta realizada com sucesso"),
			@ApiResponse(code = 599, message = "Erro inesperado")
	})
	@Transactional(readOnly=true)
    public @ResponseBody EnderecoUsuarioTO consulta(
    		@ApiParam(value="Id do endereço", required=true) 
    		@PathVariable("id") Integer id) {

        final EnderecoUsuario enderecoUsuario = enderecoUsuarioRepository.findOne(id);
		return enderecoUsuario!=null ? new EnderecoUsuarioTO(enderecoUsuario) : null;
    }

}
