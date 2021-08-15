@testinium
Feature: Testinium Testcase using SeleniumWebDriver and Cucumber Framework
  @smoke @login
  Scenario: Go to website in valid credentials and add selected product to favorites and confirm
    Given go to the gittigidiyor homepage
    And the user opened the login page
    And login with valid credentials
    And in the search area write bilgisayar and click search button
    When product page opens and go to product page "2"
    And the product is choosing
    And product information and amount information are written to txt file
    And Add the  product to basket
    And going to Sepetim page
    And this product is the same as the product added to basket
    And product number 2 is made
    And  the product added to basket is being removed
    Then basket page is now empty