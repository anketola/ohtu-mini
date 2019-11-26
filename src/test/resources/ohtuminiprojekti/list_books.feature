Feature: As an user, I can see a list of saved books

    Scenario: List of books is empty, when no books are saved
        Given there are no books saved
        When command list books is selected
        Then empty list of books is shown

    Scenario: List of books contains book, when one book is saved.
        Given book with title "Uolevin elämä ja teot" and author "Kotivalo" is saved
        When command list books is selected
        Then book with title "Uolevin elämä ja teot" and author "Kotivalo" is listed
        
    Scenario: List of books contains books, when multiple books are saved.
        Given book with title "Uolevin elämä ja teot" and author "Kotivalo" is saved
        Given book with title "Syrjälän puhelinluettelo" and author "Syrjälän tietotoimisto" is saved
        When command list books is selected
        Then book with title "Uolevin elämä ja teot" and author "Kotivalo" is listed
        And book with title "Syrjälän puhelinluettelo" and author "Syrjälän tietotoimisto" is listed