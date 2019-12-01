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
    
