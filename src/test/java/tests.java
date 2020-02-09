import api.endpoints.UserEndPoint;
import api.objects.ApiProduct;
import api.objects.ApiUser;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.codeborne.selenide.Condition;
import ui.pageObject.Functions;

import static com.codeborne.selenide.Selenide.*;

import static org.assertj.core.api.Assertions.assertThat;

public class tests {

    static {
       //Configuration.baseUrl = "www.google.com";
    }


    @Test
    public void testOne(){

        String productPath = "//div[h3/a[text()='Nerd leg']]";

//        private String houseNumber;
//        private String streetName;
//        private String city;
//        private String postCode;
//        private String country;
//
//        private String cardNumber;
//        private String expires;
//        private String cvv;

        ApiUser user = new ApiUser();
        user.setUsername("dude");
        user.setEmail("some3@o.com");
        user.setPassword("zxczxczxc");
        user.setHouseNumber("12A");
        user.setStreetName("Mayakovskiogo str.");
        user.setCity("Dnipro");
        user.setPostCode("48000");
        user.setCountry("Ukraine");

        user.setCardNumber("123456");
        user.setExpires("1224");
        user.setCvv("789");
        UserEndPoint userEndPoint = new UserEndPoint();
        Response response = userEndPoint.apiUserCreateViaObj(user);


        userEndPoint.getStatusCodeFromResponse(response);
        assertThat(userEndPoint.getStatusCodeFromResponse(response)).isEqualTo(200);
        System.out.println(userEndPoint.getStatusCodeFromResponse(response));

         open("http://167.172.110.35/");
         $(By.xpath("//li[@id='login']")).shouldBe(Condition.visible).click();
         $(By.xpath("//h4[text()='Customer login']")).shouldBe(Condition.visible);

         //login
        $(By.xpath("//input[@id='username-modal']")).shouldBe(Condition.visible).setValue(user.getUsername());
        $(By.xpath("//input[@id='password-modal']")).shouldBe(Condition.visible).setValue(user.getPassword());
        $(By.xpath("//button[@onclick='return login()']")).shouldBe(Condition.visible).click();

       //find needed product
        $(By.xpath("//li[@id='tabCatalogue']")).shouldBe(Condition.visible).click();
        $(By.xpath("//a[@onclick='setNewPageSize(9)']")).shouldBe(Condition.visible).click();

        SelenideElement product = $(By.xpath(productPath)).shouldBe(Condition.visible);

        //product in memory
        Functions functions = new Functions();
        ApiProduct catalogueProduct = functions.getProductObject(product);

        //add to card
        $(By.xpath(productPath + "//a[@class = 'btn btn-primary']")).shouldBe(Condition.visible).click();

        //go to the card
        $(By.xpath("//span[@id='numItemsInCart']")).shouldBe(Condition.visible).click();

        //checking added product
        SelenideElement tbody = $(By.xpath("//tbody[@id='cart-list']")).shouldBe(Condition.visible);

        ApiProduct checkOutProduct = functions.getProductObjectFromTable(tbody);

        assertThat(catalogueProduct).isEqualTo(checkOutProduct);


        //buy the product
        $(By.xpath("//h4[text()='Shipping Address']/following-sibling::p/a")).shouldBe(Condition.visible).click();
        $(By.xpath("//h4[text()='Address']")).shouldBe(Condition.visible);
        functions.fillAddressForm(user);



        $(By.xpath("//h4[text()='Payment']/following-sibling::p/a")).click();
        //$(By.xpath("//h4[text()='Credit Card']")).shouldBe(Condition.visible);
        functions.fillCardInfo(user);

        $(By.xpath("//button[@id='orderButton']")).shouldBe(Condition.visible).click();

        $(By.xpath("//h1[text()='My orders']")).shouldBe(Condition.visible);

        String idOfProduct = $(By.xpath("//tbody//th[1]")).getText();
        String dateOfProduct = $(By.xpath("//tbody//td[1]")).getText();
        String finalPriceOfProduct = $(By.xpath("//tbody//td[2]")).getText();

        assertThat(idOfProduct).isNotBlank();
        assertThat(dateOfProduct).isNotBlank();
        assertThat(finalPriceOfProduct).isNotBlank();








    }




}
