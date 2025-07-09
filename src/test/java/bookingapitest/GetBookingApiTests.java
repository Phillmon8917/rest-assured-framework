package bookingapitest;

import api.GetBookingApi;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetBookingApiTests {

    private GetBookingApi getBookingApi;

    @BeforeClass
    public void initializeApi(){
        this.getBookingApi = new GetBookingApi();
    }

    @Test(priority = 1)
    public void validateStatusCodeForGetBookingIdApi(){
          this.getBookingApi.getAllBookingIds()
                .then().assertThat().statusCode(200);
    }

    @Test(description =  "Basic HTTP Status check for get booking by ID API", priority = 2)
    public void validateStatusCodeForGetBookingByIdAPI(){
        this.getBookingApi.getBookingById(20)
                .then().assertThat().statusCode(200);
    }
}
