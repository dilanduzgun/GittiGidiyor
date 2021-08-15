package SeleniumWebAutomation.pages;

import SeleniumWebAutomation.utilities.BrowserUtils;
import SeleniumWebAutomation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage() {
        PageFactory.initElements(Driver.get(), this);

    }

    @FindBy(css = "button#add-to-basket")
    public WebElement addToBasketBtn;

    @FindBy(css = "h3.productName")
    public List<WebElement> products;

    @FindBy(id = "search-result-info")
    public WebElement resultText;

    @FindBy(css = "div#item-info-block-716452101")
    public WebElement firstproduct;

    @FindBy(xpath = "/html/body/div[7]/div/img[1]")
    public WebElement closePopup;

    public void ProductsPagesBtn(String number) {
        String productsPages = "//a[text()='" + number + "']";
        BrowserUtils.waitForClickablility(By.xpath(productsPages), 5).click();

    }



    public String getResult(){
        return resultText.getText();
    }


}