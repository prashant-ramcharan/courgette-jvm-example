@regression
Feature: Feature with Examples

  Background:
    Given I navigate to the Courgette JVM changelog page

  Scenario Outline: Ensure that Courgette JVM release <release> exists
    Then I verify the Courgette JVM changelog includes release <release>

    Examples:
      | release |
      | 6.0.0   |
      | 6.1.0   |
      | 6.2.0   |