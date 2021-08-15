package SeleniumWebAutomation.pages;

import SeleniumWebAutomation.utilities.BrowserUtils;
import SeleniumWebAutomation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.get(), this);

    }

    @FindBy(css = "a[class*='home']")
    public WebElement pageSubTitle;

    @FindBy(css = "a.menuTitle")
    public WebElement myAccountBtn;


    public void hoverUp() {
        Actions actions = new Actions(Driver.get());
        BrowserUtils.waitFor(2);
        actions.moveToElement(myAccountBtn).perform();

    }



    public String getPageSubTitle() {

        return pageSubTitle.getAttribute("title");
    }
}