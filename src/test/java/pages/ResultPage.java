package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ResultPage {

    WebDriver driver = null;
    WebDriverWait wait = null;

    @FindBy(css = "[data-component-type=\"s-search-results\"]")
    private WebElement searchResultTable;

    @FindBy(css = "[class=\"s-pagination-item s-pagination-disabled\"]")
    private WebElement lastSearchPage;

    @FindBy(css = "span[class=\"s-pagination-strip\"]")
    private WebElement paginationStrip;

    //constructor
    public ResultPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // searches the listings to see if they contain the search term
    public boolean checkSearchResultsFor(String searchTerm) {

        // gets the list of listings excluding the ad
        wait.until(ExpectedConditions.visibilityOf(searchResultTable));
        List<WebElement> searchResultListExcludingAds = searchResultTable.findElements(By.cssSelector("div[class=\"sg-col-4-of-24 sg-col-4-of-12 s-result-item s-asin sg-col-4-of-16 sg-col s-widget-spacing-small sg-col-4-of-20\"]"));

        // goes through every listing and checks to see if it contains the search term
        for (WebElement searchResult : searchResultListExcludingAds) {
            String listingText = searchResult.getText();

            // fail if any listing does not contain the search term
            if (!listingText.toLowerCase().contains(searchTerm)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkSearchResultPageCount(String pageCount) {
        String actualLastPageCount = lastSearchPage.getText();
        Assert.assertEquals(pageCount, actualLastPageCount);
        return true;
    }

    public boolean clickOnResultPageX(String pageNumber) {

        boolean passed = false;
        List<WebElement> pageButtons = paginationStrip.findElements(By.cssSelector("[class*=\"s-pagination-item s-pagination\"]"));

        for (WebElement pageButton : pageButtons){
            if (pageButton.getText().equals(pageNumber)){
                pageButton.click();
                passed = true;
                return passed;
            }
        }
        return passed;
    }

    public boolean checkAtPageX(String pageNumber) {

        String currentURL = driver.getCurrentUrl();
        assert currentURL != null;

        boolean pass = currentURL.contains("page=" + pageNumber);
        return pass;
    }
}