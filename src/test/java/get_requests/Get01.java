package get_requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

    /**
        Given
            https://restful-booker.herokuapp.com/booking/1
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
        And
            Status Line "HTTP/1.1 200 OK" olmalı
    */
    /**
     * //given() kismi testin basinda request'in hazirlanmasi asamasidir. Testin temel kosullarinin olusturdugumuz adimdir.
     *         // Orn: Rezervasyon bilgilerini siteye girme islemi//when() kismi olusturulan kosullarin eyleme gecirilip gerceklesmesi adimidir. get,put, post islemleri gibi...
     *         //Orn: Rezervasyonu onayla butonuna tiklanmasi
 Selenium ne icin kullanilir? Web sitelerinde otomasyon yapmak icin kullanilir
     fakat ;Restasured kutuphanesinden kullandigimiz uygulama ise API otomasyonlari icin kullanilir
     */



    @Test
    public void get01(){
        // 1.yontem
      //  String url = "https://restful-booker.herokuapp.com/booking/1 ";  // cok tercih edilen bir yol degil ,diger yontem soyle;

        // 2.yontem
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";  // burasi degismez
        RestAssured.basePath = "/booking/1";  // fakat bu deger degisebilir , o yuzden 2. yontem daha avantajlidir

       Response response = given().when().get();
       response.prettyPrint();  // aseertionlari bundan sonra response kullanicaz

        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 OK"); // method zinciri

    }
}
