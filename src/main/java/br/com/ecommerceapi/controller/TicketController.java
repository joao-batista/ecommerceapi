package br.com.ecommerceapi.controller;

import br.com.ecommerceapi.dto.TicketDTO;
import br.com.ecommerceapi.entity.Ticket;
import br.com.ecommerceapi.entity.converter.Mapper;
import br.com.ecommerceapi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketService ticketService;


    @PostMapping
    public ResponseEntity<TicketDTO> create(@RequestBody @Valid TicketDTO ticketDTO) {
        Ticket ticket = ticketService.save(Mapper.map(ticketDTO, Ticket.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(ticket));
    }

    @GetMapping("/{code}")
    public ResponseEntity<TicketDTO> findByCode(@PathVariable String code) {
        Ticket ticket = ticketService.findByCode(code);
        return ResponseEntity.ok(convertToDTO(ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ticketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    private TicketDTO convertToDTO(Ticket ticket) {
        return Mapper.map(ticket, TicketDTO.class);
    }

}
