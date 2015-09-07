package br.com.afi.web.rest.crud.endereco.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.domain.Usuario;
import br.com.afi.web.rest.crud.endereco.repository.EnderecoUsuarioRepository;
import br.com.afi.web.rest.crud.endereco.repository.UsuarioRepository;
import br.com.afi.web.rest.crud.endereco.service.impl.EnderecoUsuarioServiceImpl;
import br.com.afi.web.rest.crud.endereco.to.AlteraEnderecoUsuarioTO;
import br.com.afi.web.rest.crud.endereco.to.IncluiEnderecoUsuarioTO;

/**
 * Testes unitários para consulta, alteração, criação e remoção de um endereço.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class EnderecoUsuarioServiceTest {
	private final static String CEP_VALIDO = "01001123";
	private final static String CEP_VALIDO_2 = "01001124";
	private static final int USUARIO_ID = 1;
	private final Integer ENDERECO_ID = 1;
	private final Integer NOVO_ENDERECO_ID = 2;
	private final Dado dado = new Dado();
	private final Quando quando = new Quando();
	private final Entao entao = new Entao();
	private EnderecoUsuario enderecoUsuario;
	private Usuario usuario;
	
	@Mock
	private EnderecoUsuarioRepository enderecoUsuarioRepository;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private EnderecoUsuarioService enderecoUsuarioService = new EnderecoUsuarioServiceImpl();
	
	@Before
	public void clear(){
		enderecoUsuario = null;
		usuario = null;
		Mockito.reset(enderecoUsuarioRepository);
		Mockito.reset(usuarioRepository);
		
		when(enderecoUsuarioRepository.save(any(EnderecoUsuario.class))).thenAnswer(new Answer<EnderecoUsuario>() {

			@Override
			public EnderecoUsuario answer(InvocationOnMock invocation) throws Throwable {
				final EnderecoUsuario u = (EnderecoUsuario) invocation.getArguments()[0];
				if(u.getId()==null){
					u.setId(NOVO_ENDERECO_ID);
				}
				return u;
			}
			
		});
	}
	
	/**
	 * Ao consultar um endereço pelo ID o mesmo deve ser retornado.
	 */
	@Test
	public void testConsulta(){
		dado.umUsuarioCadastrado();
		dado.umEnderecoCadastrado();
		quando.consultaPeloId();
		entao.deveRetornarOEnderecoCorrespondente();
	}
	
	/**
	 * Ao excluir o endereço, os dados devem ser removidos do banco de dados.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 */
	@Test
	public void testRemocao() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
		dado.umUsuarioCadastrado();
		dado.umEnderecoCadastrado();
		quando.removeOEndereco();
		entao.deveSerRemovidoDoBancoDeDados();
	}
	
	/**
	 * Ao excluir o endereço que não existe, uma exceção deve ser lançada.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 */
	@Test(expected=EnderecoUsuarioNotFoundException.class)
	public void testRemocaoEnderecoNaoCadastrado() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
		quando.removeOEndereco();
	}
	
	/**
	 * Ao alterar o endereço, os dados devem ser alterado no banco de dados.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 */
	@Test
	public void testAlteracao() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
		dado.umUsuarioCadastrado();
		dado.umEnderecoCadastrado();
		quando.alteraOEndereco();
		entao.deveSerAlteradoNoBancoDeDados();
	}
	
	/**
	 * Ao alterar o endereço para um usuário que não existe, uma exceção deve ser lançada.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 */
	@Test(expected=UsuarioNotFoundException.class)
	public void testAlteracaoUsuarioInexistente() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
		dado.umEnderecoCadastrado();
		quando.alteraOEnderecoParaUmUsuarioInexistente();
	}
	
	/**
	 * Ao alterar o endereço que não existe, uma exceção deve ser lançada.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 */
	@Test(expected=EnderecoUsuarioNotFoundException.class)
	public void testAlteracaoEnderecoInexistente() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
		quando.alteraOEndereco();
	}
	
	/**
	 * Ao incluir um endereço, os dados devem ser incluídos no banco de dados.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 */
	@Test
	public void testInclusao() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
		dado.umUsuarioCadastrado();
		quando.incluiUmEndereco();
		entao.deveSerInseridoNoBancoDeDados();
	}
	
	/**
	 * Ao incluir o endereço para um usuário que não existe, uma exceção deve ser lançada.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 */
	@Test(expected=UsuarioNotFoundException.class)
	public void testInclusaoUsuarioInexistente() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
		dado.umEnderecoCadastrado();
		quando.incluiUmEndereco();
	}
	
	private class Dado{				
		public Dado umEnderecoCadastrado() {
			final EnderecoUsuario e = new EnderecoUsuario(ENDERECO_ID,usuario,CEP_VALIDO, "qualquer", "qualquer", "São Paulo", "SP");
			when(enderecoUsuarioRepository.findOne(eq(e.getId()))).thenReturn(e);
			return this;
		}
		
		public Dado umUsuarioCadastrado(){
			usuario = new Usuario(USUARIO_ID,"João da Silva");
			when(usuarioRepository.findOne(eq(usuario.getId()))).thenReturn(usuario);
			return this;
		}
	}
	
	private class Quando{
		public Quando consultaPeloId() {
			enderecoUsuario = enderecoUsuarioService.consulta(ENDERECO_ID);
			return this;
		}
		
		public Quando incluiUmEndereco() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
			final IncluiEnderecoUsuarioTO to = new IncluiEnderecoUsuarioTO();
			to.setUsuarioId(USUARIO_ID);
			to.setCidade("São Paulo");
			to.setBairro("qualquer");
			to.setRua("qualquer");
			to.setCep(CEP_VALIDO);
			
			enderecoUsuario = enderecoUsuarioService.inclui(to);
			return this;
		}
		
		public Quando alteraOEndereco() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
			final AlteraEnderecoUsuarioTO to = new AlteraEnderecoUsuarioTO();
			to.setUsuarioId(USUARIO_ID);
			to.setCidade("São Paulo");
			to.setBairro("qualquer");
			to.setRua("qualquer");
			to.setCep(CEP_VALIDO_2);
			
			enderecoUsuario = enderecoUsuarioService.altera(ENDERECO_ID, to);
			return this;
		}
		
		public Quando removeOEndereco() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
			enderecoUsuarioService.remove(ENDERECO_ID);
			return this;
		}
		
		public Quando alteraOEnderecoParaUmUsuarioInexistente() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
			final AlteraEnderecoUsuarioTO to = new AlteraEnderecoUsuarioTO();
			to.setUsuarioId(2);
			enderecoUsuario = enderecoUsuarioService.altera(ENDERECO_ID, to);
			return this;
		}
	}
	
	private class Entao {
		public Entao deveRetornarOEnderecoCorrespondente(){
			Assert.assertNotNull("Endereço não encontrado",enderecoUsuario);
			Assert.assertEquals("Endereço incorreto encontrado",ENDERECO_ID, enderecoUsuario.getId());
			return this;
		}
		
		public Entao deveSerAlteradoNoBancoDeDados(){			
			Assert.assertNotNull("Endereço não alterado",enderecoUsuario);
			Assert.assertEquals("Endereço incorreto alterado",ENDERECO_ID, enderecoUsuario.getId());
			Assert.assertEquals("Endereço incorreto alterado",CEP_VALIDO_2, enderecoUsuario.getCep());
			
			verify(enderecoUsuarioRepository).save(any(EnderecoUsuario.class));
			
			return this;
		}
		
		public Entao deveSerInseridoNoBancoDeDados(){			
			Assert.assertNotNull("Endereço não incluído",enderecoUsuario);
			Assert.assertEquals("Endereço incorreto alterado",NOVO_ENDERECO_ID, enderecoUsuario.getId());
			
			verify(enderecoUsuarioRepository).save(any(EnderecoUsuario.class));
			
			return this;
		}
		
		public Entao deveSerRemovidoDoBancoDeDados(){			
			verify(enderecoUsuarioRepository).delete(any(EnderecoUsuario.class));
			
			return this;
		}
	}
}
