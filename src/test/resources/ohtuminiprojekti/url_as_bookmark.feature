Feature: As an user, I can save internet resources

    Scenario: user can save internet resources with title and url information
        Given command save internet resource is selected
        When url information title "Wery Nize Webzite" and url "www.nosuchsiteithinkok.wow" are entered
        Then internet resource with name "Wery Nize Webzite" is saved

    Scenario: user is redirected to a listing page after adding an internet resource
        Given command save internet resource is selected
        When url information title "This Too Wery Nice Webzite" and url "www.ohwowhamburgerzlolokok.wow" are entered
        Then a page with with text "Lista lukuvinkeistä" is displayed

    Scenario: bookmark added as an internet resource has type "link"
        Given command save internet resource is selected
        When url information title "This too Wery Nize Webzite" and url "www.lookimdoingtesting.wow" are entered
        And a page with with text "Lista lukuvinkeistä" is displayed
        Then a bookmark with a type "link" is displayed