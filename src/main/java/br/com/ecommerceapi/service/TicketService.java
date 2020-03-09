package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Ticket;

public interface TicketService {

    Ticket findByCode(String id);

    Ticket save(Ticket ticket);

    void deleteById(Long id);
}
