package objects;

import lombok.Data;

@Data
public class BookingResponse {
    private String firstname;
    private String lastname;
    private double totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}
