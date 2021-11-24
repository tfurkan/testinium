package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.Log4jManager;

import java.util.Random;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver, WebDriverWait wait, SoftAssert sa) {
        super(driver, wait,sa);
        PageFactory.initElements(driver, this);
    }

    String baseURL = "https://www.lcwaikiki.com/tr-TR/TR";

    @FindBy(xpath = "//span[contains(text(),'Giriş Yap')]")
    public WebElement btnGirisYap;
    @FindBy(xpath = "//a[contains(text(),'Giriş Yap')]")
    public WebElement btnSubGirisYap;
    @FindBy(id = "search_input")
    public WebElement searchBar;
    @FindBy(xpath = "//button[contains(text(),'Ara')]")
    public WebElement btnSearch;
    @FindBy(xpath = "//b[contains(text(),'\"')]")
    public WebElement verifySearchedProduct;
    @FindBy(xpath = "//p[contains(text(),'Daha Fazla Ürün Gör')]")
    public WebElement btnMoreProducts;
    static int rand = new Random().nextInt(95)+1;
    String toStr = Integer.toString(rand);
    By randomProduct = By.xpath("//body/div[5]/div[3]/div[2]/div[7]/div[1]/div[1]/div["+toStr+"]/a[1]/div[1]/img[1]");

    public void goToLcWaikiki(){
        try{
            driver.get(baseURL);
            softAssertText(driver.getCurrentUrl(), baseURL);
            Log4jManager.info("Ana Sayfa Açıldı");
        }catch (Exception e){
            Log4jManager.error("Ana Sayfa Açılmadı", null);
            throw(e);
        }

    }
    public void clickSignIn() throws InterruptedException {
        try{
            Log4jManager.info("Giriş Yap butonuna tıklandı.");
            Actions actions = new Actions(driver);
            actions.moveToElement(btnGirisYap);
            actions.perform();
            click(btnSubGirisYap);
            softAssertText(driver.getCurrentUrl(), "https://www.lcwaikiki.com/tr-TR/TR/giris");
        }catch (Exception e){
            Log4jManager.error("Giriş Yap butonuna tıklanamadı", e);
            throw(e);
        }
    }
    public void writeAndSearch(String str, String expected){
        try {
            writeTextWithClear(searchBar, str);
            click(btnSearch);
            softAssertText(readText(verifySearchedProduct), expected);
            Log4jManager.info("Kelime Girildi ve Ara Butonuna Basıldı");
        }catch (Exception e){
            Log4jManager.error("Kelime Girilemedi||Ara Butonuna Basılamadı",e);
            throw(e);
        }

    }
    public void scrollEndOfPage(){
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Log4jManager.info("Sayfanın Sonuna Scroll Yapıldı");
        }catch (Exception e){
            Log4jManager.error("Sayfa Sonuna Scroll Yapılamadı", null);
        }
    }
    public void clickMoreProducts(){
        try {
            click(btnMoreProducts);
            Log4jManager.info("Daha Fazla Ürün butonuna tıklandı");
        }catch (Exception e){
            Log4jManager.error("Daha Fazla Ürün butonuna tıklanamadı",null);
            throw(e);
        }
    }
    public void clickRandomProduct(){
        try {
            driver.findElement(randomProduct).click();
            Log4jManager.info("Rastgele Bir Ürün Seçildi");
        }catch (Exception e){
            Log4jManager.error("Ürün Seçilemedi",null);
            throw(e);
        }
    }

}
