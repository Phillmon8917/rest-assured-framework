package bookingapitest;

import api.UpdateBookingApi;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import config.PropertyUtil;
import dataloader.BookingDataLoader;
import email.EmailSender;
import io.restassured.response.ValidatableResponse;
import objects.BookingRequest;
import objects.BookingResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ApiRequestHelper;
import util.ExtentReportManager;

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
        ExtentReports reports = ExtentReportManager.getExtentReporter("BookingApi");

        for (BookingRequest request : bookingRequestData){

        }

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
