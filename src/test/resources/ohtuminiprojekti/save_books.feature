Feature: As a user, I can save books

    Scenario: user can save books with title and author information
        Given command save book is selected
        When title "Spaghetti Code" and author "Team Mergehell" are entered
        Then book is saved

    Scenario: user is redirected to a listing page after adding a book
        Given command save book is selected
        When title "Lasagne Code" and author "Garfield" are entered
        Then a page with with text "Kirjalista" is displayed