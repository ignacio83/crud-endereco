package br.com.afi.web.rest.crud.endereco.service;

/**
 * Exceção para casos em que o CEP não é encontrado.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public class InvalidCepException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "CEP não encontrado";
	private final String cep;
	
	/**
	 * Construtor.
	 * 
	 * @param cep CEP que não foi encontrado
	 */
	public InvalidCepException(String cep){
		super(MESSAGE);
		this.cep = cep;
	}

	public String getCep() {
		return cep;
	}
}
