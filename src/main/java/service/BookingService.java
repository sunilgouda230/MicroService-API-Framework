package service;

import config.ConfigManager;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import models.common.ErrorResponse;
import models.request.CreateBookingRequest;
import models.response.Booking.BookingDetailsResponse;
import models.response.Booking.BookingResponse;

import java.util.List;

public class BookingService extends AuthService {

    private String getBaseUrl() {
        String baseUrl = ConfigManager.get("base.url");
        System.out.println("the base url "+ baseUrl);
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new RuntimeException("BASE_URL is not configured properly.");
        }
        return baseUrl;
    }

    public List<BookingResponse> getBookings() {

        Response response = get(getBaseUrl() + "/booking");

        return response.as(new TypeRef<List<BookingResponse>>() {});
    }

    public BookingDetailsResponse getBookingById(int id) {

        Response response = get(getBaseUrl() + "/booking/" + id);

        return handleResponse(
                response,
                BookingDetailsResponse.class,
                ErrorResponse.class
        );
    }

    public BookingResponse createBooking(CreateBookingRequest request) {

        Response response = post(
                getBaseUrl() + "/booking",
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

        Response response = put(
                request,
                getBaseUrl() + "/booking/" + bookingId
        );

        return handleResponse(
                response,
                BookingResponse.class,
                ErrorResponse.class
        );
    }
}