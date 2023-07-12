Feature: eBay Search
  As a user
  I want to search for cell phones and apply filters
  So that I can find the desired items

  Scenario: Apply filters to search for cell phones
    Given I am on the eBay homepage
    When I navigate to "Cell phones & accessories"
    And I click on "Cell Phones & Smartphones" in the left-hand side navigation
    And I click on "See All" under "Shop by Brand"
    And I add filters for "5.0", "price", and "item location"
    And I apply the filters
    Then I should see the filter tags applied

  Scenario: Access a Product via Search
    Given I am on the eBay homepage
    When I type "Macbook" in the search bar
    And I change the search category to "Computers/Tablets & Networking"
    And I click on Search
    Then the page should load completely
    And the first result name should match "MacBook"
