package com.sun.base.thread.A_j;

/**
 * Created by louis on 2014/11/3.
 */
public class TicketOffice2 implements Runnable {
    private Cinema cinema;
    public TicketOffice2(Cinema cinema) {
        this.cinema=cinema;
    }
    @Override
    public void run() {
        cinema.sellTickets2(2);
        cinema.sellTickets2(4);
        cinema.sellTickets1(2);
        cinema.sellTickets1(1);
        cinema.returnTickets2(2);
        cinema.sellTickets1(3);
        cinema.sellTickets2(2);
        cinema.sellTickets1(2);
    }
}
