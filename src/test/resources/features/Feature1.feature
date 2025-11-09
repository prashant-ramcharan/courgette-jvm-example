@regression
Feature: Feature 1

  Scenario: Ensure that Courgette JVM Github project loads
    When I navigate to the Courgette JVM GitHub project page
    Then I verify the Courgette JVM GitHub project page is opened

  Scenario: Ensure that Courgette JVM release exists (force scenario to fail)
    When I navigate to the Courgette JVM changelog page
    Then I verify the Courgette JVM changelog includes release 0.1

  @excluded
  Scenario: This feature will be excluded
    When I navigate to the Courgette JVM GitHub project page
    Then I verify the Courgette JVM GitHub project page is opened