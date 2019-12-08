Feature: As a user, I can add a Youtube video as a bookmark

        Scenario: user is displayed a page with option to either automatic fetch Youtube-video information or enter information manually
            Given user is at the main page
            When a link with text "uusi videovinkki" is clicked
            Then a page with text "Hae tiedot" is displayed
            And a page with text "Lisää video ilman hakua" is displayed

        Scenario: user can fetch book information by based on Youtube URL and correct information is shown
            Given user is in a view to enter video query information
            When query information "https://www.youtube.com/watch?v=3moLkjvhEu0" is entered for field "link"
            Then a page with text "Disturbed - Stricken [Official Music Video] - YouTube" is displayed
            And a page with text "https://www.youtube.com/watch?v=3moLkjvhEu0" is displayed

        Scenario: Youtube video information fetched and accepted by the user will be saved and displayed in the listing
            Given user is in a view to enter video query information
            When query information "https://www.youtube.com/watch?v=nZ62Mewedbs" is entered for field "link"
            And video query suggestions are accepted
            Then a page with text "Lista lukuvinkeistä" is displayed
            And a page with text "Witchfinder General - Witchfinder General - YouTube" is displayed
            And a link to address "https://www.youtube.com/watch?v=nZ62Mewedbs" is shown in the listing

        Scenario: user is able to edit automatically fetched information before saving the new bookmark

        Scenario: clicking on a video link opens a new window to view the video bookmark