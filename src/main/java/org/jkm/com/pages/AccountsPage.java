package org.jkm.com.pages;

import org.jkm.com.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountsPage {

    WebDriver driver;
    ElementUtil elementUtil;

    public AccountsPage(WebDriver driver){
        this.driver=driver;
        elementUtil = new ElementUtil(driver);
    }

    private By headers = By.xpath("//h2");
    private By editAccountInfoLink = By.linkText("Edit your account information");
    private By logoutLink = By.linkText("Logout");
    private By search = By.xpath("//input[@name='search']");
    private By searchButton = By.cssSelector(".fa-search");

    public String getAccountPageTitle() throws InterruptedException {
        Thread.sleep(2000);
        return elementUtil.waitForTitleIsAndCapture("My Account",10);
    }

    public boolean logoutLinkexists(){
        return elementUtil.checkElementIsDisplayed(logoutLink);
    }

    public List<WebElement> getHeadersList(){
        return elementUtil.waitForElementsVisible(headers,10);
    }

    public ResultsPage searchItem(String item){
        elementUtil.waitForElementVisible(search,10);
        elementUtil.doSendKeys(search,item);
        elementUtil.doClick(searchButton);
        System.out.println("print");
        return new ResultsPage(driver);
    }

    public void logout(){
        elementUtil.doClick(logoutLink);
    }
}
