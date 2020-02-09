package api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ApiProduct {
    private String productName;
    private String price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrise() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "ApiProduct{" +
                "productName='" + productName + '\'' +
                ", prise='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiProduct that = (ApiProduct) o;
        return Objects.equals(productName, that.productName) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, price);
    }
}
