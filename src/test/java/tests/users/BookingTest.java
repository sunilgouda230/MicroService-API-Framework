package tests.users;

import config.ConfigManager;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import models.request.CreateBookingRequest;
import models.response.Booking.BookingDetailsResponse;
import models.response.Booking.BookingResponse;
import models.response.LoginResponse;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import service.AuthService;
import service.BookingService;
import tests.base.BaseTest;
import utils.TestDataUtils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Listeners({AllureTestNg.class})
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

        assertThat(bookingIds.size(), greaterThan(0));
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

        // Create booking with single line
        CreateBookingRequest request =
                TestDataUtils.generateRandomBooking(true);

        BookingResponse response =
                new BookingService().createBooking(request);

            // ---------- Assertions ----------
            assertThat(response.getBookingid(), greaterThan(0));
            assertThat(response.getBooking().isDepositpaid(), is(true));
        }

    @Test
    public void shouldFailForInvalidBookingId() {
        LoginResponse loginResponse =
                new AuthService().
                        loginAndGetToken(ConfigManager.get("username"),ConfigManager.get("password"));

        List<BookingResponse> bookings =
                new BookingService().getBookings();

        try {
            Response response =
                    new BookingService().getBookingRawResponse(3148999);
            assertThat(response.getStatusCode(), is(404));
        } catch (Exception e){
            System.out.println("The Booking Id not Found"+e.getMessage());
        }
    }


   @Test
    public void deleteBookingById(){
       LoginResponse loginResponse =
               new AuthService().
                       loginAndGetToken(ConfigManager.get("username"),ConfigManager.get("password"));

       List<BookingResponse> bookings =
               new BookingService().getBookings();

       Integer bookingId = bookings.get(0).getBookingid();

           Response response = new BookingService().deleteBooking(bookingId);

           response.then()
                   .statusCode(201)
                   .body(equalTo("Created"));
       }

}
