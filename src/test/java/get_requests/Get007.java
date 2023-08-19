package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class Get007 extends HerokuAppBaseUrl {

    /**
     * Given
     *         https://restful-booker.herokuapp.com/booking/22
     *     When
     *         User send a GET request to the URL
     *     Then
     *         HTTP Status Code should be 200
     *     And
     *         Response content type is “application/json”
     *     And
     *         Response body should be like;
     *       {
     *         "firstname": "John",
     *         "lastname": "Smith",
     *         "totalprice": 111,
     *         "depositpaid": true,
     *         "bookingdates": {
     *             "checkin": "2018-01-01",
     *             "checkout": "2019-01-01"
     *         },
     *         "additionalneeds": "super bowls"
     *     }
     */

    @Test
    public void Get() {

        // 1. set the url
        spec.pathParams("first","booking"
                ,"second",25);

        //2. set expected data  , data yok
        // 3.send rep and get resp

       Response response = given().spec(spec).when().get("{first}/{second}");
       response.prettyPrint();

       //4. Do assertion

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("Jane")
                        ,"lastname",equalTo("Doe")
                        ,"totalprice",equalTo(111)
                        ,"depositpaid",equalTo(true)
                        ,"bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Extra pillows please"));

        /**
         * Body uzerinde yaptigimiz islemlerin aynisini Header ler uzerindede yapabiliyoruz
         */


        // 2. Way

        JsonPath json = response.jsonPath(); // response jsonpath() mtd kullanarak JsonPath data cesidine donusturduk
        // JsonPath'den response deki datalara kolayca ulasabiliriz

        System.out.println(json.getString("firstname"));   //   Josh olarak verdi

        assertEquals("Jane",json.getString("firstname"));  // Direkt firstname e ulasip bunu String bir kutuya koymamaizi sagliyor
        assertEquals("Doe",json.getString("lastname"));  // Fakat requestler buna izin vermiyor
        assertEquals(111,json.getInt("totalprice"));
        assertEquals(true,json.getBoolean("depositpaid"));

        assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",json.getString("bookingdates.checkout"));
        assertEquals("Extra pillows please", json.getString("additionalneeds"));

        // 3.way (Soft Assertion)
        //Soft assertion hangi frame worke ait? TestNj

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("firstname"),"Jane");
        softAssert.assertEquals(json.getString("lastname"),"Doe");
        softAssert.assertEquals(json.getInt("totalprice"),111);
        softAssert.assertTrue(json.getBoolean("depositpaid"));
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01");
        softAssert.assertEquals(json.getString("additionalneeds"),"Extra pillows please");

        softAssert.assertAll();


    }
}
