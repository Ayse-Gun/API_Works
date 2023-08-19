package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class delete01 extends JsonPlaceHolderBaseUrl {


    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            I send DELETE Request to the Url
        Then
            Status code is 200
        And Response body is { }
    */


    @Test
    public void delete01() {

        spec.pathParams("first","todos","second",198);

        // set the expected data
        // Bos bir map olusturalim

        Map<String,String> expectedData = new HashMap<>();

        // send the request and get the response

        Response response = RestAssured.given(spec).when().delete("{first}/{second}");  // restassured kismi olmayabilirde
        response.prettyPrint();

        // Do assertion
        Map<String,String> actualData = convertJsonToJava(response.asString(),HashMap.class);

        assertEquals(200,response.statusCode());
        //1.way
        assertEquals(0,actualData.size());

        // 2.way

        assertEquals(expectedData,actualData);

        // 3.way

        assertTrue(actualData.isEmpty());
    }
}
