package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.Log4jManager;

import java.util.Set;

public class ProductsPage extends BasePage{
    public ProductsPage(WebDriver driver, WebDriverWait wait, SoftAssert sa) {
        super(driver, wait, sa);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "pd_add_to_cart")
    public WebElement btnAddToCart;
    @FindBy(xpath = "//*[contains(@data-tracking-label,'BedenSecenekleri')and(contains(@data-placement,'top'))and not(contains(@data-stock,'0'))]")
    public WebElement btnBeden;
    @FindBy(xpath = "//*[@id=\"rightInfoBar\"]/div[1]/div/div[2]/div/div/div[2]/div[2]")
    public WebElement productDiscount;
    @FindBy(xpath = "//div[contains(text(),'Sepetim')]")
    public WebElement btnBasket;

    public void clickAddToCart() throws InterruptedException {
        try{
            click(btnAddToCart);
            try {
                click(btnBeden);
                Log4jManager.info("Beden Seçimi Gerçekleşti");
            }catch (Exception e){
                Log4jManager.error("Beden Seçimi Gerçekleşmedi",null);
            }
            try {
                driver.findElement(By.xpath("//*[contains(@data-tracking-label,'BoySecenekleri')and(contains(@data-placement,'top'))and not(contains(@class,'disable'))]")).click();
                Log4jManager.info("Boy Seçimi Gerçekleşti");
            }catch (Exception e){
                Log4jManager.warn("Boy Seçimi Gerçekleşmedi");
            }
            click(btnAddToCart);
            Log4jManager.info("Ürün Sepete Eklendi");
        }
        catch (Exception e){
            Log4jManager.error("Ürün Sepete Eklenemedi",null);
            throw(e);
        }


    }

    public String getPrice() {
        return readText(productDiscount);

    }
    public void clickToGoBasket() {
        try {
            click(btnBasket);
            Log4jManager.info("Sepete Gidildi");
        }
        catch (Exception e){
            Log4jManager.error("Sepete Gidilemedi",null);
            throw(e);
        }
    }
}
