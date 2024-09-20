package stepDefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ResultPage;

public class ResultPageSteps {

    WebDriver driver = HomePageSteps.driver;
    ResultPage resultPage = new ResultPage(driver);

    @Then("^results for \"([^\"]*)\" appear$")
    public void resultsForXAppear(String searchTerm) {
        Assert.assertTrue(resultPage.checkSearchResultsFor(searchTerm));
    }

    @Then("^there are \"([^\"]*)\" page results$")
    public void thereArePageResults(String pageCount) {
        Assert.assertTrue(resultPage.checkSearchResultPageCount(pageCount));
    }

    @Then("^I click page \"([^\"]*)\" results$")
    public void iClickPageResults(String pageNumber) {
        Assert.assertTrue(resultPage.clickOnResultPageX(pageNumber));
    }

    @Then("^I am on page \"([^\"]*)\"$")
    public void iAmOnPage(String pageNumber) {
        Assert.assertTrue(resultPage.checkAtPageX(pageNumber));
    }
}