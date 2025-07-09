package util;

import org.joda.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class ApiRequestHelper {
    public static Map<String, Object> getCreateBookingPayload(String firstname, String lastname,
                                                              double totalPrice,boolean depositPaid, String checkingDate, String checkoutDate){
        ApiRequestHelper.validateDateInputs(checkingDate, checkoutDate);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("totalprice", totalPrice);
        requestBody.put("depositpaid", depositPaid);

        Map<String, Object> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", checkingDate);
        bookingDatesMap.put("checkout", checkoutDate);
        requestBody.put("bookingdates", bookingDatesMap);

        return requestBody;
    }

    private static void validateDateInputs(String checkinDate, String checkoutDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate checkin = LocalDate.parse(checkinDate);
            LocalDate checkout = LocalDate.parse(checkoutDate);

            if (checkin.isAfter(checkout)){
                throw new IllegalArgumentException("Check-in date cannot be after checkout date.");
            }
        }catch (DateTimeParseException ex){
            throw new IllegalArgumentException("Invalid date format exception, expected yyyy-MM-dd", ex);
        }
    }
}

