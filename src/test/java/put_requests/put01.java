package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import io.restassured.response.Response;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class put01 extends JsonPlaceHolderBaseUrl {

    /**
        Given
            1) https://jsonplaceholder.typicode.com/todos/198
            2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }

        When
            Kullanıcı URL'e bir PUT request gönderir

        Then
           Status code 200 olmalı
           Response şu şekilde olmalı:
           {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
                "id": 198
           }
     */

    @Test
    public void put01() {
   // Set The url
        spec.pathParams("first","todos","second",198);

        // Set the expected data
        Map<String,Object> expecteddata = new HashMap<>();
        expecteddata.put("user",21);
        expecteddata.put("title","Wash the dishes");
        expecteddata.put("completed",false);

        System.out.println("Expected Data : "+expecteddata);
  // Send the request and get the response

        Response response = given(spec).body(expecteddata).when().put("{first}/{second}");
        response.prettyPrint();
 // Do assertion
        Map<String,Object> actualDaata = response.as(HashMap.class);  // deserialization
        System.out.println("Actual Data : "+actualDaata);

        // Status code dogrulamasi
        assertEquals(200,response.statusCode());

        assertEquals(expecteddata.get("completed"),actualDaata.get("completed"));
        assertEquals(expecteddata.get("title"),actualDaata.get("title"));
        assertEquals(expecteddata.get("userId"),actualDaata.get("userId"));



    }
    @Test
    public void put01b() {
        // Set The url , Method ile cozumu
        spec.pathParams("first","todos","second",198);

        // Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataMethod(21, "Wash the dishes", false);
        System.out.println("Expected Data: " + expectedData);
        // Send the request and get the response

        Response response = given(spec).body(expectedData).when().put("{first}/{second}");
        response.prettyPrint();
        // Do assertion
        Map<String,Object> actualDaata = response.as(HashMap.class);  // deserialization
        System.out.println("Actual Data : "+actualDaata);

        // Status code dogrulamasi
        assertEquals(200,response.statusCode());

        assertEquals(expectedData.get("completed"),actualDaata.get("completed"));
        assertEquals(expectedData.get("title"),actualDaata.get("title"));
        assertEquals(expectedData.get("userId"),actualDaata.get("userId"));

  //header, status code, time, size bilgileri response'un icerisinde mevcut.
        // actualData ile sadece body icesindeki datalari test ettik

    }
}
