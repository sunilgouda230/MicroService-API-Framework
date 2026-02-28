package models.response.Booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.request.CreateBookingRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponse {

    private int bookingid;
    private CreateBookingRequest booking;

    public int getBookingid() {
        return bookingid;
    }

    public CreateBookingRequest getBooking() {
        return booking;
    }
}