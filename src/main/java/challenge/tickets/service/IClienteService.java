package challenge.tickets.service;

import challenge.tickets.Entity.Cliente;
import challenge.tickets.dto.ClienteDto;



public interface IClienteService {
	ClienteDto createCliente(Cliente cliente);
	
	ClienteDto getCliente(Long id);
	
	Long updateCliente(Long id, ClienteDto clienteDto);
	
	Long deleteCliente(Long id);
}
