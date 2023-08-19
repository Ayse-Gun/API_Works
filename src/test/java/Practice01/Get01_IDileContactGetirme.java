package Practice01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class Get01_IDileContactGetirme {

    @Test
    public void get01() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = "/contacts/64d7e5e736c2810013fe7d8d";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGQ3YzQ4YTM2YzI4MTAwMTNmZTdjMWIiLCJpYXQiOjE2OTE4NjM0MjV9.a6KNxYCBHQz7i-tR6vYMtUTRCKwCvQuaWI9C_CygOl8";


       Response response =
               given()
                .auth().oauth2(token)    // tokenlar icin
                .when()
                .get();  // get requestin responsu
       response.prettyPrint();



       response
               .then()
               .body("firstName",equalTo("X"))
               .body("lastName",equalToIgnoringCase("doe"))
               .body("email",not(equalTo("jdo@fake.com")))
               .body("email",containsString("@fake.com"))
               .body("city",startsWith("Any"))
               .body("city",endsWith("own"))
               .body("stateProvince",anyOf(equalTo("KS"),equalTo("CA")))
               .body("country",allOf(equalTo("USA"),equalToIgnoringCase("usa")))
               .body("__v",greaterThan(-1));


    }
}

