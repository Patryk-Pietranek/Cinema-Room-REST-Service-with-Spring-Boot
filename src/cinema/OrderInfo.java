package cinema;

import java.util.UUID;

public class OrderInfo {

    private UUID token;
    private TheaterSeat ticket;

    public OrderInfo() {
        this.token = UUID.randomUUID();
    }

    public OrderInfo(TheaterSeat ticket) {
        this();
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public TheaterSeat getTicket() {
        return ticket;
    }

    public void setTicket(TheaterSeat ticket) {
        this.ticket = ticket;
    }
}
