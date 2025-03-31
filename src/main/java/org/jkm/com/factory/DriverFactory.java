package org.jkm.com.factory;

import org.apache.commons.io.FileUtils;
import org.jkm.com.exception.FrameworkExceptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    WebDriver driver;
    OptionsManager optionsManager;
    Properties properties;

    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();

    public WebDriver initDriver(Properties properties){

        String browserName = properties.getProperty("browser");
        optionsManager=new OptionsManager(properties);
        System.out.println(browserName);

        switch (browserName.toLowerCase()){
            case "chrome":
                driverThreadLocal.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;

            case "firefox":
                driverThreadLocal.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
                break;

            case "edge":
                driverThreadLocal.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;

            default:
                System.out.println("Please pass valid browser name");
                break;
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        System.out.println(properties.getProperty("url"));
        getDriver().get(properties.getProperty("url"));
        return getDriver();
    }

    public synchronized static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public Properties initProperties(){
        properties=new Properties();
        FileInputStream fp=null;
        String envName=System.getProperty("env");
        System.out.println("Environment name is : "+envName);
        try {
            if (envName == null) {
                fp = new FileInputStream("./src/test/java/resources/config/config.properties");
            } else {
                switch (envName.toLowerCase()) {
                    case "qa":
                        fp = new FileInputStream("./src/test/java/resources/config/qa_config.properties");
                        break;
                    case "uat":
                        fp = new FileInputStream("./src/test/java/resources/config/UAT_config.properties");
                        break;
                    case "dev":
                        fp = new FileInputStream("./src/test/java/resources/config/dev_config.properties");
                        break;
                    default:
                        System.out.println("Please right environment name");
                        throw new FrameworkExceptions("Invalid env name");
                }
            }
        }
       catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(fp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /*
    Take screenshot
     */

    public static String getScreenshot(){
        File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
        File dest = new File(path);
        try {
            FileUtils.copyFile(src,dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
}


