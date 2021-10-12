package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TheaterController {

    private Theater theater = new Theater(9, 9);
    private List<OrderInfo> orderInfoList = new ArrayList<>();
    private TheaterStatistics theaterStatistics = new TheaterStatistics(0, theater.getTotal_rows() * theater.getTotal_columns(), 0);
    private List<TheaterSeat> list = this.theater.getAvailable_seats();

    @GetMapping("/seats")
    public Theater getSeats() {
        return this.theater;
    }

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.OK)
    public OrderInfo postPurchase(@RequestBody TheaterSeat theaterSeat) {
        int index = countIndex(theaterSeat);
        if (theater.getTotal_rows() < theaterSeat.getRow() || theater.getTotal_columns() < theaterSeat.getColumn()
                || theaterSeat.getColumn() < 0 || theaterSeat.getRow() < 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "The number of a row or a column is out of bounds!");
        } else {
            if (list.get(index).getPurchased()) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!");
            }
            list.get(index).setPurchased(true);
            theaterStatistics.addToCurrent_income(list.get(index).getPrice());
            theaterStatistics.decrementNumber_of_available_seats();
            theaterStatistics.incrementNumber_of_purchased_tickets();
        }
        OrderInfo orderInfo = new OrderInfo(list.get(index));
        orderInfoList.add(orderInfo);
        return orderInfo;
    }

    @PostMapping("/return")
    public Map<String, TheaterSeat> postReturn(@RequestBody OrderInfo orderInfo) {
        OrderInfo responseOrder = new OrderInfo();
        boolean found = false;
        for (OrderInfo order : orderInfoList) {
            if (order.getToken().equals(orderInfo.getToken())) {
                responseOrder.setToken(order.getToken());
                responseOrder.setTicket(order.getTicket());
                int index = countIndex(responseOrder.getTicket());
                found = true;
                list.get(index).setPurchased(false);
                orderInfoList.remove(orderInfo);
                theaterStatistics.incrementNumber_of_available_seats();
                theaterStatistics.decrementNumber_of_purchased_tickets();
                theaterStatistics.addToCurrent_income(-order.getTicket().getPrice());
            }
        }
        if (!found) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Wrong token!");
        }
        return Map.of("returned_ticket", responseOrder.getTicket());
    }

    @PostMapping("/stats")
    public TheaterStatistics postStatistics(@RequestParam(value = "password", required = false) String password) {
        if (!theaterStatistics.getPassword().equals(password)) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "The password is wrong!");
        }
        return theaterStatistics;
    }

    public int countIndex(TheaterSeat theaterSeat) {
        int index = 0;
        if (theaterSeat.getRow() > 1) {
            index = (theaterSeat.getRow() - 1) * theater.getTotal_columns() + theaterSeat.getColumn() - 1;
        } else {
            index = theaterSeat.getRow() * theaterSeat.getColumn() - 1;
        }
        return index;
    }

}
