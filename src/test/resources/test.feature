Feature: An example feature run as part of a JUnit test
  Scenario: Open Google and do some tests
    Given I set the alias mappings
      | Google Logo | #hplogo |
    Given I open the application
    Then I wait "10" seconds for the element found by "Google Logo" to not be present
    Then I verify that the browser title should be "Google"