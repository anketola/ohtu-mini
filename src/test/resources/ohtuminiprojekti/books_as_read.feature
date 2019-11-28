Feature: As an user, I can mark books as read

    Scenario: user can mark a book as read
        Given book read is selected
        When user chooses "Merkitse luetuksi"
        Then book is marked read
