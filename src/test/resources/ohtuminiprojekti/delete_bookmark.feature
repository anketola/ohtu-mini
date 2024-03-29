Feature: As an user, I can delete a saved bookmark

    Scenario: user is shown a button to delete a bookmark
        Given book with title "This Books Might Get Removed" and author "Dr. Pepper" is saved
        When command list books is selected
        Then button to remove an entry is shown

    Scenario: user is shown a warning when deleting a bookmark
        Given book with title "Whoops I Made Typo In Title" and author "Dracula" is saved
        When command list books is selected
        And button to remove an entry is clicked
        Then an alert with text "Poista lukuvinkki?" is shown

    Scenario: the bookmark is deleted when user confirms the removal in a prompt
        Given book with title "Never Say Never" and author "Dracula featuring Miley Cyrus" is saved
        When command list books is selected
        And button to remove an entry is clicked
        Then bookmark "Never say Never" is no longer listed
