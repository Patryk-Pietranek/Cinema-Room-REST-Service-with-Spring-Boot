package cinema;

import java.util.ArrayList;
import java.util.List;

public class Theater {

    private int total_rows;
    private int total_columns;
    private List<TheaterSeat> available_seats;

    public Theater(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new ArrayList<>();
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                TheaterSeat temp = new TheaterSeat(i, j);
                available_seats.add(temp);
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<TheaterSeat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<TheaterSeat> available_seats) {
        this.available_seats = available_seats;
    }

}
