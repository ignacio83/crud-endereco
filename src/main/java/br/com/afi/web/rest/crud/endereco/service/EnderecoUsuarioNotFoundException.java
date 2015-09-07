package br.com.afi.web.rest.crud.endereco.service;

/**
 * Exceção para casos em que o endereço não é encontrado.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public class EnderecoUsuarioNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Endereço não encontrado";
	private final Integer id;
	
	/**
	 * Construtor.
	 * 
	 * @param id Id do endereço que não foi encontrado.
	 */
	public EnderecoUsuarioNotFoundException(Integer id){
		super(MESSAGE);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
