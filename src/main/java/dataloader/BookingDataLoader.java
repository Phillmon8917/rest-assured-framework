package dataloader;

import objects.BookingDates;
import objects.BookingRequest;
import util.LoggingUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDataLoader {

    public static List<BookingRequest> loadData(String csvFilePath) {
        List<BookingRequest> bookings = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] values = line.split(";", -1); // Allow empty strings
                if (values.length < 8) continue;

                String executeFlag = values[7].trim();
                if ("Y".equalsIgnoreCase(executeFlag)) {
                    BookingRequest booking = new BookingRequest();
                    booking.setFirstname(values[0].trim());
                    booking.setLastname(values[1].trim());
                    booking.setTotalprice(Double.parseDouble(values[2].trim()));
                    booking.setDepositpaid(Boolean.parseBoolean(values[3].trim()));

                    BookingDates bookingDates = new BookingDates();
                    Date checkin = formatter.parse(values[4].trim());
                    Date checkout = formatter.parse(values[5].trim());
                    bookingDates.setCheckin(checkin);
                    bookingDates.setCheckout(checkout);
                    booking.setBookingdates(bookingDates);

                    booking.setAdditionalneeds(values[6].trim());

                    bookings.add(booking);
                }
            }
        } catch (Exception ex) {
            LoggingUtil.log("BookingDataLoader", "loadData", ex.toString());
        }

        return bookings;
    }
}
