package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.Log4jManager;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver, WebDriverWait wait, SoftAssert sa) {
        super(driver, wait, sa);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="LoginEmail")
    public WebElement eMailField;
    @FindBy(id="Password")
    public WebElement passwordField;
    @FindBy(id = "loginLink")
    public WebElement btnLogin;


    public void clickSignIn(){
        click(btnLogin);
    }
    public void writeEMail(String eMail){
        writeText(eMailField, eMail);
    }
    public void writePassword(String pwd){
        writeText(passwordField, pwd);
    }

    public void oneWayLogin(String eMail, String pwd) throws InterruptedException {
        try{
            writeText(eMailField, eMail);
            writeText(passwordField, pwd);
            click(btnLogin);
            softAssertText(driver.getCurrentUrl(),"https://www.lcwaikiki.com/tr-TR/TR");
            Log4jManager.info("Login olundu");
        }catch (Exception e){
            Log4jManager.error("Login Olunamadı.",e);
            throw(e);
        }

    }
}
