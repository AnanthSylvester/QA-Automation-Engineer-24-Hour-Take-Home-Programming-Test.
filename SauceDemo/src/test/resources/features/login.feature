@smoke
Feature: Login Validation

  Scenario: Invalid login shows error
    Given I attempt login with invalid credentials
    Then I should see an authentication error message
    And I should remain on login page