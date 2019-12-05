Feature: As an user, I can save YouTube videos

    Scenario: user can save YouTube videos with title and url information
        # Given command save YouTube video is selected
        # When url information title "Learn Java 8 - Full Tutorial for Beginners" and url "https://www.youtube.com/watch?v=grEKMHGYyns" are entered
        # Then YouTube video with name "Learn Java 8 - Full Tutorial for Beginners" is saved

    Scenario: user is redirected to a listing page after adding a YouTube video
        # Given command save YouTube video is selected
        # When url information title "Learn Java 8 - Full Tutorial for Beginners" and url "https://www.youtube.com/watch?v=grEKMHGYyns" are entered
        # Then a page with with text "Lista lukuvinkeistä" is displayed

    Scenario: bookmark added as an YouTube video has type "link"
        # Given command save YouTube video is selected
        # When url information title "Learn Java 8 - Full Tutorial for Beginners" and url "https://www.youtube.com/watch?v=grEKMHGYyns" are entered
        # And a page with with text "Lista lukuvinkeistä" is displayed
        # Then a bookmark with a type "link" is displayed
