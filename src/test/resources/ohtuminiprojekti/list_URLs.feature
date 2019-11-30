Feature: As an user, I can see a list of saved internet resources (URLs)

    Scenario: List of URLs is empty, when no IRs are saved
        Given there are no URLs saved
        When command list URLs is selected
        Then empty list of URLs is shown

    Scenario: List of URLs contains an, when one URL is saved.
        Given URL with title "C Programming Tutorial" and author "Mark Burgess" is saved
        When command list URLs is selected
        Then URL with title "C Programming Tutorial" and author "Mark Burgess" is listed
        
    Scenario: List of URLs contains URLs, when multiple URLs are saved.
        Given URL with title "C Programming Tutorial" and author "Mark Burgess" is saved
        Given URL with title "Programming with C" and author "Byron Gottfried" is saved
        When command list URLs is selected
        Then URL with title "C Programming Tutorial" and author "Mark Burgess" is listed
        And URL with title "Programming with C" and author "Byron Gottfried" is listed
