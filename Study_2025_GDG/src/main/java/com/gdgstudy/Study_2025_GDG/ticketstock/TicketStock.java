package com.gdgstudy.Study_2025_GDG.ticketstock;

import com.gdgstudy.Study_2025_GDG.exception.OutOfStockException;
import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_stock_id;

    private Integer quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Builder
    public TicketStock(Ticket ticket){
        this.quantity=10;
        this.ticket=ticket;
    }

    public Integer addTicketStock(){
        this.quantity+=1;
        return this.quantity;
    }

    public Integer subTicketStock(){
        if (this.quantity==0){
            throw new OutOfStockException("티켓의 현재 재고가 0개입니다.");
        }
        this.quantity-=1;
        return this.quantity;
    }
}
