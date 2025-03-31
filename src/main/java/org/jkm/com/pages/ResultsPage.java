package org.jkm.com.pages;

import org.jkm.com.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage {
    WebDriver driver;
    private ElementUtil elementUtil;

    public ResultsPage(WebDriver driver){
        this.driver=driver;
        elementUtil = new ElementUtil(driver);
    }

    private By searchCriteriaLabel = By.xpath("//label[text()='Search Criteria']");
    private By productList = By.cssSelector("div.product-layout");

    public String getSearchPagetitle(){
        return elementUtil.waitForTitleIsAndCapture("Search - macbook",10);
    }

    public List<WebElement> getProductsList(){
        return elementUtil.waitForElementsVisible(productList,10);
    }

    public ProductInfoPage selectProduct(String productName){
        By pname = By.linkText(productName);
        elementUtil.clickElementWhenReady(pname,10);
        return new ProductInfoPage(driver);
    }
}

