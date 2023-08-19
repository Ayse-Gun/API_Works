package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /**
              OZET :
          API'lerde en onemli kisim gonderdiginiz veri ile gelen veri birbiri ile ortusup ortusmedigidir.
          Biz farkli yollarla (Hamcrest Matchers,assertion vs.) bunu dogrulariz.

              Swagger Dokumani:
          Api dokumantasyonu icin Swagger i kullaniriz.
          Kullanacagimiz end pointleri, methodlari (get, put, post) bunlari nasil ve hangi petlerle kullanacagimizi anlatir.
          Icine girince testlerimizi orda da yapabiliriz
          Bize gelebilecek ornek response datayi da saglar
     *
     *        Postman:
     *     API test araci. Hem manuel hem otomasyon ile test yapilabilir.
     *
     *     Serialization = Java Map objesinin Json objesine donusturulmesidir.
     *     Deserialization = Json Objesinin Java Map Objesine donusturulmesidir.
     *
     *      */
    /**
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
        I send GET Request to the URL
    Then
        Status code is 200
        And "completed" is false
        And "userId" is 1
        And "title" is "quis ut nam facilis et officia qui"
        And header "Via" is "1.1 vegur"
        And header "Server" is "cloudflare"
        {
            "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
        }
    */

    @Test
    public void Get08() {

        //1. Set the URL
        spec.pathParams("first", "todos", "second", 2);


        //2. Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");  // bunlari ekledik , yani bu sonucu alacagimizi declare ediyoruz



        //3. Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();   // data json formatinda geliyor , o yuzden hashMap'e geciyoruz ki iki Map'i karsilastirabilelim


        //4. Do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(200 , response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("Via"),response.header("Via"));
        assertEquals(expectedData.get("Server"),response.header("Server"));


        //Status Code dogrulamasi
    }
}
