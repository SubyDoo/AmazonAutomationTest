Feature: searchbar feature
  This feature checks the functionality of the the searchbar

  Scenario: Verify a valid search
    Given I get to the homepage
    Then the search bar is visible
    When I click the search bar
    Then there are suggested searches
    When I type "Veeva" in the search bar
    Then suggestions for "Veeva" are shown
    When I remove text from the searchbar
    Then the search bar is visible
    When I click the search button
    Then I am on the homepage
    Then I type "Veeva" in the search bar
    When I click the search button
    Then results for "Veeva" appear
    When I go to the homepage
    Then the search bar is visible
    When I click the search bar
    Then "Veeva" appears in the search history
    When I click "Veeva" in the search history
    Then results for "Veeva" appear