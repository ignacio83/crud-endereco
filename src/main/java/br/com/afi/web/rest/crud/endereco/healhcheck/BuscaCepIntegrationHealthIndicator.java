package br.com.afi.web.rest.crud.endereco.healhcheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import br.com.afi.web.rest.crud.endereco.integration.BuscaCepClient;
import br.com.afi.web.rest.crud.endereco.integration.HealthTO;

/**
 * Health check que verifica se a comunicação com o serviço de busca de CEP é possível.
 * 
 * @author André de Fontana Ignacio
 * @version 1.0
 */
@Component
public class BuscaCepIntegrationHealthIndicator extends AbstractHealthIndicator {

	@Autowired
	private BuscaCepClient buscaCepClient;
	
	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		HealthTO health = null; 
		try{
			health = buscaCepClient.health();
			if(health == null || !Status.UP.getCode().equals(health.getStatus())){
				builder.down().withDetail("busca.cep", "Serviço de busca de CEP não está disponível.");
			}
			else{
				builder.up();
			}
		}
		catch(Exception e){
			builder.down().withDetail("busca.cep", "Serviço de busca de CEP não está disponível.");
		}
	}
}

