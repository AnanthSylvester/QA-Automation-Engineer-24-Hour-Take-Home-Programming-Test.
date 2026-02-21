@smoke
Feature: Purchase Flow

  Scenario: Successful purchase of a single product
    Given I login as a standard user
    When I add "Sauce Labs Backpack" to the cart
    And I checkout with valid customer details
    Then I should see the order confirmation page