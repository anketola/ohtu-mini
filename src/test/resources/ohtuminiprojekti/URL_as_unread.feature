Feature: As an user, I can mark internet resources as unread

    Scenario: user can mark an internet resource as unread
        Given internet resource unread is selected
        When user chooses "Merkitse lukemattomaksi"
        Then internet resource is marked unread
