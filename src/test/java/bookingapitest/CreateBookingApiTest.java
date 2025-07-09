package bookingapitest;

import api.CreateBookingApi;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ApiRequestHelper;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingApiTest {

    private CreateBookingApi createBookingApi;

    @BeforeClass
    public void initializeObject() {
        createBookingApi = new CreateBookingApi();
    }

    @Test
    public void createABookingAndValidateStatusCode() {
        Map<String, Object> createRequestPayload = ApiRequestHelper.getCreateBookingPayload("Phill",
                "Motsinoni", 255.65,true ,"2025-06-15", "2025-07-15");
        createBookingApi.createNewBooking(createRequestPayload)
                .then().assertThat().statusCode(200)
                .and().body("bookingid", is(not(equalTo(0))));
    }
}
