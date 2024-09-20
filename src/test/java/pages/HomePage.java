package pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    WebDriver driver = null;
    WebDriverWait wait = null;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBar;

    @FindBy(id = "nav-flyout-searchAjax")
    private WebElement suggestedSearchList;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(id = "nav-logo-sprites")
    private WebElement homePageButton;

    @FindBy(css = "[class=\"s-suggestion s-recentSearchDistinct s-suggestion-ellipsis-direction\"]")
    private List<WebElement> recentSearchList;


    //constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // checks to see that the right default text is in the search bar
    public boolean checkSearchBar() {
        String searchBarText = searchBar.getAttribute("placeholder");
        Assert.assertEquals("Search Amazon.ca", searchBarText);

        // checks that the color of the search button is correct
        WebElement parent = driver.findElement(By.cssSelector("div[class=\"nav-search-submit nav-sprite\"]"));
        String backgroundColor = parent.getCssValue("background-color");
        Assert.assertEquals("rgba(254, 189, 105, 1)", backgroundColor);

        return true;
    }

    // clicks the search button
    public boolean clickSearchBar() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchBar.click();
        wait.until(ExpectedConditions.visibilityOf(suggestedSearchList));
        return true;
    }

    // checks that search suggestions are present
    public boolean searchSuggestionsArePresent() {
        wait.until(ExpectedConditions.visibilityOf(suggestedSearchList));
        return true;
    }

    // types given string into the search bar
    public boolean typeInSearchBar(String searchText){
        searchBar.sendKeys(searchText);
        return true;
    }

    // checks the list of suggested searches see if it contains a specific term
    public boolean checkSearchSuggestions(String searchTerm) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(suggestedSearchList));

        // waits until suggested list is updated and checks for "sleep" in "veeva sleep"
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElement(suggestedSearchList, "sleep")));

        String suggestionList = suggestedSearchList.getText();
        // testing what is suggested
        // System.out.println(suggestionList);

        // checking the suggested items for some expected results
        boolean stringCheck = suggestionList.contains("sleep") && suggestionList.contains("formula") && suggestionList.contains("stress");

        return stringCheck;
    }

    // removes text types in the search bar
    public boolean removeSearchBarText() {
        //searchBar.clear();
        searchBar.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchBar.sendKeys(Keys.DELETE);
        return true;
    }

    // clicks the search button to query search
    public boolean clickSearchButton() {
        searchButton.click();
        return true;
    }

    // checks if you are on the default home page
    public boolean checkIfOnHomePage() {
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals("https://www.amazon.ca/", currentURL);
        return true;
    }

    // clicks the amazon button to return to the home
    public boolean clickHomePage() {
        wait.until(ExpectedConditions.elementToBeClickable(homePageButton));
        homePageButton.click();
        return true;
    }

    // checks the search bar history for a specific term
    public boolean checkSearchHistoryFor(String searchTerm) {

        boolean passed = false;
        String lowerSearchTerm = searchTerm.toLowerCase();

        // once the term is found, the check is done
        for (WebElement recentSearch: recentSearchList) {
            String recentSearchText = recentSearch.getText();

            if (recentSearchText.equals(lowerSearchTerm)){
                passed = true;
                return passed;
            }
        }

        return passed;
    }

    public boolean clickSearchHistoryTerm(String searchTerm) {

        boolean passed = false;
        String lowerSearchTerm = searchTerm.toLowerCase();

        // once the term is found, the check is done
        for (WebElement recentSearch: recentSearchList) {
            String recentSearchText = recentSearch.getText();

            if (recentSearchText.equals(lowerSearchTerm)){
                wait.until(ExpectedConditions.elementToBeClickable(recentSearch));
                recentSearch.click();
                passed = true;
                return passed;
            }
        }

        return passed;
    }
}
