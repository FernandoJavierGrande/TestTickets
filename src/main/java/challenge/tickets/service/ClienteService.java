package challenge.tickets.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import challenge.tickets.Entity.Cliente;
import challenge.tickets.dto.ClienteDto;
import challenge.tickets.repository.ClienteRepository;


@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private clienteValidatorService clienteValidator;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public ClienteDto createCliente(Cliente cliente) {
		//Validacion contra el endpoint
		clienteValidator.validarCliente(cliente);
		
		return modelMapper.map(clienteRepo.save(cliente), ClienteDto.class);
	}

	@Override
	public ClienteDto getCliente(Long id) {
		Cliente cliente = clienteRepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no existe"));
		return modelMapper.map(cliente, ClienteDto.class);
	}
	
	
	@Override
	public Long updateCliente(Long id, ClienteDto clienteDto) {
		//Busco el id, si no existe lanza la excepcion
		clienteRepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no existe"));
		
		Cliente cliente =  modelMapper.map(clienteDto, Cliente.class);
		cliente.setId(id);
		clienteRepo.save(cliente);
		return id;		
	}

	@Override
	public Long deleteCliente(Long id) {
		clienteRepo.deleteById(id);
		return id;
	}

}