package bookingapitest;

import api.DeleteBookingApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteBookingApiTest {

    private DeleteBookingApi deleteBookingApi;

    @BeforeClass
    public void initializeObject(){
        deleteBookingApi = new DeleteBookingApi();
    }

    @Test
    public void deleteApiAndVerifyStatusCode(){
        deleteBookingApi.deleteBookingById(13)
                .then().assertThat().statusCode(201);
    }
}
