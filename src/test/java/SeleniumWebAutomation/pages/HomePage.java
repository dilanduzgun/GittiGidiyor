package SeleniumWebAutomation.pages;

import SeleniumWebAutomation.utilities.BrowserUtils;
import SeleniumWebAutomation.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {


    public HomePage() {

        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "/html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div")
    public WebElement SignInBtn;


    @FindBy(xpath = "//span[text()='Tamam']")
    public WebElement privPoliciyBtn;

    @FindBy(xpath = "/html/body/div[1]/header/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input")
    public WebElement searchInput;

    @FindBy(xpath= "/html/body/div[1]/header/div[3]/div/div/div/div[2]/form/div/div[2]/button/span")
    public WebElement searchBtn;

    @FindBy(css = "a.tyj39b-5")
    public WebElement CloseCookie;



    public void searchProduct(String product) {
        BrowserUtils.waitFor(2);
        searchInput.sendKeys(product);
        searchBtn.click();
    }


}