package get_requests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertTrue;

public class Get04 {
    @Test
    public void Get01() {

        /**
     Given
         https://restful-booker.herokuapp.com/booking/0
     When
         Kullanıcı URL'e bir GET request gönderir
     Then
         HTTP Status code 404 olmalı
     And
         Status Line "HTTP/1.1 404 Not Found" olmalı
     And
         Response body "Not Found" içermeli
     And
         Response body "TechProEd" içermemeli
     And
         Server değeri "Cowboy" olmalı

         NOTES : response 'u asString() methoduyla String'e cevirip
         String Class methodlarini (contains vs.) kullanabiliriz


 */
        String url = "https://restful-booker.herokuapp.com/booking/0";
        Response response = given().when().get(url);
        //response.prettyPrint();
        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found");

       assertTrue(response.asString().contains("Not Found"));
       assertFalse(response.asString().contains("Not FoundX"));
       assertEquals(response.asString(),"Not Found");

       assertEquals("Cowboy",response.header("Server"));
       response
               .then()
               .body(containsString("Not Found"))
               .body(not(containsString("techProEd")));  // olmayan biseyi yazdik

    }


}
