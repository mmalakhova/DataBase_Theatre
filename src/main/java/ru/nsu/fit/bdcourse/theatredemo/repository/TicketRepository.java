package ru.nsu.fit.bdcourse.theatredemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.bdcourse.theatredemo.model.Performance;
import ru.nsu.fit.bdcourse.theatredemo.model.Ticket;
import ru.nsu.fit.bdcourse.theatredemo.model.dto.PerformanceSalesDto;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Procedure(value = "find_cheap_tickets")
    Page<Ticket> findCheapTickets(Pageable pageable);

    @Query(nativeQuery = true,
            value = "select p.* from performances p inner join tickets t on p.id = t.performance_id " +
                    "where p.name = ?1")
    Performance findPerformanceByName(String performanceName);

    @Query(nativeQuery = true, value = "select p.name as name, sum(tickets.price) as total from tickets " +
            "inner join performances p on tickets.performance_id = p.id " +
            "group by p.name having sum(tickets.price) > 500")
    Page<PerformanceSalesDto> countSalesPerPerformance(Pageable pageable);
}
