package utils;

import io.restassured.response.Response;
  import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

    public class AuthenticationGmiBank {
        public static void main(String[] args) {
            System.out.println(generateToken());
        }
        public static String generateToken(){
            String body = "{\n" +
                    "    \"password\": \"Mark.123\",\n" +
                    "    \"rememberMe\": true,\n" +
                    "    \"username\": \"mark_twain\"\n" +
                    "}";
            Response response = given().body(body).contentType(ContentType.JSON).when().post("https://gmibank.com/api/authenticate");
            return response.jsonPath().getString("id_token");
        }
    }
    /*
    //String'e koydugumuz verileri GmiBankBackend API dokumentasyonundan aldik. Orada Post /api/authenticate
    //kisminda loginVM'deki body'i kopyaladik ve postman'da post islemi url bar'a url ve sonunda
    //authenticate'i ekledik. Kopyaladigimiz body'i Body, raw, JSON sonrasi bosluga ekledik password ile
    //username'e isim verdik. send va save sonrasi gelen token'i Header'a ekledik
    Response response= given().body(body).contentType(ContentType.JSON).when().post("https://gmibank.com/api/authenticate");
    //post icine postman'da authentication yaparak token'i almak isterken url bar'a ekledigimiz url'i koyduk
    response.prettyPrint();
    return response.jsonPath().getString("id_token"); //burada getString icine postman'da response
    //olarak gelen veride baslangic kismi id_token olarak gelmisti. Onu yazdik.



     */




