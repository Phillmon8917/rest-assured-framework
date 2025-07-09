package api;

import config.PropertyUtil;
import constants.BookingApiPaths;
import http.BaseApi;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import objects.BookingRequest;
import util.DecodeUtil;

import java.util.Map;

public class UpdateBookingApi extends BaseApi {

    public UpdateBookingApi(){
        super();
        super.logAllSpecifiedRequestDetails(LogDetail.BODY).logAllResponseData();
        super.setContentType(ContentType.JSON);
    }

    public Response updateBooking(Map<String, Object> createBookingPayload, int bookingId){
        return getUpdateBookingApiResponse(createBookingPayload, bookingId);
    }

    public Response updateBooking(BookingRequest bookingRequest, int bookingId){
        return getUpdateBookingApiResponse(bookingRequest, bookingId);
    }

    private Response getUpdateBookingApiResponse(Object createBookingPayload, int bookingId){
        super.setBasePath(BookingApiPaths.UPDATE_BOOKING.getApiPath());
        super.setRequestBody(createBookingPayload);
        super.setPathParam("bookingId", bookingId);
        super.setBasicAuth(PropertyUtil.getConfig().username(), DecodeUtil.decode64(PropertyUtil.getConfig().password()));
        return super.sendRequest(BookingApiPaths.UPDATE_BOOKING.getHttpMethodType());
    }
}
