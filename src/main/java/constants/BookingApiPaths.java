package constants;
/* This enum's responsibility is to provide api paths for related requests
* Meaning if we have BookingsApis, ProductsApis,AuthenticationApis,
* All of them will have their different enum not this below to help when later one is trying to maintain.
* So this will only hold the booking related apis*/

import io.restassured.http.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookingApiPaths {
    GET_BOOKING("/booking/{bookingId}", Method.GET),
    GET_BOOKING_IDS("/booking", Method.GET),
    CREATE_BOOKING("/booking", Method.POST),
    DELETE_BOOKING("/booking/{bookingId}", Method.DELETE),
    UPDATE_BOOKING("/booking/{bookingId}",  Method.PUT);

    private final String apiPath;
    private final Method httpMethodType;
}
