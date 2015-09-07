package br.com.afi.web.rest.crud.endereco.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa um usuário.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(scale=8)
	private Integer id;
	
	@Column(length=50,nullable=false)
	private String nome;
	
	/**
	 * Construtor padrão.
	 */
	public Usuario() {
		super();
	}
	
	/**
	 * Construtor.
	 * 
	 * @param nome Nome do usuário 
	 */
	public Usuario(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Construtor.
	 * 
	 * @param id Id do usuário
	 * @param nome Nome do usuário 
	 */
	public Usuario(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + "]";
	}
}
