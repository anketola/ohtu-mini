Feature: user can do absolutely nothing

  Scenario: user can stare at the page
    Given user is at the main page
    When nothing is done
    Then "yay" is shown
