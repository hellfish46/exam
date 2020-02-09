package api.endpoints;

import api.objects.ApiUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoint extends EndPointBase{

    private final String ENDPOINT = "/register";

    public ApiUser getUserObjectFromResponse(Response response){
        return response.then().extract().body().as(ApiUser.class);
    }

    public Response apiUserCreateViaObj(ApiUser user){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(ENDPOINT);
        return response;
    }

}
