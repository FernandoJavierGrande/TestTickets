package challenge.tickets.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import challenge.tickets.Entity.Cliente;
import challenge.tickets.dto.ClienteDto;
import challenge.tickets.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	//inyeccion de dependencias
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<ClienteDto> createCliente(@Valid @RequestBody ClienteDto clienteDto){
		
		Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
				
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(cliente));
		}
		catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "DNI duplicado");
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> getCliente(@PathVariable Long id){
		
		return ResponseEntity.ok(clienteService.getCliente(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Long> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto){
		return ResponseEntity.ok(clienteService.updateCliente(id, clienteDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteCliente(@PathVariable Long id){
		try {
			return ResponseEntity.ok(clienteService.deleteCliente(id));
			
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
				
		}
		
	}
}
