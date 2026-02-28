package models.response.Booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDetailsResponse {

    private boolean depositpaid;

    public boolean isDepositpaid() {
        return depositpaid;
    }
}
