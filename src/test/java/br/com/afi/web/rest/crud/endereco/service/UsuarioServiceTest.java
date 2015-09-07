package br.com.afi.web.rest.crud.endereco.service;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.afi.web.rest.crud.endereco.domain.EnderecoUsuario;
import br.com.afi.web.rest.crud.endereco.domain.InvalidCepException;
import br.com.afi.web.rest.crud.endereco.repository.EnderecoUsuarioRepository;
import br.com.afi.web.rest.crud.endereco.service.EnderecoUsuarioService;
import br.com.afi.web.rest.crud.endereco.service.impl.EnderecoUsuarioServiceImpl;

/**
 * Testes unitários para a consulta de CEP.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {
	private final static String CEP_INVALIDO = "01001-123";
	private final static String CEP_VALIDO = "01001123";
	private final static String CEP_VALIDO_ZEROS = "01001000";
	private final Dado dado = new Dado();
	private final Quando quando = new Quando();
	private final Entao entao = new Entao();
	private String cep;
	private EnderecoUsuario endereco;
	
	@Mock
	private EnderecoUsuarioRepository enderecoRepository;
	
	@InjectMocks
	private EnderecoUsuarioService cepService = new EnderecoUsuarioServiceImpl();
	
	@Before
	public void clear(){
		cep = null;
		endereco = null;
		Mockito.reset(enderecoRepository);
	}
	
	/**
	 * Dado um CEP inválido deve retornar a mensagem CEP inválido.
	 * 
	 * @throws InvalidCepException
	 */
	@Test(expected=InvalidCepException.class)
	public void testCepInvalido() throws InvalidCepException{
		dado.umCepInvalido();
		quando.buscaPeloCep();
	}
	
	/**
	 * Dado um CEP válido deve retornar o endereço correspondente.
	 * 
	 * @throws InvalidCepException 
	 */
	@Test
	public void testCepValido() throws InvalidCepException{
		dado.umCepValido().cadastrado();
		quando.buscaPeloCep();
		entao.deveRetornarOEnderecoCorrespondente();
	}
	
	/**
	 * Dado um CEP válido, que não exista um endereço, deve substituir um digito da direita para a esquerda até que o endereço seja localizado.
	 * Exemplo: Dado 22333999 tentar com 22333990, 2233900 ...
	 * 
	 * @throws InvalidCepException
	 */
	@Test
	public void testCepValidoNaoExistente() throws InvalidCepException{
		dado.umCepValido().cadastradoComZerosADireita();
		quando.buscaPeloCep();
		entao.deveRetornarOEnderecoSimilarCorrespondente();
	}
	
	private class Dado{
		public Dado umCepValido(){
			cep = CEP_VALIDO;
			return this;
		}
		
		public Dado umCepInvalido(){
			cep = CEP_INVALIDO;
			return this;
		}
		
		public Dado cadastrado() throws InvalidCepException{
			final EnderecoUsuario e = new EnderecoUsuario(cep, "qualquer", "qualquer","São Paulo", "SP");
			when(enderecoRepository.findOne(eq(cep))).thenReturn(e);
			return this;
		}
		
		public Dado cadastradoComZerosADireita() throws InvalidCepException{
			final EnderecoUsuario e = new EnderecoUsuario(CEP_VALIDO_ZEROS, "qualquer", "qualquer","São Paulo", "SP");
			when(enderecoRepository.findOne(eq(CEP_VALIDO_ZEROS))).thenReturn(e);
			return this;
		}
	}
	
	private class Quando{
		public Quando buscaPeloCep() throws InvalidCepException{
			endereco = cepService.findByCep(cep);
			return this;
		}
	}
	
	private class Entao {
		public Entao deveRetornarOEnderecoCorrespondente(){
			Assert.assertNotNull("CEP não encontrado",endereco);
			Assert.assertEquals("CEP incorreto encontrado",cep, endereco.getCep());
			return this;
		}
		
		public Entao deveRetornarOEnderecoSimilarCorrespondente(){
			Assert.assertNotNull("CEP não encontrado",endereco);
			Assert.assertEquals("CEP incorreto encontrado",CEP_VALIDO_ZEROS, endereco.getCep());
			return this;
		}
	}
}
