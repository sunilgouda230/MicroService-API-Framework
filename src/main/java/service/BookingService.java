package service;

import config.ConfigManager;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import models.common.ErrorResponse;
import models.request.CreateBookingRequest;
import models.response.Booking.BookingDetailsResponse;
import models.response.Booking.BookingResponse;

import java.util.List;

public class BookingService extends AuthService{

    private static final String BASE_URL = ConfigManager.get("base.url");

    public List<BookingResponse> getBookings() {

        Response response = get(BASE_URL + "/booking");

        if (response.getStatusCode() != 200) {
            handleResponse(response, ErrorResponse.class, ErrorResponse.class);
        }

        return response.as(new TypeRef<List<BookingResponse>>() {});
    }

    public BookingDetailsResponse getBookingById(int id){
        Response response = get(BASE_URL + "/booking/" + id);

        return handleResponse(
                response, BookingDetailsResponse.class, ErrorResponse.class);
    }

    public BookingResponse createBooking(CreateBookingRequest request) {

        Response response = post(
                BASE_URL + "/booking",
                request
        );

        return handleResponse(
                response,
                BookingResponse.class,
                ErrorResponse.class
        );
    }

    public BookingResponse updateBooking(int bookingId,
                                                CreateBookingRequest request) {

        Response response = put(request,BASE_URL + "/booking/" + bookingId);

        return handleResponse(
                response,
                BookingResponse.class,
                ErrorResponse.class
        );
    }

}
