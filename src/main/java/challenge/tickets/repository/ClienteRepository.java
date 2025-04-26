package challenge.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import challenge.tickets.Entity.Cliente;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
