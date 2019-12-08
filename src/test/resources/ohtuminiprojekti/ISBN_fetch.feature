Feature: As a user, book information is fetched for me based on ISBN or I can enter data myself

        Scenario: user is displayed a page with option to either automatic fetch by ISBN or manually book information
            Given user is at the main page
            When a link with text "uusi kirjavinkki" is clicked
            Then a page with text "Hae tiedot" is displayed
            And a page with text "Lisää kirja ilman ISBN-tunnusta" is displayed
        
        Scenario: user can choose to ignore the new function and manually enter a book information
            Given command save book is selected
            When title "Why Does Youtube Suggests Me Weird Videos" and author "Yayyyy" are entered
            Then a page with text "Lista lukuvinkeistä" is displayed
            And a page with text "Why Does Youtube Suggests Me Weird Videos" is displayed
            And a page with text "Yayyyy" is displayed

        Scenario: user can fetch book information by ISBN and save the data as a bookmark
            Given user is in a view to enter ISBN query information
            When query information "9788215027029" is entered for field "isbn"
            And ISBN query suggestions are accepted
            Then a page with text "Lista lukuvinkeistä" is displayed
            And a page with text "Scandinavian Maritime Law" is displayed
        
        Scenario: when fetching information based on ISBN, correct information is shown to the user
            Given user is in a view to enter ISBN query information
            When query information "9788215027029" is entered for field "isbn"
            Then a page with text "Scandinavian Maritime Law" is displayed
            And a page with text "Thor Falkanger, Hans Jacob Bull, Lasse Brautaset" is displayed
        
        Scenario:  when fetching url based on ISBN, user can edit the entry before saving

