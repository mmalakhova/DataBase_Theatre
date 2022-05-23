package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.bdcourse.theatredemo.model.Ticket;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.TicketDto;
import ru.nsu.fit.bdcourse.theatredemo.service.TicketService;

@RestController
@RequestMapping("/theatre")
@CrossOrigin("*")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    public Page<TicketDto> getAllTickets(Integer pageNo, Integer pageSize, String sortBy,
                                         @RequestParam(name = "price") String price,
                                         @RequestParam(name = "row") String row,
                                         @RequestParam(name = "seat") String seat,
                                         @RequestParam(name = "performance") String performanceName) {
        return ticketService.getAllTickets(pageNo, pageSize, sortBy, price, row, seat, performanceName);
    }


    @GetMapping("tickets/cheap")
    public Page<Ticket> getAllCheapTickets(Integer pageNo, Integer pageSize, String sortBy){
        return ticketService.getCheapTickets(pageNo, pageSize, sortBy);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ticketService.createNewTicket(ticket);
    }

    @RequestMapping(value = "/tickets", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }


    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<HttpStatus> deleteTicketById(@PathVariable("id") Long id) {
        return ticketService.deleteTicketById(id);
    }
}
