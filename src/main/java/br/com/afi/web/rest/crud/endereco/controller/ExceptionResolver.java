package br.com.afi.web.rest.crud.endereco.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.afi.web.rest.crud.endereco.service.EnderecoUsuarioNotFoundException;
import br.com.afi.web.rest.crud.endereco.service.UsuarioNotFoundException;
import br.com.afi.web.rest.crud.endereco.to.ValidationErrorTO;

/**
 * Exception Handler que indica o que irá ocorrer para cada uma das exceções.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionResolver {
	public static final int STATUS_CODE_VALIDATION_ERROR = 400;
	public static final int STATUS_CODE_USUARIO_NOT_FOUND = 420;
	public static final int STATUS_CODE_ENDERECO_NOT_FOUND = 421;

	@ExceptionHandler(EnderecoUsuarioNotFoundException.class)
    public void enderecoUsuarioNotFoundExceptionHandler(Exception exception, HttpServletResponse response) throws IOException {
        response.setStatus(STATUS_CODE_ENDERECO_NOT_FOUND);   
        IOUtils.write(exception.getMessage(), response.getOutputStream());
    }
	
	@ExceptionHandler(UsuarioNotFoundException.class)
    public void usuarioNotFoundExceptionHandler(Exception exception, HttpServletResponse response) throws IOException {
        response.setStatus(STATUS_CODE_ENDERECO_NOT_FOUND);   
        IOUtils.write(exception.getMessage(), response.getOutputStream());
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ResponseEntity<ValidationErrorTO> test(MethodArgumentNotValidException exception) throws IOException{		
		final ValidationErrorTO validationErrorTO = new ValidationErrorTO(exception);
		return new ResponseEntity<ValidationErrorTO>(validationErrorTO, HttpStatus.BAD_REQUEST);
	}
}
