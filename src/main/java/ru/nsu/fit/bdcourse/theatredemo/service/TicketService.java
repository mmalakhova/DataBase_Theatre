package ru.nsu.fit.bdcourse.theatredemo.service;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;
import ru.nsu.fit.bdcourse.theatredemo.model.Ticket;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.TicketDto;
import ru.nsu.fit.bdcourse.theatredemo.repository.TicketRepository;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Page<TicketDto> getAllTickets(Integer pageNo, Integer pageSize, String sortBy,
                                         String price, String row, String seat, String performanceName) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("price", contains().ignoreCase())
                .withMatcher("row", contains().ignoreCase())
                .withMatcher("seat", contains().ignoreCase())
                .withMatcher("performance", contains().ignoreCase());
        Performance performance;
        if (!Objects.equals(performanceName, "")) {
            performance = ticketRepository.findPerformanceByName(performanceName);
        } else {
            performance = null;
        }
        Ticket ticketToFind = Ticket
                .builder()
                .price(price.equals("") ? null : Integer.parseInt(price))
                .row(row.equals("") ? null : Integer.parseInt(row))
                .seat(seat.equals("") ? null : Integer.parseInt(seat))
                .performance(performance)
                .build();
        return ticketRepository.findAll(Example.of(ticketToFind, matcher), paging).map(TicketDto::new);
    }

    public Page<Ticket> getCheapTickets(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return ticketRepository.findCheapTickets(paging);
    }


    public ResponseEntity<Ticket> getTicketById(Long id) {
        Optional<Ticket> ticketData = ticketRepository.findById(id);
        return ticketData.map(t ->
                new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Ticket> createNewTicket(Ticket ticket) {
        return new ResponseEntity<>(ticketRepository.save(ticket), HttpStatus.CREATED);
    }

    public ResponseEntity<Ticket> updateTicket(Ticket ticket) {
        Optional<Ticket> ticketData = ticketRepository.findById(ticket.getId());
        if (ticketData.isPresent()) {
            Ticket updatedTicket = ticketData.get();
            if (ticket.getPrice() != null) {
                updatedTicket.setPrice(ticket.getPrice());
            }
            if (ticket.getRow() != null) {
                updatedTicket.setRow(ticket.getRow());
            }
            if (ticket.getSeat() != null) {
                updatedTicket.setSeat(ticket.getSeat());
            }
            if (ticket.getPerformance() != null) {
                updatedTicket.setPerformance(ticket.getPerformance());
            }
            return new ResponseEntity<>(ticketRepository.save(updatedTicket), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteTicketById(Long id) {
        Optional<Ticket> ticketToDelete = ticketRepository.findById(id);
        if (ticketToDelete.isPresent()) {
            ticketRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
