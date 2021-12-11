Feature: Division by zero
    Honk honk hoonk!

  Scenario: 34/0 => exception => window pops up
    Given left operand typed 34
    Given right operand typed 0
    Given operator set to /
    When result is calculated
    Then division by zero message window pops up