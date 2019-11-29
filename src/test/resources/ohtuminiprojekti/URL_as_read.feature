Feature: As an user, I can mark internet resources as read

    Scenario: user can mark an internet resource as read
        Given internet resource read is selected
        When user chooses "Merkitse luetuksi"
        Then internet resource is marked read
