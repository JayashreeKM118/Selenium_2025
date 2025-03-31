package Tests;

import Base.BaseTest;
import org.jkm.com.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @BeforeClass
    public void setUp(){
        registerPage = loginPage.navigateToRegister();
    }

    public String getRandomMail(){
       return "TestAutomation-"+System.currentTimeMillis()+"@gmail.com";
    }

//    @DataProvider
//    public Object[][] createRegistrationdata() {
//        return new Object[][]{
//                {"abc","def","aloo@gmail.com","7897890011","test@123"},
//                {"pqr","xyz","bonnda@gmail.com","7897890011","test@123"}
//        };
//    }

    @DataProvider(name="registerExceldata")
    public Object[][] getExcelTestData(){
        Object obj[][] = ExcelUtil.getTestData("testdata");
        return obj;
    }

    @Test(dataProvider = "registerExceldata")
    public void registration(String firstName, String lastName, String emailId, String telephone, String pwd){
        String msg = registerPage.registerUser(firstName,lastName,emailId,telephone,pwd);
        Assert.assertTrue(msg.contains("Created"));
    }
}
