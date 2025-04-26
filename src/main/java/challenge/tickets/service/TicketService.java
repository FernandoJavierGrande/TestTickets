package challenge.tickets.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import challenge.tickets.Entity.Cliente;
import challenge.tickets.Entity.Ticket;
import challenge.tickets.dto.TicketDto;
import challenge.tickets.repository.TicketRepository;

@Service
public class TicketService implements ITicketService {

	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Value("${validador.cliente.ticket}")
	boolean validaTicket;
	
	@Override
	public TicketDto createTicket(TicketDto ticketDto) {
		// si no encuentra el cliente devuelve una ex con un response notFound capturado en el controller
		Cliente cliente = modelMapper.map(clienteService.getCliente(ticketDto.getCliente_id()), Cliente.class);
		
		// la variable validaTiket permite 'desactivar' la validacion de que para asignar un ticket a un cliente debe tener
		// uno previamente asignado, esto deja que se cargue al menos un ticket por cliente nuevo que de otra forma no lo permitiría
		// la variale se encuentra en application.properties como validador.cliente.ticket
		if(validaTicket && ticketRepo.ticketPrevio(ticketDto.getCliente_id()) == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Cliente debe tener un ticket existente asignado");
		}
		
		Ticket ticket = new Ticket();
		ticket.setCliente(cliente);
		ticket.setCosto(ticketDto.getCosto());
		ticket.setEvento(ticketDto.getEvento());
		ticket.setFechaVigencia(ticketDto.getFechaVigencia());
		
		ticketDto = modelMapper.map(ticketRepo.save(ticket), TicketDto.class);
		ticketDto.setCliente_id(ticket.getCliente().getId());
		return ticketDto;
	}

}
