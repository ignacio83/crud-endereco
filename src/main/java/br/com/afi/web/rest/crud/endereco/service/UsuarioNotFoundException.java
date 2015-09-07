package br.com.afi.web.rest.crud.endereco.service;

/**
 * Exceção para casos em que o usuário não é encontrado.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public class UsuarioNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Usuário não encontrado";
	private final Integer id;
	
	/**
	 * Construtor.
	 * 
	 * @param id Id do usuário que não foi encontrado.
	 */
	public UsuarioNotFoundException(Integer id){
		super(MESSAGE);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
