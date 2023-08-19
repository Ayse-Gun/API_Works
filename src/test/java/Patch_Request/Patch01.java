package Patch_Request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {

    /*
    Given
        1) https://jsonplaceholder.typicode.com/todos/198
        2) {
              "title": "Wash the dishes"
           }
    When
      I send PATCH Request to the Url
    Then
          Status code is 200
          And response body is like
              {
                "userId": 10,
                "title": "Wash the dishes",
                "completed": true,
                "id": 198
              }
     */

    // Burada sadece tile i update yapiyoruz
    ////burda bir payload map olusturup
    // body de sorguya bunu gonderdik.
    // Ayri bir expectedData map olustururak da bun dogrulama isleminde kullandik

    @Test
    public void patch01() {
        // Set the Url

        spec.pathParams("first", "topdos", "second", 198);

        // Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> payLoad = obj.expectedDataMethod(null, "Wash the dishes", null);
        Map<String, Object> expectedData = obj.expectedDataMethod(10, "Wash the dishes", null);


        // send the request and get the response
        Response response = given(spec).body(expectedData).when().patch("{first}/{second}");
        response.prettyPrint();

        // Do assertion

        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));

        assertEquals(expectedData.get("completed"),actualData.get("completed"));



    }

}
