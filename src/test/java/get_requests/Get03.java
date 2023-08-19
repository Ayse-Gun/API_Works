package get_requests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 {
    /**
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
        And
            "title" şu metni içermeli: "et itaque necessitatibus maxime molestiae qui quas velit",
        And
            "completed" değeri false olmalı
        And
            "userId" değeri 2 olmalı
     */

    @Test
    public void get03() {

        // HARD ASSERTION

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/todos/23";

        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Burada kes yapistir hatasi olmsin diye "ContentType.JSON)"kismi yazdik,//"application/json" yerine

                .body("title",containsString("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed",equalTo(false))
                .body("userId",equalTo(2));

    }

    @Test
    public void softAssertion() {
      // SOFT ASSERTION
        //SOFT ASSERTION yapmak icin bütün body'leri iç içe geçirmemiz gerekiyor.


        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/todos/23";

        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",containsString("et itaque necessitatibus maxime molestiae qui quas velit")
                ,"completed",equalTo(false)
                ,"userId",equalTo(2));

        /**
         * Expected: a string containing "et itaque necessitatibus maxime molestiae qui quas velitW"
         *   Actual: et itaque necessitatibus maxime molestiae qui quas velit
         *
         * JSON path completed doesn't match.
         * Expected: <true>
         *   Actual: <false>
         *
         * JSON path userId doesn't match.
         * Expected: <21>
         *   Actual: <2>
         *       Degerleri degistirdigimizde bunlari yazdirdi
         */

    }

}


