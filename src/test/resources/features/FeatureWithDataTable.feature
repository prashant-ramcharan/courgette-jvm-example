@regression
Feature: Feature with DataTable

  Scenario: Ensure that a stack overflow question page can be opened using a data table
    When I use the following data table to navigate to a Stack Overflow question page
      | StackOverflowPage |
      | 1                 |

    Then I verify Stack Overflow question page 1 is opened