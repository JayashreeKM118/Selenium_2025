package Base;

import org.jkm.com.factory.DriverFactory;
import org.jkm.com.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {

    WebDriver driver;
    protected Properties properties;
    protected DriverFactory driverFactory;
    protected LoginPage loginPage;
    protected AccountsPage accountsPage;
    protected ResultsPage resultsPage;
    protected ProductInfoPage productInfoPage;
    protected RegisterPage registerPage;

    @Parameters({"browser"})
    @BeforeTest
    public void testSetUp(String browserName){
        driverFactory = new DriverFactory();
        properties = driverFactory.initProperties();
        if(browserName!=null)
            properties.setProperty("browser",browserName);
        driver = driverFactory.initDriver(properties);
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
