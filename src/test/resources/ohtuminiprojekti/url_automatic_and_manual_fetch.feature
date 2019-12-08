Feature: As a user, internet resource information is fetched on behalf of me or I can enter them myself

        Scenario: user is displayed a page with option to either automatic fetch or manual insert a URL bookmark
            Given user is at the main page
            When a link with text "uusi nettivinkki" is clicked
            Then a page with text "Hae tiedot" is displayed
            And a page with text "Lisää nettilinkki ilman hakua" is displayed

        Scenario: user can choose to ignore the new function and manually enter a new URL bookmark
            Given command save internet resource is selected
            When url information title "Propably Some University" and url "https://www.ohwowhamburgerzlolddddfdokok.wtf" are entered
            Then a page with text "Lista lukuvinkeistä" is displayed
            And a link to address "https://www.ohwowhamburgerzlolddddfdokok.wtf" is shown in the listing
 
        Scenario: user can fetch url information automatically and save the data as a URL bookmark
            Given user is in a view to enter URL query information
            When query information "https://www.turku.fi/" is entered for field "link"
            And link query suggestions are accepted
            Then a page with text "Lista lukuvinkeistä" is displayed
            And a link to address "https://www.turku.fi/" is shown in the listing

        Scenario: when fetching url information automatically, correct information is shown to the user
            Given user is in a view to enter URL query information
            When query information "https://www.turku.fi/" is entered for field "link"
            Then a page with text "Turun kaupunki | Åbo stad | City of Turku" is displayed

        Scenario: when fetching url information automatically, user can edit the entry before saving
        