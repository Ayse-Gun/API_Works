package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResponse {



        /**
         * Nots :
         * 1) Postman manuel testler icin kullanilir
         * 2) API otomasyon testleri icin REST Assured kutuphanesini kullanicaz(POM.xml'e ekledik , Java icin uygundur)
         * 3) Otomasyon code'larini yazarken su adimlari takip edecegiz;
         *    i) Gereksinimleri anlama
         *    ii) Test senaryolarini yazma
         *     ==> Test seneryalorunu yazarken Gherkin dilini kullanicaz
         *     - Given : On kosullar (Endpoint , body )
         *     - When : Islemler : Get , Post Put ,Delete ...
         *     - Then : Assertionlarda ve close islemlerinde (donut) kullaniyoruz
         *     - And : Coklu islemler yapilacagi zaman kullanilir
         *    iii) Otomosyon code larini yazarken su adimlari takip ederiz
         *      1) set the Url
         *      2) Set the expected data
         *      3) Send the request and get the response
         *      4) Do assertion
         */
        //  i. Set the URL
        //  ii. Set the expected data
        // iii. Send the request and get the response
        //  iv. Do assertion

        public static void main(String[] args) {

        String url = "https://petstore.swagger.io/v2/pet/3435";

        Response response = given().when().get(url);  // send request

         response.prettyPrint();  // sadece body kismini yazdiriyor , gercek testlerde body yazdirilmaz

            // Status code nasil yazdirilir ?
            System.out.println("Status code : "+response.statusCode());  //  Status code : 200

            // content Type nasil yazdirilir ?
            System.out.println("Content Type : "+response.contentType());  //  Content Type : application/json

            // Status line nasil yazdirilir ?
            System.out.println("Status line : "+response.statusLine());  //  Status line : HTTP/1.1 200 OK

            // Header bolumunden bir veri nasil yazdirilir ?
            System.out.println("Header | Server : "+response.header("Server"));  //  Header | Server : Jetty(9.2.9.v20150224)

            // Headers bolumu nasil yazdirilir ?
            System.out.println("Headers = " + response.headers());  // Bunlari dogrulama yapmak icin kullanabiliriz

            // Time bilgisi nasil yazdirilir ?
            System.out.println("Time = " + response.time());  // Time = 926

            // //response.prettyPrint();   //response dan gelen cevabi json olarak yazdirmaya yarar
        }
}
