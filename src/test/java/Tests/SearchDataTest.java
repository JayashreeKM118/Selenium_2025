package Tests;

import Base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchDataTest extends BaseTest {


    @BeforeClass
    public void doLogin(){
        accountsPage=loginPage.login(properties.getProperty("username"),properties.getProperty("password"));;
    }

    @Test
    public void searchProductTest(String item){
        resultsPage = accountsPage.searchItem(item);
        Assert.assertEquals(resultsPage.getProductsList().size(),3);
    }

    @Test
    public void SearchPageTitle(){
        resultsPage = accountsPage.searchItem("macbook");
        Assert.assertEquals(resultsPage.getSearchPagetitle(),"Search - macbook");
    }

    @Test
    public void selectProductTest(){
        resultsPage = accountsPage.searchItem("macbook");
        productInfoPage = resultsPage.selectProduct("MacBook Air");
        Assert.assertEquals(productInfoPage.getProductName(),"MacBook Air");
    }

    @Test
    public void productImagesTest(){
        resultsPage = accountsPage.searchItem("macbook");
        productInfoPage = resultsPage.selectProduct("MacBook Air");
        Assert.assertEquals(productInfoPage.getProdImagescount(),4);
    }

}
