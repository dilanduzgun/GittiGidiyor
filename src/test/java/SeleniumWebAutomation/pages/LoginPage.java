package SeleniumWebAutomation.pages;

import SeleniumWebAutomation.utilities.BrowserUtils;
import SeleniumWebAutomation.utilities.ConfigurationReader;
import SeleniumWebAutomation.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class LoginPage {

    public LoginPage() {

        PageFactory.initElements(Driver.get(), this);

    }

    @FindBy(id = "L-UserNameField")
    public WebElement usernameInput;

    @FindBy(id = "gg-login-enter")
    public WebElement loginButton;

    @FindBy(id = "L-PasswordField")
    public WebElement PasswordInput;


    public void Login(String usernameStr, String passwordStr) {
            usernameInput.sendKeys(usernameStr);
             PasswordInput.sendKeys(passwordStr);
             loginButton.click();

    }

}