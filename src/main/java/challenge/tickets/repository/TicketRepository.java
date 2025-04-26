package challenge.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import challenge.tickets.Entity.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM tickets WHERE cliente_id = :id", nativeQuery = true)
	int ticketPrevio(@Param("id") Long id);
}
