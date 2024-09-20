package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver = null;
    WebDriverWait wait = null;

//    @FindBy(css = "[class='nav-search-field ']")
//    private WebElement searchBar;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBar;

    @FindBy(id = "nav-flyout-searchAjax")
    private WebElement suggestedSearchList;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;



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

    public boolean typeInSearchBar(String searchText){
        searchBar.sendKeys(searchText);
        return true;
    }


    public boolean checkSearchSuggestions(String searchTerm) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(suggestedSearchList));

        // waits until suggested list is updated and checks for "sleep" in "veeva sleep"
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElement(suggestedSearchList, "sleep")));

        String suggestionList = suggestedSearchList.getText();

        // testing what is suggested
        System.out.println(suggestionList);

        // checking the suggested items for some expected results
        boolean stringCheck = suggestionList.contains("sleep") && suggestionList.contains("formula") && suggestionList.contains("stress");

        return stringCheck;
    }


}
