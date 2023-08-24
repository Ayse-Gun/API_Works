package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationHerokuApp {

    public static void main(String[] args) {
       generateToken();
    }
  // //token i otomatik alacak method
    public static String generateToken(){
        String body ="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        Response response = given().body(body).contentType(ContentType.JSON).when().post("https://restful-booker.herokuapp.com/auth");
        response.prettyPrint();
        return response.jsonPath().getString("token");

    }
}
