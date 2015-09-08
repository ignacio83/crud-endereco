package br.com.afi.web.rest.crud.endereco.integration;



/**
 * Client para o serviço REST de consulta de CEP.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
public interface BuscaCepClient {

	/**
	 * Consulta o endereço do CEP.
	 * 
	 * @param cep CEP
	 * @return Endereço
	 * @throws BuscaCepIntegrationException Caso não seja possível se comunicar com o serviço
	 * @throws InvalidCepException Caso o CEP não seja válido
	 */
	EnderecoTO consultaCep(String cep) throws BuscaCepIntegrationException, InvalidCepException;

	/**
	 * Verifica se o serviço está no ar.
	 * 
	 * @return Saúde do serviço
	 */
	HealthTO health();
}
