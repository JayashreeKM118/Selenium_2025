package org.jkm.com.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private Properties properties;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;

    public OptionsManager(Properties properties){
        this.properties=properties;
    }

    public ChromeOptions getChromeOptions(){
        co=new ChromeOptions();
        if(Boolean.parseBoolean(properties.getProperty("headless")))
            co.addArguments("--headless");
        if(Boolean.parseBoolean(properties.getProperty("incognito")))
            co.addArguments("--incognito");
        return co;
    }

    public FirefoxOptions getFireFoxOptions(){
        fo=new FirefoxOptions();
        if(Boolean.parseBoolean(properties.getProperty("headless")))
            fo.addArguments("--headless");
        if(Boolean.parseBoolean(properties.getProperty("incognito")))
            fo.addArguments("--incognito");
        return fo;
    }

    public EdgeOptions getEdgeOptions(){
        eo=new EdgeOptions();
        if(Boolean.parseBoolean(properties.getProperty("headless")))
            eo.addArguments("--headless");
        if(Boolean.parseBoolean(properties.getProperty("incognito")))
            eo.addArguments("--incognito");
        return eo;
    }
}
