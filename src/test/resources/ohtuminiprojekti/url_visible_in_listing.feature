Feature: As a user, I'm able to see the URL of the added internet resource in listing

    Scenario: when manually adding a URL bookmark, the URL is seen in the listing
        Given command save internet resource is selected
        When url information title "Propably Some University" and url "https://www.jyu.fi" are entered
        And a page with with text "Lista lukuvinkeistä" is displayed
        Then a link to address "https://www.jyu.fi" is shown in the listing
        
    Scenario: when automatically feching URL data, the added book is seen in the listing
        Given command to automatically get data by URL is selected
        When url information "https://www.utu.fi" is entered
        And fetched url information is accepted
        And a page with with text "Lista lukuvinkeistä" is displayed
        Then a link to address "https://www.utu.fi" is shown in the listing

    Scenario: when adding a long bookmark, the link still points to correct address despite being abbreviated
        Given command save internet resource is selected
        When url information title "Going to be a long one" and url "https://www.youtube.com/watch?v=dQw4w9WgXcQ" are entered
        And a page with with text "Lista lukuvinkeistä" is displayed
        Then a page with with text "https://www.youtube.com/wat..." is displayed
        And a link to address "https://www.youtube.com/watch?v=dQw4w9WgXcQ" is shown in the listing


        