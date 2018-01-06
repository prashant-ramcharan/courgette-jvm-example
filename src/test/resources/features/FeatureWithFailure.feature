@regression_failure
Feature: Feature with Failure

  Scenario: Ensure that stack overflow question page 2 can be opened (force scenario to fail)
    When I navigate to Stack Overflow question page 2
    Then I verify Stack Overflow question page 3 is opened