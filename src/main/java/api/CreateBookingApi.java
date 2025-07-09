package api;

import constants.BookingApiPaths;
import http.BaseApi;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import objects.BookingRequest;

import java.util.Map;

public class CreateBookingApi extends BaseApi {

    public CreateBookingApi(){
        super();
        super.logAllSpecifiedRequestDetails(LogDetail.BODY).logAllResponseData();
        super.setContentType(ContentType.JSON);
    }

    public Response createNewBooking(Map<String, Object> createBookingPayload){
        return getCreateBookingApiResponse(createBookingPayload);
    }

    public Response createNewBooking(BookingRequest bookingRequest){
        return getCreateBookingApiResponse(bookingRequest);
    }

    private Response getCreateBookingApiResponse(Object createBookingPayload){
        super.setBasePath(BookingApiPaths.CREATE_BOOKING.getApiPath());
        super.setRequestBody(createBookingPayload);
        return super.sendRequest(BookingApiPaths.CREATE_BOOKING.getHttpMethodType());
    }
}
