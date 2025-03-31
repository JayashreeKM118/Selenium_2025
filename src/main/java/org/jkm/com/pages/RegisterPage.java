package org.jkm.com.pages;

import org.jkm.com.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    WebDriver driver;
    protected ElementUtil elementUtil;

    public RegisterPage(WebDriver driver){
        this.driver=driver;
        elementUtil=new ElementUtil(driver);
    }

    private By pageHeader = By.xpath("//h1");
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPwd = By.id("input-confirm");
    private By checkbox = By.xpath("//input[@name='agree']");
    private By submitButton = By.xpath("//input[@value='Continue']");
    private By successmsg = By.xpath("//div/h1");
    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");
    public String registerUser(String fname, String lname, String email, String telephone, String pwd){

        elementUtil.waitForElementVisible(this.pageHeader,10);
        elementUtil.doSendKeys(firstName,fname);
        elementUtil.doSendKeys(lastName,lname);
        elementUtil.doSendKeys(this.email,email);
        elementUtil.doSendKeys(this.telephone,telephone);
        elementUtil.doSendKeys(password,pwd);
        elementUtil.doSendKeys(confirmPwd,pwd);
        elementUtil.doClick(checkbox);
        elementUtil.doClick(submitButton);
        String msg = elementUtil.waitForElementVisible(successmsg,10).getText();
        elementUtil.doClick(logoutLink);
        elementUtil.doClick(registerLink);
        return msg;
    }
}
