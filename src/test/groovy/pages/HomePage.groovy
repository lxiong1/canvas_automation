package pages

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.How
import org.openqa.selenium.support.ui.ExpectedConditions
import static org.junit.Assert.*

class HomePage extends UtilityPage {

    void verifyDashboardCardsTraverse() {
        wait.until(ExpectedConditions.visibilityOfAllElements(dashboardCards))
        traverseElements(dashboardCards)
    }

    void verifyGlobalNavigationNames(String navigationName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(globalNavigationBar))
        WebElement navigation = getNamedElement(globalNavigationBar, navigationName)
        assertEquals(navigationName, navigation.getText())
    }

    void verifyHeader(String headerName) {
        wait.until(ExpectedConditions.visibilityOf(headerTitle))
        WebElement header = getNamedElement(headerTitle, headerName)
        assertEquals(headerName, header.getText())
    }

    /*
    ----------------
    Page Objects
    ----------------
    */

    @FindBy(how = How.CSS, using = '.ic-DashboardCard__header_hero')
    private List<WebElement> dashboardCards

    @FindBy(how = How.CSS, using = '.ic-Dashboard-header__title')
    private WebElement headerTitle
}
