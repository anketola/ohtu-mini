Feature: As a user, internet resource information is fetched on behalf of me or I can enter them myself

        Scenario: user is displayed a page with option of either automatic fetch or manual insert
            Given user is at the main page
            When a link with text "uusi nettivinkki" is clicked
            Then a page with text "Hae tiedot" is displayed
            And a page with text "Lisää nettilinkki ilman hakua" is displayed