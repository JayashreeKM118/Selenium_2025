package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @Test
    public void loginPageTitleTest(){
       String title= loginPage.loginPageTitle();
        Assert.assertEquals(title,"Account Login");
    }

    @Test
    public  void checkForgotPwdTest(){
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @Test
    public  void footerLinksTest(){
        loginPage.getFooterList();
    }

    @Test
    public  void loginTest(){
        loginPage.login(properties.getProperty("username"),properties.getProperty("password"));
    }


}
