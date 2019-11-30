Feature: As an user, I can save books

    Scenario: user can save books with title and author information
        Given command save book is selected
        When title "Spaghetti Code" and author "Team Mergehell" are entered
        And command save book is selected
        And  title "Spaghetti Code 2" and author "Team Mergehell" are entered
        Then book with name "Spaghetti Code" is saved
        And book with name "Spaghetti Code 2" is saved

    Scenario: user is redirected to a listing page after adding a book
        Given command save book is selected
        When title "Lasagne Code" and author "Garfield" are entered
        Then a page with with text "Lista lukuvinkeistä" is displayed
    
    Scenario: user can save internet resources with title and author information
        Given command save internet resource is selected
        When title "C Programming Tutorial" and author "Mark Burgess" are entered
        And command save internet resource is selected
        Then internet resource with name "C Programming Tutorial" is saved

    Scenario: user is redirected to a listing page after adding an internet resource
        Given command save internet resource is selected
        When title "Programming with C" and author "Byron Gottfried" are entered
        Then a page with with text "Lista lukuvinkeistä" is displayed
