Feature: Login functionality

  Background:
    Given user is on login page

  #Rule: Valid users should be able to login successfully

    Scenario Outline: Login with valid user credentials
      When user enters username "<username>"
      And user enters password "<password>"
      And user clicks on login button
      Then user should be logged in successfully

      Examples:
        | username | password |
        | Admin    | admin123 |


  #Rule: Invalid users should not be allowed to login

    Scenario Outline: Login with invalid user credentials
      When user enters username "<username>"
      And user enters password "<password>"
      And user clicks on login button
      Then user should not login successfully

      Examples:
        | username | password |
        | User1    | pass123  |
        | User2    | test@123 |
