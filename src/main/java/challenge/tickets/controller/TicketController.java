package challenge.tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import challenge.tickets.dto.TicketDto;
import challenge.tickets.service.TicketService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@PostMapping
	public ResponseEntity<TicketDto> Crear(@Valid @RequestBody TicketDto ticketDto){
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(ticketDto));
			
		} catch (ResponseStatusException  e) {
			
			throw new ResponseStatusException(e.getStatusCode(), e.getReason());
		}	
		
	}
}
