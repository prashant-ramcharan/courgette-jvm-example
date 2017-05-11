Feature: Feature 1

  @regression
  Scenario: Ensure that stack overflow question page 1 can be opened
    When I navigate to Stack Overflow question page 1
    Then I verify Stack Overflow question page 1 is opened


  @excluded
  Scenario: This feature will be excluded
    When I navigate to Stack Overflow question page 1
    Then I verify Stack Overflow question page 1 is opened