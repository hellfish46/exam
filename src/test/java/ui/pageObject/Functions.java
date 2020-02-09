package ui.pageObject;

import api.objects.ApiProduct;
import api.objects.ApiUser;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Functions {

    public ApiProduct getProductObject(SelenideElement productElement){
        ApiProduct apiProduct = new ApiProduct();
       // productElement.$(By.xpath(".//h3/a")).getText();
        apiProduct.setProductName(productElement.$(By.xpath(".//h3/a")).getText());
        apiProduct.setPrice(productElement.$(By.xpath(".//p[@class='price']")).getText());
        return apiProduct;
    }

    public ApiProduct getProductObjectFromTable(SelenideElement tbody){
        ApiProduct apiProduct = new ApiProduct();
        apiProduct.setProductName(tbody.$(By.xpath(".//td[2]/a")).getText());
        apiProduct.setPrice(tbody.$(By.xpath(".//td[4]")).getText());
        return apiProduct;
    }

    public void fillAddressForm(ApiUser user){
        $(By.xpath("//input[@id='form-number']")).setValue(user.getHouseNumber());
        $(By.xpath("//input[@id='form-street']")).setValue(user.getStreetName());
        $(By.xpath("//input[@id='form-city']")).setValue(user.getCity());
        $(By.xpath("//input[@id='form-post-code']")).setValue(user.getPostCode());
        $(By.xpath("//input[@id='form-country']")).setValue(user.getCountry());
        $(By.xpath("//button[@onclick='return address()']")).shouldBe(Condition.visible).click();
    }

    public void fillCardInfo(ApiUser user){
        $(By.xpath("//input[@id='form-card-number']")).setValue(user.getCardNumber());
        $(By.xpath("//input[@id='form-expires']")).setValue(user.getExpires());
        $(By.xpath("//input[@id='form-ccv']")).setValue(user.getCvv());
        $(By.xpath("//button[@onclick='return card()']")).shouldBe(Condition.visible).click();
    }

}
