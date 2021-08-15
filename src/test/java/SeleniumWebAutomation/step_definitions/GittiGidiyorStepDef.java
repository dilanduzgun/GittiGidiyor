package SeleniumWebAutomation.step_definitions;

import SeleniumWebAutomation.pages.*;
import SeleniumWebAutomation.utilities.BrowserUtils;
import SeleniumWebAutomation.utilities.ConfigurationReader;
import SeleniumWebAutomation.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class GittiGidiyorStepDef {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    SearchPage searchPage = new SearchPage();
    MyAccountsPage myAccountsPage = new MyAccountsPage();
    String basketProductPrices;
    String chosenproductprices;
    WebDriverWait wait = new WebDriverWait(Driver.get(), 20);


    @Given("go to the gittigidiyor homepage")
    public void go_to_the_gittigidiyor_homepage() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
        System.out.println("I opened the gittigidiyor.com.tr");
        wait.until(ExpectedConditions.elementToBeClickable(homePage.CloseCookie)).click();
        String actualUrl = Driver.get().getCurrentUrl();
        Assert.assertEquals("Verify this website", url, actualUrl);

    }


    @When("the user opened the login page")
    public void the_user_opened_the_login_page() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.SignInBtn)).click();
        String url = ("https://www.gittigidiyor.com/uye-girisi");
        Driver.get().get(url);
    }


    @Then("login with valid credentials")
    public void login_with_valid_credentials() throws InterruptedException {
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        loginPage.Login(username, password);
        String url = ("https://www.gittigidiyor.com");
        Driver.get().get(url);
        //String expectedTitle = "GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi";
       // Assert.assertEquals("Verify pageSubTitle", expectedTitle, homePage.getPageSubTitle());
       // System.out.println("PageSubTitle = " + homePage.getPageSubTitle());


    }

    @When("in the search area write {} and click search button")
    public void in_the_search_area_write_and_click_search_button(String product) {
        homePage.searchProduct(product);
        searchPage.getResult();
        System.out.println("the result of the product = " + searchPage.getResult());
    }


    @Then("product page opens and go to product page {string}")
    public void product_page_opens_and_go_to_product_page(String expectedPage) {
        searchPage.ProductsPagesBtn(expectedPage);
        BrowserUtils.waitFor(3);
        Driver.get().getCurrentUrl().contains("https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2");
        System.out.println("It is seen that page " + expectedPage + " is opened");


    }

    @Then("the product is choosing")
    public void the_product_is_choosing() {
        searchPage.firstproduct.click();
        System.out.println("the product is choosen");
    }

    @When("product information and amount information are written to txt file")
    public void product_information_and_amount_information_are_written_to_txt_file() throws IOException {

        File file = new File("/src/test/resources/output.txt");
        String productInfoPath = "h1#sp-title";
        String productInfo = BrowserUtils.waitForClickablility(By.cssSelector(productInfoPath), 5).getText();
        String productPrice =Driver.get().findElement(By.id("sp-price-discountPrice")).getText();
        System.out.println(productInfo);
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        writer.write("Ürün bilgisi : " + productInfo);
        writer.newLine();
        writer.write("Ürün Fiyatı : " + productPrice);
        writer.flush();

    }

    @When("Add the  product to basket")
    public void add_the_product_to_basket() {
       // wait.until(ExpectedConditions.elementToBeClickable(searchPage.closePopup)).click();
       // searchPage.closePopup.click();
        wait.until(ExpectedConditions.elementToBeClickable(searchPage.addToBasketBtn)).click();
        //searchPage.addToBasketBtn.click();
        BrowserUtils.waitFor(3);
        System.out.println("The product is added the basket");

    }

    @When("going to Sepetim page")
    public void going_to_sepetim_page() {
        String url = ("https://www.gittigidiyor.com/sepetim");
        Driver.get().get(url);
        System.out.println("My basket page is clicking now");
    }


    @When("this product is the same as the product added to basket")
    public void this_product_is_the_same_as_the_product_added_to_basket() {
        String expectedResults = myAccountsPage.basketproductprice.getText();
        String actualResults =myAccountsPage.chosenproductprice.getText();
        Assert.assertEquals(expectedResults, actualResults);
        System.out.println("expectedResult = " + expectedResults);
        System.out.println("actualResult = " + actualResults);
    }

    @When("product number {int} is made")
    public void product_number_is_made(Integer int1) {
        myAccountsPage.prodcutNumber.click();
        myAccountsPage.prodcutNumber2.click();
        System.out.println("The product numbers are updated 2");
        BrowserUtils.waitFor(3);
    }

    @When("the product added to basket is being removed")
    public void the_product_added_to_basket_is_being_removed() {
        myAccountsPage.deleteBasketProduct();
        BrowserUtils.waitFor(3);
        System.out.println("Products are deleted from My Account Page");
    }

    @Then("basket page is now empty")
    public void basket_page_is_now_empty() {
        myAccountsPage.emptyBasket();
        String expectedResult = "Ürün Toplamı (0 Adet)";
        String actualResult =myAccountsPage.emptyBasket.getText();
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println("expectedResult = " + expectedResult.contains("Ürün Toplamı (0 Adet)"));
        System.out.println("actualResult = " + actualResult);


    }


}