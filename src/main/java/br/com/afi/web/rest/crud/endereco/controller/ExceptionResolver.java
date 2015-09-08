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

import br.com.afi.web.rest.crud.endereco.integration.BuscaCepIntegrationException;
import br.com.afi.web.rest.crud.endereco.integration.InvalidCepException;
import br.com.afi.web.rest.crud.endereco.service.CepNotFoundException;
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
	public static final int STATUS_CODE_INVALID_CEP = 420;
	public static final int STATUS_CODE_INTEGRATION_FAILED = 510;
	public static final int STATUS_CODE_USUARIO_NOT_FOUND = 430;
	public static final int STATUS_CODE_ENDERECO_NOT_FOUND = 431;
	public static final int STATUS_CODE_CEP_NOT_FOUND = 432;

	@ExceptionHandler(EnderecoUsuarioNotFoundException.class)
    public void enderecoUsuarioNotFoundExceptionHandler(Exception exception, HttpServletResponse response) throws IOException {
        response.setStatus(STATUS_CODE_ENDERECO_NOT_FOUND);   
        IOUtils.write(exception.getMessage(), response.getOutputStream());
    }
	
	@ExceptionHandler(UsuarioNotFoundException.class)
    public void usuarioNotFoundExceptionHandler(Exception exception, HttpServletResponse response) throws IOException {
        response.setStatus(STATUS_CODE_USUARIO_NOT_FOUND);   
        IOUtils.write(exception.getMessage(), response.getOutputStream());
    }
	
	@ExceptionHandler(InvalidCepException.class)
    public void invalidCepExceptionHandler(Exception exception, HttpServletResponse response) throws IOException {
        response.setStatus(STATUS_CODE_INVALID_CEP);
        IOUtils.write(exception.getMessage(), response.getOutputStream());
    }
	
	@ExceptionHandler(CepNotFoundException.class)
    public void cepNotFoundExceptionHandler(Exception exception, HttpServletResponse response) throws IOException {
        response.setStatus(STATUS_CODE_CEP_NOT_FOUND);
        IOUtils.write(exception.getMessage(), response.getOutputStream());
    }
	
	@ExceptionHandler(BuscaCepIntegrationException.class)
	public void test(BuscaCepIntegrationException exception, HttpServletResponse response) throws IOException{
		response.setStatus(STATUS_CODE_INTEGRATION_FAILED);
		IOUtils.write(exception.getMessage(), response.getOutputStream());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ResponseEntity<ValidationErrorTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) throws IOException{		
		final ValidationErrorTO validationErrorTO = new ValidationErrorTO(exception);
		return new ResponseEntity<ValidationErrorTO>(validationErrorTO, HttpStatus.BAD_REQUEST);
	}
}
