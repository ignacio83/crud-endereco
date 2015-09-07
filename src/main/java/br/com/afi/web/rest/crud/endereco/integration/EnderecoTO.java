package br.com.afi.web.rest.crud.endereco.integration;

import java.text.MessageFormat;

/**
 * Representa um endereço. Não é vinculado a um cliente.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public class EnderecoTO implements RemoteTransferObject {
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	
	/**
	 * Construtor padrão.
	 */
	public EnderecoTO() {
		super();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoTO other = (EnderecoTO) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return MessageFormat.format("Endereco [cep={0}, logradouro={1}, bairro={2}, cidade={3}, uf={4}]",cep, logradouro, bairro, cidade, uf);
	}
}
