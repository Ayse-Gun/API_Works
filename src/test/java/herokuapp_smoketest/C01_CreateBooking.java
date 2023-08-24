package herokuapp_smoketest;

import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C01_CreateBooking extends HerokuAppBaseUrl {


    /*

    Given
        https://restful-booker.herokuapp.com/booking
    And
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
    When
        Kullanıcı post request gönderir
    Then
        Status code = 200
    And
        {
            "bookingid": 1,
            "booking": {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
        }
 */

    // datalar bize buradan geliyor , o yuzden buradan olusturuyoruz

    public static int bookingId;
    @Test
    public void post01() {
        // set the url
        spec.pathParam("first","booking");
        // Set the expected data

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expextedData = new BookingPojo("Jim","Brown",111,true,bookingDates,"Breakfast");

        // Send the request and get the response

        Response response = given(spec).body(expextedData).when().post("{first}");
        response.prettyPrint();
        bookingId = response.jsonPath().getInt("bookingid");  // statik yapalim diger classdan kullanabilmek icin

        System.out.println("bookingid = " + bookingId);  // 470 geldi

        // Do assertion

        BookingResponsePojo actualData = convertJsonToJava(response.asString(),BookingResponsePojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expextedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expextedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expextedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expextedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expextedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

   /*
    // Do assertion
        BookingResponsePojo actualData = convertJsonToJava(response.asString(), BookingResponsePojo.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
    }
}

    */


    }
}
