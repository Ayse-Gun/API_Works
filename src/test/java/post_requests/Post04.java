package post_requests;

import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04 extends HerokuAppBaseUrl {

    /*
        Given
          1)  https://restful-booker.herokuapp.com/booking
          2) {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
              }
        When
            I send POST Request to the URL
        Then
            Status code is 200
        And
            Response body is like
                 {
                    "bookingid": 16,
                    "booking" :{
                        "firstname": "Ali",
                        "lastname": "Can",
                        "totalprice": 999,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2021-09-21",
                            "checkout": "2021-12-21"
                        },
                        "additionalneeds": "Breakfast"
                     }
                  }
     */

    // Booking icin pojo lazim


    @Test
    public void name() {
        // Set the Url
        spec.pathParam("first", "booking");

        // Send the Expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo expectedData = new BookingPojo("Ali", "Can", 999, true, bookingDates, "Breakfast");


        // Send the Request and Get the Response
        Response response = given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();

        // Do Assertion
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println(actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
//        assertEquals(expectedData.getBookingdates(),actualData.getBooking().getBookingdates()); Alttaki 2 assertioni yapmamdan
//        bu sekilde de assert edilebiliriz. Ancak herhange bir hata aldigimizda checkin den mi yoksa checkout tanmi oldugu belli olmaz
        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
  /*
  <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310 -->
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
    <version>2.15.2</version>
</dependency>

Notes: Data type'lari arasinda casting yapmada kullaniliyor
,ihtiyac olmadikca pom.xml 'e eklememek lazim cunku calismasini yavaslatabilir
////response as methodu ve sadece json ve java datalari arasinda donusum yapmaz.
//  jackson databind ve jackson.datatype dependency leri Integer variable i String'e,
 String'i Local Date'e cevirebilmemize imkan tanir
   */
    }
}
