package post_requests;

import Pojos.JsonPlaceHolderPojo;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post03 extends JsonPlaceHolderBaseUrl {
     
    /* 
    Given 
       1) https://jsonplaceholder.typicode.com/todos 
       2)  { 
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
    // Neden postman da otomosyon yapiyorken ij de yapiyoruz?


    @Test
    public void post05() {
        /*
        Payload Olusturma(Pojo Class) :variable lari private yapip
         getter, setter methodlariyla cagisirip degisiklik yaptigimiz class lardir.
         */

        // Set the Url
        spec.pathParam("first","todos");


         // Set the expected data
        JsonPlaceHolderPojo expextedData = new JsonPlaceHolderPojo(55,"Tidy your room",false);

        //Send the request and get the response

       Response response =  given(spec).body(expextedData).when().post("{first}");
       response.prettyPrint();

       // Do assertion

        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);

        assertEquals(201,response.statusCode());
        assertEquals(expextedData.getUserId(),actualData.getUserId());
        assertEquals(expextedData.getTitle(),actualData.getTitle());
        assertEquals(expextedData.getCompleted(),actualData.getCompleted());




    }
}

// EndToEnd testinde:
//UI testi--> API Testi --> DAtabase Testi yapabiliyoruz ==> E2E Test
/*

Biz intellij de hem UI(Selenium ile) - Api(Rest Assured ile ) - Database(JDBC ile) testi yapabiliriz

Postman da otomasyon yapilabilir. Lakin postman de sadece Api Testi yaparken Intellij de E2E testi yapabiliriz
UI Testi ---> API Testi ---> Database Testi === E2E TEST  .....Burcu


//UI Testi----> API Testi ------> Database Testi
//Postman'de API testi yapabiliriz ancak UI ve DataBase Testlerini yapamayiz.
//Bir veri gonderiyoruz, register islemi yapiyoruz.
 Bunu UI'da yaptik ve bilgileri intellij'de bir pojo class'ta kaydedebiliriz
//Bu kayitli bilgileri UI ile DataBase Testleri ile kontrol edebiliriz.
UI, DataBase ve API testlerini yapabildigimizde full stack Otomation Engineer olabiliriz.
Bu testleri intellij'de yapabiliriz..... Zeynep
 */
