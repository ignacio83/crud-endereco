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
import br.com.afi.web.rest.crud.endereco.integration.BuscaCepClient;
import br.com.afi.web.rest.crud.endereco.integration.BuscaCepIntegrationException;
import br.com.afi.web.rest.crud.endereco.integration.EnderecoTO;
import br.com.afi.web.rest.crud.endereco.integration.InvalidCepException;
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
	private static final String CEP_1 = "01001123";
	private static final String CEP_2 = "01001124";
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
	
	@Mock
	private BuscaCepClient buscaCepClient;
	
	@InjectMocks
	private EnderecoUsuarioService enderecoUsuarioService = new EnderecoUsuarioServiceImpl();
	
	@Before
	public void clear(){
		enderecoUsuario = null;
		usuario = null;
		Mockito.reset(enderecoUsuarioRepository);
		Mockito.reset(usuarioRepository);
		Mockito.reset(buscaCepClient);
		
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
		dado.umEnderecoCadastrado(ENDERECO_ID,CEP_1);
		quando.consultaPeloId(ENDERECO_ID);
		entao.deveRetornarOEnderecoCorrespondente(ENDERECO_ID);
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
		dado.umEnderecoCadastrado(ENDERECO_ID,CEP_1);
		quando.removeOEndereco(ENDERECO_ID);
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
		quando.removeOEndereco(ENDERECO_ID);
	}
	
	/**
	 * Ao alterar o endereço, os dados devem ser alterado no banco de dados.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 * @throws InvalidCepException 
	 * @throws BuscaCepIntegrationException 
	 */
	@Test
	public void testAlteracao() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, CepNotFoundException, BuscaCepIntegrationException, InvalidCepException{
		dado.umUsuarioCadastrado();
		dado.umEnderecoCadastrado(ENDERECO_ID,CEP_1);
		dado.cepExistente(CEP_2);
		quando.alteraOEndereco(ENDERECO_ID,CEP_2);
		entao.deveSerAlteradoNoBancoDeDados(ENDERECO_ID);
	}
	
	/**
	 * Ao alterar o endereço para um CEP que não existe, uma exceção deve ser lançada.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 * @throws CepNotFoundException 
	 * @throws BuscaCepIntegrationException 
	 * @throws InvalidCepException 
	 */
	@Test(expected=CepNotFoundException.class)
	public void testAlteracaoCepInexistente() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, CepNotFoundException, BuscaCepIntegrationException, InvalidCepException{
		dado.umUsuarioCadastrado();
		dado.umEnderecoCadastrado(ENDERECO_ID,CEP_1);
		quando.alteraOEndereco(ENDERECO_ID,CEP_2);
	}
	
	/**
	 * Ao alterar o endereço para um usuário que não existe, uma exceção deve ser lançada.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 * @throws InvalidCepException 
	 * @throws BuscaCepIntegrationException 
	 */
	@Test(expected=UsuarioNotFoundException.class)
	public void testAlteracaoUsuarioInexistente() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, CepNotFoundException, BuscaCepIntegrationException, InvalidCepException{
		dado.cepExistente(CEP_2);
		dado.umEnderecoCadastrado(ENDERECO_ID,CEP_1);
		quando.alteraOEnderecoParaUmUsuarioInexistente(ENDERECO_ID,CEP_2);
	}
	
	/**
	 * Ao alterar o endereço que não existe, uma exceção deve ser lançada.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 * @throws InvalidCepException 
	 * @throws BuscaCepIntegrationException 
	 */
	@Test(expected=EnderecoUsuarioNotFoundException.class)
	public void testAlteracaoEnderecoInexistente() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, CepNotFoundException, BuscaCepIntegrationException, InvalidCepException{
		dado.cepExistente(CEP_2);
		quando.alteraOEndereco(ENDERECO_ID,CEP_2);
	}
	
	/**
	 * Ao incluir um endereço, os dados devem ser incluídos no banco de dados.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 * @throws InvalidCepException 
	 * @throws BuscaCepIntegrationException 
	 */
	@Test
	public void testInclusao() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, CepNotFoundException, BuscaCepIntegrationException, InvalidCepException{
		dado.umUsuarioCadastrado();
		dado.cepExistente(CEP_1);
		quando.incluiUmEndereco(CEP_1);
		entao.deveSerInseridoNoBancoDeDados();
	}
	
	/**
	 * Ao incluir o endereço para um usuário que não existe, uma exceção deve ser lançada.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 * @throws InvalidCepException 
	 * @throws BuscaCepIntegrationException 
	 * @throws CepNotFoundException 
	 */
	@Test(expected=UsuarioNotFoundException.class)
	public void testInclusaoUsuarioInexistente() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, InvalidCepException, BuscaCepIntegrationException, CepNotFoundException{
		dado.umEnderecoCadastrado(ENDERECO_ID,CEP_1);
		dado.cepExistente(CEP_1);
		quando.incluiUmEndereco(CEP_1);
	}
	
	/**
	 * Ao incluir o endereço para um CEP que não existe, uma exceção deve ser lançada.
	 * 
	 * @throws UsuarioNotFoundException 
	 * @throws EnderecoUsuarioNotFoundException 
	 * @throws InvalidCepException 
	 * @throws BuscaCepIntegrationException 
	 * @throws CepNotFoundException 
	 */
	@Test(expected=CepNotFoundException.class)
	public void testInclusaoCepInexistente() throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, InvalidCepException, BuscaCepIntegrationException, CepNotFoundException{
		dado.umUsuarioCadastrado();
		quando.incluiUmEndereco(CEP_1);
	}
	
	private class Dado{				
		public Dado umEnderecoCadastrado(Integer id, String cep) {
			final EnderecoUsuario e = new EnderecoUsuario(id,usuario,cep, "qualquer", "qualquer", "São Paulo", "SP");
			when(enderecoUsuarioRepository.findOne(eq(e.getId()))).thenReturn(e);
			return this;
		}
		
		public Dado umUsuarioCadastrado(){
			usuario = new Usuario(USUARIO_ID,"João da Silva");
			when(usuarioRepository.findOne(eq(usuario.getId()))).thenReturn(usuario);
			return this;
		}
		
		public Dado cepExistente(String cep) throws BuscaCepIntegrationException, InvalidCepException{
			final EnderecoTO to = new EnderecoTO();
			to.setCep(cep);
			when(buscaCepClient.consultaCep(cep)).thenReturn(to);
			return this;
		}
	}
	
	private class Quando{
		public Quando consultaPeloId(Integer id) {
			enderecoUsuario = enderecoUsuarioService.consulta(id);
			return this;
		}
		
		public Quando incluiUmEndereco(String cep) throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, CepNotFoundException, BuscaCepIntegrationException, InvalidCepException{
			final IncluiEnderecoUsuarioTO to = new IncluiEnderecoUsuarioTO();
			to.setUsuarioId(USUARIO_ID);
			to.setCidade("São Paulo");
			to.setBairro("qualquer");
			to.setRua("qualquer");
			to.setCep(cep);
			
			enderecoUsuario = enderecoUsuarioService.inclui(to);
			return this;
		}
		
		public Quando alteraOEndereco(Integer id, String novoCep) throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, CepNotFoundException, BuscaCepIntegrationException, InvalidCepException{
			final AlteraEnderecoUsuarioTO to = new AlteraEnderecoUsuarioTO();
			to.setUsuarioId(USUARIO_ID);
			to.setCidade("São Paulo");
			to.setBairro("qualquer");
			to.setRua("qualquer");
			to.setCep(novoCep);
			
			enderecoUsuario = enderecoUsuarioService.altera(id, to);
			return this;
		}
		
		public Quando removeOEndereco(Integer id) throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException{
			enderecoUsuarioService.remove(id);
			return this;
		}
		
		public Quando alteraOEnderecoParaUmUsuarioInexistente(Integer id, String cep) throws EnderecoUsuarioNotFoundException, UsuarioNotFoundException, CepNotFoundException, BuscaCepIntegrationException, InvalidCepException{
			final AlteraEnderecoUsuarioTO to = new AlteraEnderecoUsuarioTO();
			to.setUsuarioId(2);
			to.setCep(cep);
			enderecoUsuario = enderecoUsuarioService.altera(id, to);
			return this;
		}
	}
	
	private class Entao {
		public Entao deveRetornarOEnderecoCorrespondente(Integer id){
			Assert.assertNotNull("Endereço não encontrado",enderecoUsuario);
			Assert.assertEquals("Endereço incorreto encontrado",id, enderecoUsuario.getId());
			return this;
		}
		
		public Entao deveSerAlteradoNoBancoDeDados(Integer id){			
			Assert.assertNotNull("Endereço não alterado",enderecoUsuario);
			Assert.assertEquals("Endereço incorreto alterado",id, enderecoUsuario.getId());
			Assert.assertEquals("Endereço incorreto alterado",CEP_2, enderecoUsuario.getCep());
			
			verify(enderecoUsuarioRepository).save(any(EnderecoUsuario.class));
			
			return this;
		}
		
		public Entao deveSerInseridoNoBancoDeDados(){			
			Assert.assertNotNull("Endereço não incluído",enderecoUsuario);
			Assert.assertEquals("Endereço inserido com id incorreto",NOVO_ENDERECO_ID, enderecoUsuario.getId());
			
			verify(enderecoUsuarioRepository).save(any(EnderecoUsuario.class));
			
			return this;
		}
		
		public Entao deveSerRemovidoDoBancoDeDados(){			
			verify(enderecoUsuarioRepository).delete(any(EnderecoUsuario.class));
			
			return this;
		}
	}
}
