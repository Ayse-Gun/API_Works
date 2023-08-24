package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get13__ObjectMapper_Map extends JsonPlaceHolderBaseUrl {
    
    /* 
        Given 
            https://jsonplaceholder.typicode.com/todos/198 
        When 
            I send GET Request to the URL 
        Then 
            Status code is 200 
        And response body is like 
            { 
                "userId": 10, 
                "id": 198, 
                "title": "quis eius est sint explicabo", 
                "completed": true 
            } 
    */

    @Test
    public void get13() {
        // Set the url
        spec.pathParams("first","totos","second",198);

        // Set the expected data

        String body = "{ \n" +
                "\"userId\": 10, \n" +
                "\"id\": 198, \n" +
                "\"title\": \"quis eius est sint explicabo\", \n" +
                "\"completed\": true \n" +
         "}";

        Map<String,Object> expextedData = ObjectMapperUtils.convertJsonToJava(body, HashMap.class);  // method ile yazdik

        System.out.println(expextedData);  //{id=198, completed=true, title=quis eius est sint explicabo, userId=10}

        // Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do assertion
        Map<String,Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),HashMap.class);
        System.out.println(actualData);  // json i Stringe sonrada Hashmap'e donusturduk

        assertEquals(200,response.statusCode());
        assertEquals(expextedData.get("userId"),actualData.get("userId"));
        assertEquals(expextedData.get("title"),actualData.get("title"));
        assertEquals(expextedData.get("completed"),actualData.get("completed"));





    }
}

/*
    Suana kadar Api de 5 Assertion methodu gorduk
    Hamcrest Matcher
    Json Path - groovy language
    Response as -Map
    Response as -Pojo
    Object Mapper
    
     */
