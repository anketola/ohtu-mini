Feature: As a user, I'm shown the listing of bookmarks directly as I enter the website

    Scenario: user is redirected to the book listing page as the root is accessed
        Given user is at the main page
        When nothing is done
        Then "Lista lukuvinkeistä" is shown

    Scenario: user is no longer shown a seperate greeting as the website is entered
        Given user is at the main page
        When nothing is done
        Then a message "Tervetuloa lukuvinkkisovelluukseen!" is not displayed