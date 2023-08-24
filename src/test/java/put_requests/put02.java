package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponsePojo;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class put02 extends DummyRestApiBaseUrl {

   /*
    Test Case: Type by using Gherkin Language
       URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */
    /*
Given
   1) URL: https://dummy.restapiexample.com/api/v1/update/21
   2)   {
            "employee_name": "Ali Can",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image"
         }
When
   Kullanıcı PUT Request gönderir

Then
    i) Status code is 200
And
    ii) Response body should be like the following
        {
            "status": "success",
            "data": {
                "employee_name": "Ali Can",
                "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image"
            },
            "message": "Successfully! Record has been updated."
        }
 */

    @Test
    public void put02() {

        // Set the url
      spec.pathParams("First","update","second",21);
      // Set the expected data
      // Pojomuzu olusturup kullanabiliriz

        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Ali Can",111111,23,"Perfect");
        System.out.println("expectedData = " + expectedData);
        // Send the request and get the response
        Response response = given(spec).body(expectedData).when().put("{first}/{second}");
        response.prettyPrint();

        // Do assertion

       DummyRestApiResponsePojo actualData = convertJsonToJava(response.asString(), DummyRestApiResponsePojo.class);
        System.out.println(actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());
        assertEquals("success", actualData.getStatus());
        assertEquals("Successfully! Record has been updated.", actualData.getMessage());
    }
}
