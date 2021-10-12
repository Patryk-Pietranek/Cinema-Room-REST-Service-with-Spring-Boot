package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TheaterStatistics {

    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;
    private final String password = "super_secret";

    public TheaterStatistics(int current_income, int number_of_available_seats, int number_of_purchased_tickets) {
        this.current_income = current_income;
        this.number_of_available_seats = number_of_available_seats;
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }

    public void addToCurrent_income(int income) {
        this.current_income += income;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(int current_income) {
        this.current_income = current_income;
    }

    public void decrementNumber_of_available_seats() {
        this.number_of_available_seats--;
    }

    public void incrementNumber_of_available_seats() {
        this.number_of_available_seats++;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public void setNumber_of_available_seats(int number_of_available_seats) {
        this.number_of_available_seats = number_of_available_seats;
    }

    public void incrementNumber_of_purchased_tickets() {
        this.number_of_purchased_tickets++;
    }

    public void decrementNumber_of_purchased_tickets() {
        this.number_of_purchased_tickets--;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void setNumber_of_purchased_tickets(int number_of_purchased_tickets) {
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
