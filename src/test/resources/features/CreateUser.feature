@smoke
Feature: Add User functionality

  Background:
    Given user is on login page
    When user enters username "Admin"
    And user enters password "admin123"
    And user clicks on login button
    Then user should be logged in successfully

  Scenario: Add a new admin user successfully
    When user navigates to Admin and Add User page
    And user selects User Role as "Admin"
    And user enters Employee Name as "Paul Collings"
    And user selects Status as "Enabled"
    And user enters Username as "paul.admin01"
    And user enters Password as "Test@1234"
    And user confirms Password as "Test@1234"
    And user clicks on Save button
    Then user should see user created successfully message
