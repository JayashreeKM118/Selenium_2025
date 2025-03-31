package org.jkm.com.pages;

import org.jkm.com.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    public  LoginPage(WebDriver driver){
        this.driver= driver;
        elementUtil = new ElementUtil(driver);
    }

    private By userName = By.id("input-email");
    private By passWord = By.id("input-password");
    private By loginButton =By.xpath("//input[@type=\"submit\"]");
    private By forgotPwd = By.linkText("Forgotten Password");
    private By footerLinks = By.xpath("//footer//ul/li/a");
    private By registerLink =By.linkText("Register");

    public String loginPageTitle(){
        return elementUtil.waitForTitleIsAndCapture("Account Login",10);
    }

    public boolean isForgotPwdLinkExist(){
        return elementUtil.checkElementIsDisplayed(forgotPwd);
    }

    public void getFooterList(){
        List<WebElement> list = elementUtil.waitForElementsVisible(footerLinks,10);
        list.forEach(e-> System.out.println(e.getText()));
    }

    public RegisterPage navigateToRegister(){
        elementUtil.doClick(registerLink);
        return new RegisterPage(driver);
    }

    public AccountsPage login(String uname, String pwd){
        elementUtil.waitForElementVisible(userName,10).sendKeys(uname);
        elementUtil.waitForElementVisible(passWord,10).sendKeys(pwd);
        elementUtil.doClick(loginButton);
        return new AccountsPage(driver);
    }

}
