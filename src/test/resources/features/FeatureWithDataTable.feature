@regression
Feature: Feature with DataTable

  Scenario: Ensure that Courgette JVM release exists using a data table to verify
    When I navigate to the Courgette JVM changelog page
    Then I use the following data table to verify the Courgette JVM release exists
      | release |
      | 6.0.0   |