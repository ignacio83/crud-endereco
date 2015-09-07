package br.com.afi.web.rest.crud.endereco.to;

import java.util.LinkedList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Contém as mensagens de erro e informações em caso de incosistência nos dados informados para o serviço.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@ApiModel(description="Contém as mensagens de erro e informações em caso de incosistência nos dados informados para o serviço")
public class ValidationErrorTO implements TransferObject{
	
	@ApiModelProperty("Mensagens de erro")
	private final List<ValidationErrorMessageTO> erros = new LinkedList<>();
	
	/**
	 * Constrói o objeto já incluindo as mensagens de erro.
	 * 
	 * @param exception Exception
	 */
	public ValidationErrorTO(MethodArgumentNotValidException exception){
		for (final FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			this.erros.add(new ValidationErrorMessageTO(fieldError)); 
		}
	}

	/**
	 * Constrói o objeto já incluindo uma mensagem de erro.
	 * 
	 * @param errorMessage Mensagem de erro
	 */
	public ValidationErrorTO(ValidationErrorMessageTO errorMessage){
		this.erros.add(errorMessage);
	}
	
	/**
	 * Adiciona uma mensagem de erro ao objeto.
	 * 
	 * @param errorMessage Mensagem de erro
	 */
	public void addErrorMessage(ValidationErrorMessageTO errorMessage){
		this.erros.add(errorMessage);
	}
	
	public List<ValidationErrorMessageTO> getErros() {
		return erros;
	}
}
