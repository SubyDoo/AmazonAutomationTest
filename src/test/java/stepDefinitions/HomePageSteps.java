package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.ResultPage;
import java.time.Duration;


public class HomePageSteps {

    public static WebDriver driver;
    HomePage homePage;
    ResultPage resultPage;

    // runs before test is executed, sets up driver
    @Before(order = 0)
    public void setUp(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1440x2560");
        options.addArguments("start-maximized");

        // location of the chromedriver currently set for Chrome Version 129.0.6668.59 (Official Build) (64-bit)
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(options);

        // sets implicit wait to be 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        resultPage= new ResultPage(driver);
    }

    // exiting test and closing driver
    @After
    public void closeDown() {
        driver.quit();
        driver = null;
    }

    @Given("I get to the homepage")
    public void iGetToTheHomepage() {

        driver.get("https://www.amazon.ca/");
        //driver.get("https://www.google.ca/");
        driver.manage().window().maximize();
    }

    @Then("the search bar is visible")
    public void theSearchbarIsVisible() {
        Assert.assertTrue(homePage.checkSearchBar());
    }

    @When("I click the search bar")
    public void iClickTheSearchbar() throws InterruptedException {
        Assert.assertTrue(homePage.clickSearchBar());
    }

    @Then("there are suggested searches")
    public void thereAreSuggestedSearches() {
        Assert.assertTrue(homePage.searchSuggestionsArePresent());
    }

    @When("^I type \"([^\"]*)\" in the search bar$")
    public void iTypeInTheSearchbar(String givenSearchText){
        Assert.assertTrue(homePage.typeInSearchBar(givenSearchText));
    }

    @Then("^suggestions for \"([^\"]*)\" is shown$")
    public void suggestionsForXAreShown(String searchTerm) {
        Assert.assertTrue(homePage.checkSearchSuggestions(searchTerm));
    }

    @When("I remove text from the searchbar")
    public void iRemoveTextFromTheSearchbar() {
        Assert.assertTrue(homePage.removeSearchBarText());
    }

    @When("I click the search button")
    public void iClickTheSearchButton() {
        Assert.assertTrue(homePage.clickSearchButton());
    }

    @Then("I am on the homepage")
    public void iAmOnTheHomepage() {
        Assert.assertTrue(homePage.checkIfOnHomePage());
    }

    @When("I go to the homepage")
    public void iGoToTheHomepage() {
        Assert.assertTrue(homePage.clickHomePage());
    }

    @Then("^\"([^\"]*)\" appears in the search history")
    public void appearsInTheSearchHistory(String searchTerm) {
        Assert.assertTrue(homePage.checkSearchHistoryFor(searchTerm));
    }

    @When("^I click \"([^\"]*)\" in the search history$")
    public void iClickInTheSearchHistory(String searchTerm) {
        Assert.assertTrue(homePage.clickSearchHistoryTerm(searchTerm));
    }
}