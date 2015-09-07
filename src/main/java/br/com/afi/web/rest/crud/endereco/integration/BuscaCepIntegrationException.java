package br.com.afi.web.rest.crud.endereco.integration;

/**
 * Exceção para casos em que não é possível se comunicar com o serviço REST de consulta de CEP.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public class BuscaCepIntegrationException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Falha de comunicação com o serviço de consulta de CEP";
	private final String endPoint;
	
	/**
	 * Construtor.
	 * 
	 * @param endPoint EndPoint do serviço
	 * @param cause Motivo do erro
	 */
	public BuscaCepIntegrationException(String endPoint, Throwable cause){
		super(MESSAGE,cause);
		this.endPoint = endPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}
}