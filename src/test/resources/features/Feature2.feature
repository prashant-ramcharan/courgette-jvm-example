@regression
Feature: Feature 2

  Scenario: Ensure that Courgette JVM release 1.0.0 exists
    When I navigate to the Courgette JVM changelog page
    Then I verify the Courgette JVM changelog includes release 1.0.0