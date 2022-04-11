package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.bdcourse.theatredemo.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
