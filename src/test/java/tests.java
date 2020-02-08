import api.endpoints.UserEndPoint;
import api.objects.ApiUser;
import com.codeborne.selenide.Configuration;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class tests {

    static {
       Configuration.baseUrl = "www.google.com";
    }


    @Test
    public void testOne(){

        ApiUser user = new ApiUser();
        user.setFirstName("Anton");
        user.setLastName("Gritsun");
        user.setEmail("some@o.com");
        user.setPassword("zxczxczxc");
        UserEndPoint userEndPoint = new UserEndPoint();
        Response response = userEndPoint.apiUserCreateViaObj(user);


        userEndPoint.getStatusCodeFromResponse(response);

        open("");


        $(By.xpath("//email")).shouldBe(Condition.visible).sendKeys(user.getEmail());
        $(By.xpath("//password")).shouldBe(Condition.visible).sendKeys(user.getPassword());
        $(By.xpath("//clickLoginBtn"));




    }




}
