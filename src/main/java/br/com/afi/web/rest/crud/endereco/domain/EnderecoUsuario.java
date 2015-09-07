package br.com.afi.web.rest.crud.endereco.domain;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa um endereço de um usuário.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@Entity
@Table(name="endereco_usuario")
public class EnderecoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(scale=8)
	private Integer id;
	
	@Column(length=9, nullable=false)
	private String cep;
	
	@Column(length=50,nullable=false)
	private String logradouro;
	
	@Column(length=10,nullable=false)
	private String numero;
	
	@Column(length=30,nullable=true)
	private String bairro;
	
	@Column(length=30,nullable=true)
	private String complemento;
	
	@Column(length=20,nullable=false)
	private String cidade;
	
	@Column(length=2,nullable=false)
	private String uf;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Usuario usuario;
	
	/**
	 * Construtor padrão.
	 */
	public EnderecoUsuario() {
		super();
	}
	
	/**
	 * Instância um novo endereço para o usuário informado.
	 * 
	 * @param usuario Usuário
	 * @param cep CEP
	 * @param logradouro Logradouro
	 * @param numero Numero
	 * @param cidade Cidade
	 * @param uf Unidade Federal 
	 */
	public EnderecoUsuario(Usuario usuario, String cep, String logradouro, String numero, String cidade, String uf) {
		this.usuario = usuario;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.uf = uf;
	}
	
	/**
	 * Instância um novo endereço para o usuário informado.
	 * 
	 * @param usuario Usuário
	 * @param cep CEP
	 * @param logradouro Logradouro
	 * @param numero Numero
	 * @param bairro Bairro
	 * @param cidade Cidade
	 * @param uf Unidade Federal 
	 */
	public EnderecoUsuario(Usuario usuario, String cep, String logradouro, String numero, String bairro, String cidade, String uf) {
		this(usuario,cep,logradouro,numero,cidade,uf);
		this.bairro = bairro;
	}
	
	/**
	 * Instância um novo endereço para o usuário informado.
	 * 
	 * @param usuario Usuário
	 * @param cep CEP
	 * @param logradouro Logradouro
	 * @param numero Numero
	 * @param bairro Bairro
	 * @param complemento Complemento
	 * @param cidade Cidade
	 * @param uf Unidade Federal 
	 */
	public EnderecoUsuario(Usuario usuario, String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String uf) {
		this(usuario,cep,logradouro,numero,bairro,cidade,uf);
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getId() {
		return id;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		EnderecoUsuario other = (EnderecoUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return MessageFormat.format("EnderecoUsuario [id={0}, cep={1}, usuario={2}]", id, cep, usuario);
	}
}
