Feature: As an user, I can add same bookmark only once

    Scenario: user will recieve an error if book with same author and title are entered
        Given book with title "Wrecking Ball" and author "Jimi Hendrix" is saved
        Given book with title "Wrecking Ball" and author "Jimi Hendrix" is saved
        Then an error message with text "Lukuvinkki on jo tallennettu!" is displayed

    Scenario: user will not recieve an error if a book with same title but different author is entered
        Given book with title "Wrecking Ball" and author "Jimi Hendrix" is saved
        Given book with title "Wrecking Ball" and author "Vlad the Impaler" is saved
        Then an error message with text "Lukuvinkki on jo tallennettu!" is not displayed

    Scenario: user will not recieve an error if a book with same author but different title is entered
        Given book with title "Wrecking Ball" and author "Jimi Hendrix" is saved
        Given book with title "Wrecking Cube" and author "Jimi Hendrix" is saved
        Then an error message with text "Lukuvinkki on jo tallennettu!" is not displayed