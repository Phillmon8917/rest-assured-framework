package api;

import constants.BookingApiPaths;
import http.BaseApi;
import io.restassured.response.Response;

public class GetBookingApi extends BaseApi {

    public GetBookingApi(){
        super();
        super.loggAllRequestData().logAllResponseData();
    }

    public Response getAllBookingIds(){
        super.setBasePath(BookingApiPaths.GET_BOOKING_IDS.getApiPath());
        return super.sendRequest(BookingApiPaths.GET_BOOKING_IDS.getHttpMethodType());
    }

    public Response getBookingById(int bookingId){
        super.setBasePath(BookingApiPaths.GET_BOOKING.getApiPath());
        super.setPathParam("bookingId", bookingId);
        return super.sendRequest(BookingApiPaths.GET_BOOKING.getHttpMethodType());
    }
}
