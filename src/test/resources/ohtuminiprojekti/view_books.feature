Feature: As a user, I can see a list of saved books

    Scenario: user can see no books listed when no books saved
        Given there are no books saved
        When command list books is selected
        Then empty list of books is shown

    Scenario: user can see saved books listed
        Given book with title "title" and author "author" is saved
        When command list books is selected
        Then book with title "title" and author "author" is listed