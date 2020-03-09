package br.com.ecommerceapi.repository;

import br.com.ecommerceapi.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByCode(String code);
}
