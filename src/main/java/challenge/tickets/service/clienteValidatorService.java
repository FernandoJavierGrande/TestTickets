package challenge.tickets.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import challenge.tickets.Entity.Cliente;

@Service
public class clienteValidatorService implements IClienteValidatorService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${validador.cliente.url}")
	String url;
	
	
	//realiza la validacion solicitada sobre la url proporcionada alojada en application.properties
	
	@Override
	public void validarCliente(Cliente cliente) {		
	
		Map<String, Object> requestBody = new HashMap<>();
		
		requestBody.put("nombre", cliente.getNombre());
		requestBody.put("apellido",cliente.getApellido());
		
		String value = String.format("%s:%s", cliente.getNombre(), cliente.getApellido());
		String token = Base64.getEncoder().encodeToString(value.getBytes()); 
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		if (response.getStatusCode().is4xxClientError()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Los datos no son validos");
		}
		
	}

}
