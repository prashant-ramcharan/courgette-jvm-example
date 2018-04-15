@regression
Feature: Feature with Examples

  Background:
    Given I navigate to Stack Overflow

  Scenario Outline: Ensure that stack overflow question pages can be opened
    When I navigate to Stack Overflow question page <page>
    Then I verify Stack Overflow question page <page> is opened

    Examples:
      | page |
      | 3    |
      | 4    |
      | 5    |