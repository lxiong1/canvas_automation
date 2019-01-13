Feature: HomePage

  Background:
    Given I navigate to the canvas website
    And I log in with my credentials

  Scenario: Verify dashboard cards lead to new page
    Then I am able to click on each dashboard card available

  Scenario Outline: Verify global navigation bar has correct menu items
    Then I am able to see navigation bar menu items "<name>"

    Examples:
      | name      |
      | Account   |
      | Dashboard |
      | Courses   |
      | Groups    |
      | Calendar  |
      | Inbox     |
      | Help      |

  Scenario: Verify header
    Then I see the "Dashboard" header name

  Scenario: Verify log out
    When I click on "Account" in the navigation bar
    Then I am able to log out

  Scenario: Verify email support message
    When I click on "Help" in the navigation bar
    And I click on "Email Support" from the help menu
    Then I am able to see the following email support message:
      """
      File a ticket for a personal response from our support team.
      """
