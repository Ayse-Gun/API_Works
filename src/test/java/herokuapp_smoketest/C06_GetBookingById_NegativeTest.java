package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C06_GetBookingById_NegativeTest extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/:id
        When
            Kullanıcı GET request gönderir
        Then
            Status Code = 404
        And
            Body:
                Not Found
     */
    @Test
    public void getBookingById() {
        // Set the URL
        spec.pathParams("first", "booking", "second", bookingId);
        // Set the expected data
        String expectedData = "Not Found";

        // Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        // Do assertion
        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());
    }
}
