package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Ticket;
import br.com.ecommerceapi.exception.ResourceNotFoundException;
import br.com.ecommerceapi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket findByCode(String code) {
        return ticketRepository.findByCode(code).orElseThrow(()
                -> new ResourceNotFoundException("Ticket '" + code + "' does no exist"));
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
