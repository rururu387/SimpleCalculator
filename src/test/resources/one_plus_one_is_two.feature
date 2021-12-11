Feature: Is 1+1=2?
    Easy peasy lemon squeezy

  Scenario: 1+1=2
    Given left operand is 1.0
    Given right operand is 1.0
    Given operator set to +
    When result is calculated
    Then it should match 2.0