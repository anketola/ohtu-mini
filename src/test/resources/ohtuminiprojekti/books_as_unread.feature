Feature: As an user, I can mark books as unread

    Scenario: user can mark a book as unread
        Given book unread is selected
        When user chooses "Merkitse lukemattomaksi"
        Then book is marked unread
