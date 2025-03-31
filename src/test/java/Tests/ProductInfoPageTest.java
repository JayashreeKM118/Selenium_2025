package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void setUp(){
        accountsPage=loginPage.login(properties.getProperty("username"),properties.getProperty("password"));
    }

    @Test
    public void getProductInfoTest(){
        resultsPage = accountsPage.searchItem("MacBook");
        productInfoPage = resultsPage.selectProduct("MacBook Air");
        System.out.println(productInfoPage.productInfo());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productInfoPage.productInfo().get("productName"),"MacBook Air");
        softAssert.assertEquals(productInfoPage.productInfo().get("Brand"),"Apple");
        softAssert.assertEquals(productInfoPage.productInfo().get("Product Code"),"Product 17");
        softAssert.assertEquals(productInfoPage.productInfo().get("Reward Points"),"700---");
        softAssert.assertEquals(productInfoPage.productInfo().get("Availability"),"Out Of Stock");
        softAssert.assertEquals(productInfoPage.productInfo().get("price"),"$1,202.00");
        softAssert.assertEquals(productInfoPage.productInfo().get("ex_tax"),"$1,000.00");
        softAssert.assertAll();

    }
}
