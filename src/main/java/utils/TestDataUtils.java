package utils;

import com.github.javafaker.Faker;
import models.request.BookingDates;
import models.request.CreateBookingRequest;

import java.time.LocalDate;

public class TestDataUtils {

    private static final Faker faker = new Faker();

    public static void applyRandomNames(CreateBookingRequest request) {
        request.setFirstname(faker.name().firstName());
        request.setLastname(faker.name().lastName());
    }

    public static void applyRandomAmount(CreateBookingRequest request){
        request.setTotalprice(faker.number().numberBetween(4000,5000));
    }

    public static void applyRandomNeeds(CreateBookingRequest request){
        request.setAdditionalneeds(faker.food().dish());
    }

    public static void applyRandomDates(CreateBookingRequest request) {

        BookingDates dates = new BookingDates();
        LocalDate checkin = LocalDate.now()
                .plusDays(faker.number().numberBetween(1, 30));

        LocalDate checkout = checkin
                .plusDays(faker.number().numberBetween(1, 10));

        dates.setCheckin(checkin.toString());
        dates.setCheckout(checkout.toString());

        request.setBookingdates(dates);
    }


    public static CreateBookingRequest generateRandomBooking(boolean depositPaid) {

        CreateBookingRequest request = new CreateBookingRequest();

        applyRandomNames(request);
        applyRandomAmount(request);
        applyRandomNeeds(request);
        applyRandomDates(request);

        request.setDepositpaid(depositPaid);

        return request;
    }
}