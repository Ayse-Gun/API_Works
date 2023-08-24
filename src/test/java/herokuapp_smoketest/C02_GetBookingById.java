package herokuapp_smoketest;

import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static herokuapp_smoketest.C01_CreateBooking.bookingId;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C02_GetBookingById extends HerokuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/:id
        When
            Kullanici GET request gonderir
        Then
            Status Code = 200
        And
            Body:
              {
                 "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
        }
     */

    @Test
    public void getBookingById() {
        // Set the url
        spec.pathParams("first","booking","second", bookingId); // dinamik hale getirmemiz gerekir

        // Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expextedData = new BookingPojo("Jim","Brown",111,true,bookingDates,"Breakfast");

        // Send the request and get the response

        Response response = given(spec).when().get("{first}/{second}");


        // Do assertion
        BookingPojo actualdta = convertJsonToJava(response.asString(),BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expextedData.getFirstname(),actualdta.getFirstname());
        assertEquals(expextedData.getLastname(),actualdta.getLastname());
        assertEquals(expextedData.getTotalprice(),actualdta.getTotalprice());
        assertEquals(expextedData.getDepositpaid(),actualdta.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualdta.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualdta.getBookingdates().getCheckout());
        assertEquals(expextedData.getAdditionalneeds(),actualdta.getAdditionalneeds());

    }
}

