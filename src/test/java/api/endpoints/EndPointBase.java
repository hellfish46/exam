package api.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EndPointBase {
    public Integer getStatusCodeFromResponse (Response response){
        return response.getStatusCode();
    }

    static {
        RestAssured.baseURI = "http://167.172.110.35";
        RestAssured.port = 80;
        //RestAssured.basePath = "/register";
    }

}
