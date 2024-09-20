package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import testRunners.RunTest;

import java.util.concurrent.TimeUnit;


public class HomePageSteps {

    public static WebDriver driver;

    HomePage homePage;


    // runs before test is executed
    @Before(order = 0)
    public void setUp(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1440x2560");
        options.addArguments("start-maximized");

        // location of the chromedriver currently set for Chrome Version 129.0.6668.59 (Official Build) (64-bit)
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(options);

        homePage = new HomePage(driver);


    }




    @Given("I am on the homepage")
    public void i_am_on_the_homepage() throws InterruptedException {

        driver.get("https://www.amazon.ca/");
        //driver.get("https://www.google.ca/");
        driver.manage().window().maximize();

    }

    @Then("the searchbar is visible")
    public void the_searchbar_is_visible() {
        Assert.assertTrue(homePage.checkSearchBar());
    }

    @When("I click the searchbar")
    public void i_click_the_searchbar() throws InterruptedException {
        Assert.assertTrue(homePage.clickSearchBar());
    }

    @Then("there are suggested searches")
    public void there_are_suggested_searches() {
        Assert.assertTrue(homePage.searchSuggestionsArePresent());
    }



    @When("^I type \"([^\"]*)\" in the searchbar$")
    public void i_type_in_searchbar(String givenSearchText){
        Assert.assertTrue(homePage.typeInSearchBar(givenSearchText));
    }


    @Then("^suggestions for \"([^\"]*)\" are shown$")
    public void suggestionsForXAreShown(String searchTerm) throws InterruptedException {
        Assert.assertTrue(homePage.checkSearchSuggestions(searchTerm));
    }
}