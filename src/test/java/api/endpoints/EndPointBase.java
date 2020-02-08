package api.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EndPointBase {
    public Integer getStatusCodeFromResponse (Response response){
        return response.getStatusCode();
    }

    static {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
    }

}
