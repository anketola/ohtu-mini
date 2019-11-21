Feature: As a user, I can save books

    Scenario: user can save books with title and author information
        Given command save book is selected
        When title "Spaghetti Code" and author "Team Mergehell" are entered
        Then book is saved