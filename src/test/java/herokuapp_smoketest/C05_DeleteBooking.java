package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
public class C05_DeleteBooking extends HerokuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Kullanıcı DELETE request gönderir
    Then
        Status code = 201
    And
        Body:
            Created
 */

    @Test
    public void deleteBooking() {
        // Set the URL
        spec.pathParams("first", "booking", "second", bookingId);
        // Set the expected data
        String expectedData = "Created";
        // Send the request and get the response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();
        // Do assertion
        assertEquals(201, response.statusCode());
        assertEquals(expectedData, response.asString());
    }
}

