package br.com.afi.web.rest.crud.endereco.to;

import java.text.MessageFormat;

import br.com.afi.web.rest.crud.endereco.controller.TransferObject;
import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.domain.Usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Representação do endereço do usuário no retorno do serviço REST.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@ApiModel(description="Endereço de um usuário")
@JsonInclude(Include.NON_NULL)
public class EnderecoUsuarioTO implements TransferObject{

	@ApiModelProperty(value="Id do endereço")
	private final Integer id;
	
	@ApiModelProperty(value="CEP")
	private final String cep;
	
	@ApiModelProperty(value="Rua/Logradouro")
	private final String rua;
	
	@ApiModelProperty(value="Numero")
	private final String numero;
	
	@ApiModelProperty(value="Bairro")
	private final String bairro;
	
	@ApiModelProperty(value="Complemento")
	private final String complemento;
	
	@ApiModelProperty(value="Cidade")
	private final String cidade;
	
	@ApiModelProperty(value="Estado/Unidade Federal")
	private final String estado;
	
	@ApiModelProperty(value="Id do usuário deste endereço")
	private final Integer usuarioId;
	
	/**
	 * Constrói o objeto a partir de um {@link EnderecoUsuario}.
	 * 
	 * @param enderecoUsuario Endereço do usuário
	 */
	public EnderecoUsuarioTO(EnderecoUsuario enderecoUsuario){
		this.id = enderecoUsuario.getId();
		this.cep = enderecoUsuario.getCep();
		this.rua = enderecoUsuario.getLogradouro();
		this.numero = enderecoUsuario.getNumero();
		this.bairro = enderecoUsuario.getBairro();
		this.complemento = enderecoUsuario.getComplemento();
		this.cidade = enderecoUsuario.getCidade();
		this.estado = enderecoUsuario.getUf();
		
		final Usuario usuario = enderecoUsuario.getUsuario();
		this.usuarioId = usuario!=null ? usuario.getId() : null;
	}

	public Integer getId() {
		return id;
	}

	public String getCep() {
		return cep;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	@Override
	public String toString() {
		return MessageFormat.format("EnderecoUsuarioTO [id={0}, cep={1}, usuarioId={2}]", id, cep, usuarioId);
	}
}
