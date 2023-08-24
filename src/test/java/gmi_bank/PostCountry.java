package gmi_bank;

import base_urls.GmiBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.gmi_bank.PostCountryPojo;
import pojos.gmi_bank.StatesPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class PostCountry extends GmiBankBaseUrl {
  /*
        https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1 dokümanını kullanarak en az 3 "state"
        içeren bir "country" oluşturan bir otomasyon testi yazınız.
        Not : Autherization için headers'a "Authorization" = ""Bearer abc123"  şeklinde Bearer token giriniz.
     */

    /*
            Given
                https://gmibank.com/api/tp-countries
            And
                {
                    "name": "Muz Cumhuriyeti",
                    "states": [
                        {
                            "id": 1,
                            "name": "Elma"
                        },
                        {
                            "id": 2,
                            "name": "Armut"
                        },
                        {
                            "id": 3,
                            "name": "Portakal"
                    }
                ]
            }
            When
                Kullanici POST request gonderir
            Then
                Status Code = 201
            And
                Body:
                    {
                        "id": 191762,
                        "name": "Muz Cumhuriyeti",
                        "states": [
                            {
                                "id": 1,
                                "name": "Elma",
                                "tpcountry": null
                            },
                            {
                                "id": 2,
                                "name": "Armut",
                                "tpcountry": null
                            },
                            {
                                "id": 3,
                                "name": "Portakal",
                                "tpcountry": null
                        }
                    ]
                }
     */

    @Test
    public void postCountry() {
        //Set the URL
        spec.pathParams("first","api","second","tp-countries");

        //Set the expected data
        StatesPojo state1 = new StatesPojo(1,"Elma");
        StatesPojo state2 = new StatesPojo(2,"Armut");
        StatesPojo state3 = new StatesPojo(3,"Portakal");
        List<StatesPojo> stateList = new ArrayList<>();
        stateList.add(state1);
        stateList.add(state2);
        stateList.add(state3);

        PostCountryPojo expectedData = new PostCountryPojo("Muz Cumhuriyeti",stateList);
        System.out.println(expectedData);

        //Send the request and get the expected data
        Response response = given(spec).body(expectedData).when().post("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(201,response.statusCode());

        PostCountryPojo actualData = convertJsonToJava(response.asString(), PostCountryPojo.class);
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(state1.getId(),actualData.getStates().get(0).getId());
        assertEquals(state1.getName(),actualData.getStates().get(0).getName());
        assertEquals(state2.getId(),actualData.getStates().get(1).getId());
        assertEquals(state2.getName(),actualData.getStates().get(1).getName());
        assertEquals(state3.getId(),actualData.getStates().get(2).getId());
        assertEquals(state3.getName(),actualData.getStates().get(2).getName());

    }
}
