package step_definitions

import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import org.openqa.selenium.support.PageFactory
import pages.HomePage

class HomePageSteps {

    HomePage homePage = PageFactory.initElements(new HomePage().driver, HomePage.class)

    /*
    ----------------
    Given Statements
    ----------------
     */
    @Given('^I navigate to the canvas website$')
    void iNavigateToTheCanvasWebsite() {
        homePage.goToCanvas()
    }

    @Given('^I log in with my credentials$')
    void iLogInWithMyCredentials() {
        homePage.logIn()
    }

    /*
    ----------------
    When Statements
    ----------------
     */

    @When('^I click on "([^"]*)" in the navigation bar$')
    void iClickOnInTheNavigationBar(String navigationName) {
        homePage.clickNavigationName(navigationName)
    }

    @When('^I click on "([^"]*)" from the help menu$')
    void iClickOnFromTheHelpMenu(String helpName) {
        homePage.clickHelpName(helpName)
    }

    /*
    ----------------
    Then Statements
    ----------------
     */

    @Then('^I am able to click on each dashboard card available$')
    void iAmAbleToClickOnEachDashboardCardAvailable() {
        homePage.verifyDashboardCardsTraverse()
    }

    @Then('^I am able to see navigation bar menu items "([^"]*)"$')
    void iAmAbleToSeeNavigationBarMenuItems(String navigationName) {
        homePage.verifyGlobalNavigationNames(navigationName)
    }

    @Then('^I see the "([^"]*)" header name$')
    void iSeeTheHeaderName(String headerName) {
        homePage.verifyHeader(headerName)
    }

    @Then('^I am able to log out$')
    void iAmAbleToLogOut() {
        homePage.verifyLogOut()
    }

    @Then('^I am able to see the following email support message:$')
    void iAmAbleToSeeTheFollowingEmailSupportMessage(String message) {
        homePage.verifyEmailSupportMessage(message)
    }
}
