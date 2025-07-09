package bookingapitest;

import api.UpdateBookingApi;
import config.PropertyUtil;
import dataloader.BookingDataLoader;
import email.EmailSender;
import io.restassured.response.ValidatableResponse;
import objects.BookingRequest;
import objects.BookingResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ApiRequestHelper;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.Map;

public class UpdateBookingApiTest {

    private UpdateBookingApi updateBookingApi;

    @BeforeClass
    public void initializeObject() {
        updateBookingApi = new UpdateBookingApi();
    }

    @Test
    public void updateBookingApiAndValidateStatusCodeOnly() throws Exception{
        List<BookingRequest> bookingRequestData = BookingDataLoader.loadData(PropertyUtil.getConfig().bookingApiTestData());
        int i = 79;
        for (BookingRequest bookingRequest : bookingRequestData){
            //i += 10;
            ValidatableResponse response = updateBookingApi.updateBooking(bookingRequest, i)
                    .then().assertThat().statusCode(200);
            BookingResponse bookingResponse = response.extract().as(BookingResponse.class);

        }

        //EmailSender.sendEmailWithAttachments();
    }
}
