Feature: As an user, I can tag bookmarks as already read

    Scenario: user is shown a button to mark the bookmark as read
        Given book with title "How To Teach Cats To Write Tests" and author "Mr. Fluffly" is saved
        When command list books is selected
        Then button to mark entry as read is shown

    Scenario: when user presses a button to mark the bookmark as read, the status from unread to read
        Given book with title "How To Teach Your Parrot To Bark" and author "Mr. Fluffly" is saved
        When command list books is selected
        And button to mark entry as read is shown
        And button to mark entry read is pressed
        Then bookmark has changed status to read

    Scenario: bookmark marked as read can be changed back to unread
        Given book with title "How to Automate Test Automation" and author "Mr. Automation" is saved
        When command list books is selected
        And button to mark entry unread is pressed
        And command list books is selected
        Then bookmark has changed status to unread

    Scenario: bookmarks marked as unread can be seen in a seperate listing
        Given book with title "How to Automate Test Automation" and author "Mr. Automation" is saved
        When command list unread books is selected
        Then bookmark "How to Automate Test Automation" is listed

    Scenario: bookmarks marked as read can be seen in a seperate listing
        Given book with title "Running Out Of Book Names" and author "BookNamer" is saved
        When command list books is selected
        And button to mark entry read is pressed
        And command list unread books is selected
        Then bookmark "Running Out Of Book Names" is listed