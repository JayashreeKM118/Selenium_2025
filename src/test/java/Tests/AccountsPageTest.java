package Tests;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void doLogin(){
        accountsPage=loginPage.login(properties.getProperty("username"),properties.getProperty("password"));;
    }

    @Test
    public void getAccountPagetitle() throws InterruptedException {
        Assert.assertEquals(accountsPage.getAccountPageTitle(),"My Account");
    }

    @Test
    public void getHeadersList(){
        List<WebElement> list = accountsPage.getHeadersList();
        Assert.assertEquals(list.size(),4);
        //Assert.assertTrue(list.contains("My Account"));
        list.forEach(e-> System.out.println(e.getText()));
    }


}
