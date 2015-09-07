package br.com.afi.web.rest.crud.endereco.integration;


/**
 * Representa a saúde de um serviço.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public class HealthTO implements RemoteTransferObject {
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}
}
