package ru.nsu.fit.bdcourse.theatredemo.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.nsu.fit.bdcourse.theatredemo.model.Ticket;

@Setter
@Getter
@ToString
public class TicketDto {

    private Long id;

    private String performanceName;

    private Integer price;

    private Integer row;

    private Integer seat;

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.performanceName = ticket.getPerformance().getName();
        this.price = ticket.getPrice();
        this.row = ticket.getRow();
        this.seat = ticket.getSeat();
    }
}
