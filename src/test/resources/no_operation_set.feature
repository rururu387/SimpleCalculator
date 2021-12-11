Feature: No operation set
  Татарстан супер гуд!

  Scenario: no operation set => exception => window pops up
    Given left operand is 11.34
    Given right operand is -0.3
    When result is calculated
    Then operation not supported message window pops up