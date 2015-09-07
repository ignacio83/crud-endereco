package br.com.afi.web.rest.crud.endereco.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;

/**
 * Repositório de endereços de usuários.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public interface EnderecoUsuarioRepository extends CrudRepository<EnderecoUsuario, Integer> {

}
