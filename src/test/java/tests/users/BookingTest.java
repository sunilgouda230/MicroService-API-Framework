package tests.users;

import config.ConfigManager;
import models.request.BookingDates;
import models.request.CreateBookingRequest;
import models.response.Booking.BookingDetailsResponse;
import models.response.Booking.BookingResponse;
import models.response.LoginResponse;
import org.testng.annotations.Test;
import service.AuthService;
import service.BookingService;
import tests.base.BaseTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookingTest extends BaseTest {

    @Test
    public void checkBookingIdsPresentOrNot(){
        LoginResponse loginResponse =
                new AuthService().
                        loginAndGetToken(ConfigManager.get("username"),ConfigManager.get("password"));

        List<BookingResponse> bookingResponse = new BookingService().getBookings();
        assertThat(bookingResponse, is(not(empty())));
        int size = bookingResponse.size();
        System.out.println("The size of booking array is "+size);

        List<Integer> bookingIds =
                bookingResponse
                        .stream()
                        .map(BookingResponse::getBookingid)
                        .toList();

        assertThat(bookingIds, hasItems(88, 191, 287));
    }

    @Test
    public void checkDepositPaidIsSelectedOrNot() {

        LoginResponse loginResponse =
                new AuthService().
                        loginAndGetToken(ConfigManager.get("username"),ConfigManager.get("password"));

        List<BookingResponse> bookings =
                new BookingService().getBookings();

        Integer bookingId = bookings.get(0).getBookingid();

        BookingDetailsResponse bookingDetails =
                new BookingService().getBookingById(bookingId);

        assertThat(bookingDetails.isDepositpaid(), is(true));
    }

    @Test
    public void createBookingForNewUser(){

        LoginResponse loginResponse =
                new AuthService().
                        loginAndGetToken(ConfigManager.get("username"),ConfigManager.get("password"));

            // ---------- Request Setup ----------
            BookingDates dates = new BookingDates();
            dates.setCheckin("2018-01-01");
            dates.setCheckout("2019-01-01");

            CreateBookingRequest request = new CreateBookingRequest();
            request.setFirstname("Sunil");
            request.setLastname("Gouda");
            request.setTotalprice(111);
            request.setDepositpaid(true);
            request.setBookingdates(dates);
            request.setAdditionalneeds("Breakfast");

            // ---------- Action ----------
            BookingResponse response =
                    new BookingService().createBooking(request);

            // ---------- Assertions ----------
            assertThat(response.getBookingid(), greaterThan(0));
            assertThat(response.getBooking().getFirstname(), is("Sunil"));
            assertThat(response.getBooking().getLastname(), is("Gouda"));
            assertThat(response.getBooking().isDepositpaid(), is(true));
            assertThat(response.getBooking().getAdditionalneeds(), is("Breakfast"));
        }

    @Test
    public void shouldFailForInvalidBookingId() {
        LoginResponse loginResponse =
                new AuthService().
                        loginAndGetToken(ConfigManager.get("username"),ConfigManager.get("password"));

        List<BookingResponse> bookings =
                new BookingService().getBookings();

        Integer bookingId = bookings.get(0).getBookingid();

        BookingDetailsResponse bookingDetails =
                new BookingService().getBookingById(3148);

        assertThat(bookingDetails.isDepositpaid(), not(true));
    }


    @Test
    public void shouldUpdateBookingSuccessfully() {

        LoginResponse loginResponse =
                new AuthService()
                        .loginAndGetToken(
                                ConfigManager.get("username"),
                                ConfigManager.get("password")
                        );

        // Create booking
        CreateBookingRequest request = new CreateBookingRequest();
        request.setFirstname("Jim");
        request.setLastname("Brown");
        request.setTotalprice(111);
        request.setDepositpaid(true);

        BookingDates dates = new BookingDates();
        dates.setCheckin("2018-01-01");
        dates.setCheckout("2019-01-01");
        request.setBookingdates(dates);

        request.setAdditionalneeds("Breakfast");

        BookingResponse createResponse =
                new BookingService().createBooking(request);

        int bookingId = createResponse.getBookingid();

        // Update values
        request.setFirstname("UpdatedName");
        request.setDepositpaid(false);

        BookingResponse updatedResponse =
                new BookingService().updateBooking(
                        bookingId,
                        request
                );

        // Assertions
        assertThat(updatedResponse.getBooking().getFirstname(), is("UpdatedName"));
        assertThat(updatedResponse.getBooking().isDepositpaid(), is(false));
    }

}
