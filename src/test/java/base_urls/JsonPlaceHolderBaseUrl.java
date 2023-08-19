package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {
    /**
     * Her sorguda tekrar eden kisimlari buraya yazacagiz
     */

    protected RequestSpecification spec;//requestspecificaion interface old için obje olusturamayız
    @Before
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)  // header assert edilirken setContentType eklenir
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .build();
//Accept Type olarak ContentType.JSON ekledik. Bunu her zaman isteyebilir bu nedenle baseUrls'e ekledik
    }
}

