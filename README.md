# Courgette-JVM with Gradle Example

An example project showing how to use Courgette-JVM with Gradle.

## System Requirements

* Google Chrome
* MacOS / Windows / Linux

## Test Execution

* **Parallel Feature Execution using JUnit**
    * Option 1: Run _suites/junit/FeatureSuite.java_ like a normal JUnit test.
   
    * Option 2: Execute gradle task 
    ````gradle
        gradle runFeaturesUsingJUnit
    ````
        
* **Parallel Scenario Execution using JUnit**
    * Option 1: Run _suites/junit/ScenarioSuite.java_ like a normal JUnit test.
    
    * Option 2: Execute gradle task 
    ````gradle
        gradle runScenariosUsingJUnit
    ````

_______________________________________________________

* **Parallel Feature Execution using TestNG**
    * Option 1: Run _suites/testng/FeatureSuite.java_ like a normal TestNG test.

    * Option 2: Execute gradle task
    ````gradle
        gradle runFeaturesUsingTestNG
    ````

* **Parallel Scenario Execution using TestNG**
    * Option 1: Run _suites/testng/ScenarioSuite.java_ like a normal TestNG test.

    * Option 2: Execute gradle task
    ````gradle
        gradle runScenariosUsingTestNG
    ````
