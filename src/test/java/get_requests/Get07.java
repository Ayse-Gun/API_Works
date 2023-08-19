package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {
    /**
        Given
           https://jsonplaceholder.typicode.com/todos
        When
           Kullanıcı URL'e bir GET request gönderir
        Then
           1) Status code 200 olmalı
           2) "Id"leri 190 dan büyük olanları konsola yazdırın
              "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
           3) "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
              "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
           4) "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
              "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
     */

    @Test
    public void get07() {

        // Groovy Language
        //Groovy language list icindeki json'lari sorgulama yapip data geri cagirmamizi saglar
        //uniq degerlerle(id, email..) bir tabloda bir kisinin bilgilerine ulasabiliriz.

        spec.pathParam("first","todos");  // Base url i JsonPlaceHolderBaseUrl icine koyduk , degisebilen kismini burada tanimliyoruz

        Response response = given(spec).when().get("{first}");
        //response.prettyPrint();  // Postmndan manuel olarak bakilabilir , burayi yazmaya gerek olmayabilir

        assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();
        List<Object> list = json.getList("id");
        System.out.println(list.size());   // 200

        /**
         *  verileri tek tek alip kullanabiliyoruz, conteynera koyarak
         Burada response daki verileri json'a oradan da liste koyduk ve
         hangi degeri gormek istiyorsak "" icinde key degerini yazdiriyoruz , ornegin "id" gibi
         */

        //  2) "Id"leri 190 dan büyük olanları konsola yazdırın

       // List<Object> idList = json.getList("findAll{it.id>190}");  // Burada it  , item kisaltmasi
        List<Object> idList = json.getList("findAll{it.id>190}.id");  // yazarsak sadece id degerlerini aliriz
        System.out.println("ID LIst : "+idList);  // ID LIst : [191, 192, 193, 194, 195, 196, 197, 198, 199, 200]


        System.out.println("ID LIst : "+idList.size());  // ID LIst : 10

        List<Object> idListTite = json.getList("findAll{it.id>190}.title");
        System.out.println(idListTite);

       // "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın

        assertEquals(10,idList.size());

       // "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın

        List<Integer> userIdList = json.getList("findAll{it.id<5}.userId");
        System.out.println("User ID List : "+userIdList);  // User ID List : [1, 1, 1, 1]

        // "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın

        assertEquals(4,userIdList.size());

        //"Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        List<Object> titleListLessThen5 = json.getList("findAll{it.id<5}.title");
        System.out.println("Id si 5 ten küçük tüm kullanıcıların Titlelari : "+titleListLessThen5);

        //  "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
        //     */ başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın

        assertTrue(titleListLessThen5.contains("delectus aut autem"));

        // title'i "delectus aut autem" olan elemanin id numarasi nasil bulunur

        System.out.println(json.getList("findAll{it.title=='delectus aut autem'}.id"));  // [1] verdi

        // Bu yontem cok kullanisli bir yoldur
        // FindAll List nerede basliyorsa orada kullaniyoruz , icice durumlarinda da ayni
        //body nin icerisine gondercegimiz veriye payLoad denir



    }
}
