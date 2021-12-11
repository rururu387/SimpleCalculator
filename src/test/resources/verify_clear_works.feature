Feature: Verify clear button works fine
  Geese are cool!

  Scenario: 1+1=2
    Given left operand is 15.254
    Given right operand is -376.1
    Given operator set to +
    Given clear button pressed
    When result is calculated
    Then operation not supported message window pops up