package SeleniumWebAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MyAccountsPage extends BasePage {


    @FindBy(css = "div.gg-d-8.detail-price")
    public WebElement basketproductprice;

    @FindBy(css = "i.gg-icon.gg-icon-bin-medium")
    public WebElement deleteBasketProduct;

    @FindBy(css = "div.total-price")
    public WebElement chosenproductprice;

    @FindBy(css = "select.amount")
    public WebElement prodcutNumber;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[2]/select/option[2]")
    public WebElement prodcutNumber2;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[3]/div/div[1]/div/div[5]/div[1]/div/ul/li[1]/div[1]")
    public WebElement emptyBasket;

    public String basketproductprice() {

        return basketproductprice.getText();
    }

    public String chosenproductprice() {

        return chosenproductprice.getText();
    }


    public void deleteBasketProduct() {
        deleteBasketProduct.click();

    }

    public String emptyBasket() {
        return emptyBasket.getText();

    }

}

