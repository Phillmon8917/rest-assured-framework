package api;
import config.PropertyUtil;
import constants.BookingApiPaths;
import http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.DecodeUtil;

public class DeleteBookingApi extends BaseApi {

    public DeleteBookingApi(){
        super();
        super.setContentType(ContentType.JSON);
        super.loggAllRequestData().logAllResponseData();
    }

    public Response deleteBookingById(int bookingId){
        super.setBasePath(BookingApiPaths.DELETE_BOOKING.getApiPath());
        super.setPathParam("bookingId", bookingId);
        super.setBasicAuth(PropertyUtil.getConfig().username(), DecodeUtil.decode64(PropertyUtil.getConfig().password()));
        return super.sendRequest(BookingApiPaths.DELETE_BOOKING.getHttpMethodType());
    }
}
