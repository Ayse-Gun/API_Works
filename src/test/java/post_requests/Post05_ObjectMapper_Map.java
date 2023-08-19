package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post05_ObjectMapper_Map extends JsonPlaceHolderBaseUrl{
    
    /* 
        Given 
            1) https://jsonplaceholder.typicode.com/todos 
            2) { 
            "userId": 55, 
            "title": "Tidy your room", 
            "completed": false 
            } 
        When 
            I send POST Request to the Url 
        Then 
            Status code is 201 
        And 
            response body is like 
            { 
                "userId": 55, 
                "title": "Tidy your room", 
                "completed": false, 
                "id": 201 
            } 
     */

    @Test
    public void post05() throws JsonProcessingException {

        // Set the Url
        spec.pathParam("first","todos");

        // Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(55,"Tidy your room",false);
        System.out.println("expecteddata = " + expectedData);

        // Send the request and get the response

        Response response = given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();

        // do assertion

        ObjectMapper objectMapper = new ObjectMapper(); // import com.fasterxml.jackson.databind.ObjectMapper; burdan almaliyiz
        Map<String,Object> actualData = objectMapper.readValue(response.asString(), HashMap.class);   // exeption firlatan method
       // Map<String,Object> actualData2 = response.as(HashMap.class);   // oylede kulanabiliriz
        System.out.println("actualdata = " + actualData);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        /*
        Map<String, Object> actualData=objectMapper.readValue(response.asString(), HashMap.class) ;
        Normalde esitligin saginda response.as(HashMap.class); yazip onu bir Map icine koyuyorduk.
        Ancak burada gelen datayi once bir String'e ceviriyor.
        response bize gelen json ve biz json'i once string'e ceviriyoruz.
        Sonra da onu HashMap class'ina koyuyoruz
         */



    }


}
