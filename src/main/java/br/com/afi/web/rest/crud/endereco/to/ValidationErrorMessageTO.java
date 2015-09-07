package br.com.afi.web.rest.crud.endereco.to;

import org.springframework.validation.FieldError;

import br.com.afi.web.rest.crud.endereco.controller.TransferObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Contém as mensagem de erro associadas com o campo incorreto.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@ApiModel(description="Contém as mensagens de erro")
public class ValidationErrorMessageTO implements TransferObject {
		
	@ApiModelProperty("Campo relacionado, se houver")
	private final String campo;
	
	@ApiModelProperty("Mensagem de erro")
	private final String mensagem;
	
	/**
	 * Construtor.
	 * 
	 * @param mensagem Mensagem
	 * @param campo Campo associado
	 */
	public ValidationErrorMessageTO(String mensagem, String campo) {
		this.mensagem = mensagem;
		this.campo = campo;
	}
	
	/**
	 * Construtor.
	 * 
	 * @param mensagem Mensagem
	 */
	public ValidationErrorMessageTO(String mensagem) {
		this.mensagem = mensagem;
		this.campo = null;
	}
	
	/**
	 * Construtor.
	 * 
	 * @param fieldError Erro
	 */
	public ValidationErrorMessageTO(FieldError fieldError) {
		this.mensagem = fieldError.getDefaultMessage();
		this.campo = fieldError.getField();
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getCampo() {
		return campo;
	}
}
