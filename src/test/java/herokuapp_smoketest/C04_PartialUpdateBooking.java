package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04_PartialUpdateBooking extends HerokuAppBaseUrl {


    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname" : "James",
            "lastname" : "Brown"
        }
    When
        Kullanıcı PATCH request gönderir
    Then
        Status Code = 200
    And
        {
            "firstname" : "James",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }

        Notes : Mapleri kullanicaz verilerin degismemesi icin
 */

    @Test
    public void partialUpdateBooking() {
        // Set the url
        spec.pathParams("first","booking","second","bookingId");

        // set the expexted data
        HerokuAppTestData obj = new HerokuAppTestData();
        Map<String,Object> expextedData = obj.expectedDataMapper("Ali","Can",null,null,null,null);

        // send the request
        Response response = given(spec).body(expextedData).when().patch("{first/{second}");
        response.prettyPrint();

        // Do assertion
        Map<String, String> actualData = convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(expextedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expextedData.get("lastname"), actualData.get("lastname"));

   /*
        Burda patch islemi ile sadece bir bolumu degistirdigimiz icin diger bolumleri
         null olarak yazdik. expectedDataMapper() methodunda zaten Parametrelerin null gelme ihtimaline
         karsi if li ifadeleri olusturduk. Patch islemi icin diye belirtmistik
         */



    }
}























