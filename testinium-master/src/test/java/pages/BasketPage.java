package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.Log4jManager;

public class BasketPage extends BasePage{
    public BasketPage(WebDriver driver, WebDriverWait wait, SoftAssert sa) {
        super(driver, wait, sa);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//span[contains(@class,'rd-cart-item-price mb-15')]")
    public WebElement basketDiscount;
    @FindBy(xpath = "//*[contains(@class,'oq-up plus')]")
    public WebElement btnPlus;
    @FindBy(id = "spanCart")
    public WebElement lblSpanCart;
    @FindBy(xpath = "//*[contains(@class,'cart-square-link')and contains(@title,'Sil')]")
    public WebElement btnDeleteProduct;
    @FindBy(xpath = "//*[contains(@class,'inverted-modal-button sc-delete ins-init-condition-tracking')and contains(text(),'Sil')]")
    public WebElement btnPopupDelete;
    @FindBy(xpath = "//p[contains(text(),'Sepetinizde ürün bulunmamaktadır.')]")
    public WebElement txtEmptyMessage;


    public void assertDicounts(String str){
        assertText(basketDiscount, str);
        Log4jManager.info("Sepetteki Fiyatla Ürün Fiyatı Doğrulandı");
    }
    public void assertBasketCount(){
        try{
            String str = readText(lblSpanCart);
            click(btnPlus);
            int i = Integer.parseInt(str) +1;
            assertText(lblSpanCart, Integer.toString(i));
            Log4jManager.info("Ürün Sayısı Arttırıldı ve Arttırıldığı Doğrulandı");
        }
        catch (Exception e){
            Log4jManager.error("Ürün Sayısı Artırılamadı ya da Doğrulanamadı",e);
            throw(e);
        }

    }
    public void clickDeleteProduct(){
        try {
            click(btnDeleteProduct);
            click(btnPopupDelete);
            Log4jManager.info("Ürün Sepetten Çıkarıldı");
        }catch (Exception e){
            Log4jManager.error("Ürün Sepetten Çıkarılamadı",e);
            throw(e);
        }

    }
    public void assertEmptyBasket(String str){
        assertTrue(txtEmptyMessage);
        assertText(txtEmptyMessage, str);
        Log4jManager.info("Sepette Ürün olmadığı Doğrulandı.");
    }

}
