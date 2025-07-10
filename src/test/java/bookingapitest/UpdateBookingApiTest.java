package bookingapitest;

import api.UpdateBookingApi;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import config.PropertyUtil;
import dataloader.BookingDataLoader;
import io.restassured.response.ValidatableResponse;
import objects.BookingRequest;
import objects.BookingResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ExtentReportManager;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

public class UpdateBookingApiTest {

    private UpdateBookingApi updateBookingApi;
    private ExtentReports extentReports;

    @BeforeClass
    public void initializeObject() {
        updateBookingApi = new UpdateBookingApi();
        extentReports = ExtentReportManager.getExtentReporter("BookingApi");
    }

    @Test
    public void updateBookingApiAndValidateStatusCodeOnly() throws Exception{
        List<BookingRequest> bookingRequestData = BookingDataLoader.loadData(PropertyUtil.getConfig().bookingApiTestData());

        int i = 79;
        for (BookingRequest bookingRequest : bookingRequestData){
            ExtentTest extentTest = extentReports.createTest("Update Booking APi");
            extentTest.info("firstname : " + bookingRequest.getFirstname());
            extentTest.info("lastname : " + bookingRequest.getLastname());
            extentTest.info("totalprice : " + bookingRequest.getTotalprice());
            extentTest.info("depositpaid : " + bookingRequest.isDepositpaid());
            extentTest.info("checkindate : " + bookingRequest.getBookingdates().getCheckin());
            extentTest.info("checkoutdate : " + bookingRequest.getBookingdates().getCheckout());
            extentTest.info("AdditionalNeeds : " + bookingRequest.getAdditionalneeds());

            ValidatableResponse response = updateBookingApi.updateBooking(bookingRequest, i)
                    .then().assertThat().statusCode(200);
            BookingResponse bookingResponse = response.extract().as(BookingResponse.class);

            extentReports.flush();
            return;

        }

        //EmailSender.sendEmailWithAttachments();
    }
}
