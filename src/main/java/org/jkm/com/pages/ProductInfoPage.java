package org.jkm.com.pages;

import org.jkm.com.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ProductInfoPage {
    WebDriver driver;
    private ElementUtil elementUtil;
    public LinkedHashMap<String,String> map = new LinkedHashMap<>();

    public ProductInfoPage(WebDriver driver){
        this.driver=driver;
        elementUtil = new ElementUtil(driver);
    }

    private final By productHeader = By.xpath("//h1");
    private final By images = By.xpath("//ul//img");
    private final By productData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
    private By productQuantity = By.id("input-quantity");
    private By addToCartButton = By.id("button-cart");

    public String getProductName(){
        return elementUtil.doGetElementText(productHeader);
    }

    public int getProdImagescount(){
        return elementUtil.waitForElementsVisible(images,10).size();
    }

    public HashMap<String, String> productInfo(){
        map.put("productName",getProductName());
        getProductInformation();
        getProductPriceData();
        return map;
    }

    private void getProductInformation(){
        List<WebElement> list = elementUtil.getElements(productData);

        for(WebElement e: list){
            String txt = e.getText();
            String[] ar = txt.split(":");
            map.put(ar[0].trim(),ar[1].trim());
        }
    }

    private void getProductPriceData(){
        List<WebElement> list = elementUtil.getElements(productPriceData);
        String price = list.get(0).getText();
        String ex_tax = list.get(1).getText();
        String ex_tax_value = ex_tax.split(":")[1].trim();
        map.put("price",price);
        map.put("ex_tax",ex_tax_value);
    }
}
