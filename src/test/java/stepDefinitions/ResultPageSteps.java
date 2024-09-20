package stepDefinitions;

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



public class ResultPageSteps {

    WebDriver driver = HomePageSteps.driver;
    ResultPage resultPage = new ResultPage(driver);


    @Then("^results for \"([^\"]*)\" appear$")
    public void resultsForXAppear(String searchTerm) {
        Assert.assertTrue(resultPage.checkSearchResultsFor(searchTerm));
    }


}
