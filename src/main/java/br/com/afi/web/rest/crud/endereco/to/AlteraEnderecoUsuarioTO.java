package br.com.afi.web.rest.crud.endereco.to;

import java.text.MessageFormat;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.afi.web.rest.crud.endereco.controller.TransferObject;
import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.domain.Usuario;
import br.com.afi.web.rest.crud.endereco.repository.EnderecoUsuarioRepository;
import br.com.afi.web.rest.crud.endereco.repository.UsuarioRepository;
import br.com.afi.web.rest.crud.endereco.service.EnderecoUsuarioNotFoundException;
import br.com.afi.web.rest.crud.endereco.service.UsuarioNotFoundException;

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
public class AlteraEnderecoUsuarioTO implements TransferObject{
	
	@ApiModelProperty(value="CEP")
	@NotEmpty
	@Length(max=8)
	private String cep;
	
	@ApiModelProperty(value="Rua/Logradouro")
	@NotEmpty
	@Length(max=50)
	private String rua;
	
	@ApiModelProperty(value="Numero")
	@NotEmpty
	@Length(max=10)
	private String numero;
	
	@ApiModelProperty(value="Bairro")
	@Length(max=30)
	private String bairro;
	
	@ApiModelProperty(value="Complemento")
	@Length(max=30)
	private String complemento;
	
	@ApiModelProperty(value="Cidade")
	@NotEmpty
	@Length(max=20)
	private String cidade;
	
	@ApiModelProperty(value="Estado/Unidade Federal")
	@NotEmpty
	@Length(max=2)
	private String estado;
	
	@ApiModelProperty(value="Id do usuário deste endereço")
	@NotNull
	private Integer usuarioId;

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

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	/**
	 * Converte os dados para um {@link EnderecoUsuario}.
	 * 
	 * @param id Id do endereço
	 * @param enderecoUsuarioRepository Repositório de endereços
	 * @param usuarioRepository Repositório de usuários
	 * @return Endereço de um usuário
	 * @throws EnderecoUsuarioNotFoundException Caso o endereço não exista
	 * @throws UsuarioNotFoundException Caso o usuário informado não exista
	 */
	public EnderecoUsuario toEnderecoUsuario(Integer id, EnderecoUsuarioRepository enderecoUsuarioRepository, UsuarioRepository usuarioRepository) throws UsuarioNotFoundException, EnderecoUsuarioNotFoundException{
		EnderecoUsuario enderecoUsuario = enderecoUsuarioRepository.findOne(id);
		if(enderecoUsuario==null){
			throw new EnderecoUsuarioNotFoundException(id);
		}
		
		final Usuario usuario = usuarioRepository.findOne(usuarioId);
		if(usuario==null){
			throw new UsuarioNotFoundException(usuarioId);
		}
		
		enderecoUsuario.setCep(cep);
		enderecoUsuario.setLogradouro(rua);
		enderecoUsuario.setNumero(numero);
		enderecoUsuario.setComplemento(complemento);
		enderecoUsuario.setBairro(bairro);
		enderecoUsuario.setCidade(cidade);
		enderecoUsuario.setUf(estado);
		
		return enderecoUsuario;
	}

	@Override
	public String toString() {
		return MessageFormat.format("AlteraEnderecoUsuarioTO [cep={0}, usuarioId={1}]", cep, usuarioId);
	}
}
