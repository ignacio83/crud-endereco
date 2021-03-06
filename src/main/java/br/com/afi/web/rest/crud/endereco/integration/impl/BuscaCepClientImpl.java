package br.com.afi.web.rest.crud.endereco.integration.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.afi.web.rest.crud.endereco.integration.BuscaCepClient;
import br.com.afi.web.rest.crud.endereco.integration.BuscaCepIntegrationException;
import br.com.afi.web.rest.crud.endereco.integration.EnderecoTO;
import br.com.afi.web.rest.crud.endereco.integration.HealthTO;
import br.com.afi.web.rest.crud.endereco.integration.InvalidCepException;

/**
 * Implementação do client para o serviço REST de consulta de CEP.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@Component
public class BuscaCepClientImpl implements BuscaCepClient{
	private static final String BUSCA_CEP_ENDPOINT = "/endereco/{cep}";
	private static final String HEALTH_ENDPOINT = "/health";
	private static final int STATUS_CODE_CEP_NOT_FOUND = 420;
	private final Logger logger = LoggerFactory.getLogger(BuscaCepClientImpl.class);
	
	@Value("${busca.cep.url:http://localhost:8081}")
	private String server;
	private RestTemplate rest;

	/**
	 * Construtor.
	 */
	public BuscaCepClientImpl() {
		rest = new RestTemplate();
	}
	
	@Override
	public HealthTO health(){
		final String url = server + HEALTH_ENDPOINT;
		return rest.getForObject(url, HealthTO.class);		
	}

	@Override
	public EnderecoTO consultaCep(String cep) throws BuscaCepIntegrationException, InvalidCepException {
		final String url = server + BUSCA_CEP_ENDPOINT;
		EnderecoTO enderecoTO = null;
		try{
			logger.debug("Consultando serviço {} com o CEP {}", url, cep);
			enderecoTO = rest.getForObject(url, EnderecoTO.class, cep);
		}
		catch(HttpClientErrorException e){
			if(e.getStatusCode().value()==STATUS_CODE_CEP_NOT_FOUND){
				throw new InvalidCepException(e.getResponseBodyAsString(), cep);
			}
			else{
				throw new BuscaCepIntegrationException(url, e);
			}
		}
		return enderecoTO;
	}
}
