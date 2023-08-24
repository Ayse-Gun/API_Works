package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class Post01 extends JsonPlaceHolderBaseUrl {
   /**
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
        When
            Kullanıcı URL'e bir POST request gönderir
        Then
            Status code 201 olmalı
        And
            Response şu şekilde olmalı:
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */
   @Test
   public void post01() {

       // 1.Set the Url

       spec.pathParam("first","todos");
       // 2. Set the expected data , bu kismi ilk defa burada yapicaz cunku get de body ihtiyacimiz yoktu

       String payLoad = "{\n" +
               "    \"userId\": 55,\n" +
               "    \"title\": \"Tidy your room\",\n" +
               "    \"completed\": false\n" +
               "}";
       // 3. Send the request and get the response

       Response response = given(spec).body(payLoad).when().post("{first}");  // on hazirligin , ilkini yaptik ,simdi body kismini hazirlayalim
       response.prettyPrint();
       // 4. Do assertion
       /**
        *                 "userId": 55,
        *                 "title": "Tidy your room",
        *                 "completed": false,
        *                 "id": 201
        */
       //   Status code 201 olmalı
       assertEquals(201,response.statusCode());


       JsonPath json = response.jsonPath();
       assertEquals(55,json.getInt("userId"));
       assertEquals("Tidy your room",json.getString("title"));
       assertFalse("false",json.getBoolean("completed"));


       /**
         Id yi otamatik olusturdu bizim icin , manuel olarak postmanda post yaptik
        payLoad ==> Gonderilecek verinin adina denir ,postmanden alip aynen yapistiriyoruz
        ve hicbir degisiklik yapmiyoruz

        setContentType(ContentType.JSON) ==> kismini JsonPlaceHolderBaseUrl'e ekledik
        cunku her post isleminde kullanicaz
        ve code kalabalikligi olmamasi icin oraya aldik
        */

   }

    @Test
    public void post01Map() {

        // 1.Set the Url

        spec.pathParam("first","todos");
        // 2. Set the expected data , burada Map olusturcaz


        Map<String,Object> expectedData = new HashMap<>();

        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);
        System.out.println(expectedData);
        // {completed=false, title=Tidy your room, userId=55} verdi
        /**
         *
         *             "userId": 55,
         *         *                 "title": "Tidy your room",
         *         *                 "completed": false,
         *         *                 "id": 201
         * Biz map gonderiyoruz karsi tarafa ama bizden json bekleniyor, bu java objesini jason objesine donusturmeliyiz
         * Java object'in Json object'e cevrilmesine serialization deniyor
         * Bunu yapabilmek icin de POM.xml'e Jackson repository'sini ekleyecegiz
              Serialization = Java Map datasının Json datasına çevrilmesidir
              Deserialization ---> Json Objesinin Java Objesine cevrilmesidir.
         */

        // 3. Send the request and get the response

        Response response = given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();
        // 4. Do assertion
        /**
         *                 "userId": 55,
         *                 "title": "Tidy your room",
         *                 "completed": false,
         *                 "id": 201
         */

               // Do assertion

        // Diger dogrulamalari kaldirabiliriz
        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

        //  Deserialization ---> Json Objesinin Java Objesine cevrilmesidir.
        // islemini yaptik


    }
}
