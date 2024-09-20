Feature: searchbar feature
  This feature checks the functionality of the the searchbar

  Scenario: Verify a valid search
    Given I am on the homepage
    Then the searchbar is visible
    When I click the searchbar
    Then there are suggested searches
    When I type "Veeva" in the searchbar
    Then suggestions for "Veeva" are shown