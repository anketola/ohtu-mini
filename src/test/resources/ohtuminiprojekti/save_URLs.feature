Feature: As an user, I can save internet resources

    Scenario: user can save internet resources with title and author information
        Given command save internet resource is selected
        When title "C Programming Tutorial" and author "Mark Burgess" are entered
        And command save internet resource is selected
        Then internet resource with name "C Programming Tutorial" is saved

    Scenario: user is redirected to a listing page after adding an internet resource
        Given command save internet resource is selected
        When title "Programming with C" and author "Byron Gottfried" are entered
        Then a page with with text "Lista lukuvinkeistä" is displayed
