package br.com.afi.web.rest.crud.endereco.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.afi.web.rest.crud.endereco.domain.Usuario;

/**
 * Repositório de usuários.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
