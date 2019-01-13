package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.How
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import utility.Hooks
import static org.junit.Assert.*

class UtilityPage {

    static WebDriver driver
    static WebDriverWait wait
    static Actions actions

    UtilityPage() {
        driver = Hooks.driver
        wait = Hooks.wait
        actions = Hooks.actions
    }

    void goToCanvas() {
        driver.get('https://canvas.umn.edu')
    }

    void logIn() {
        wait.until(ExpectedConditions.visibilityOf(usernameField))
        usernameField.sendKeys(getSecret('username'))
        passwordField.sendKeys(getSecret('password'))
        signInButton.click()
    }

    void logOut() {
        wait.until(ExpectedConditions.visibilityOf(logOutButton))
        logOutButton.click()
    }

    String getSecret(String propertyName) {
        Properties property = new Properties()
        property.load(this.class.getClassLoader().getResourceAsStream('secrets.properties'))
        return property.getProperty(propertyName)
    }

    void traverseElements(List<WebElement> webElements) {
        for(int i = 0; i < webElements.size(); i++) {
            webElements[i].click()
            driver.navigate().back()
        }
    }

    WebElement getNamedElement(List<WebElement> webElements, String name) {
        for(WebElement webElement in webElements) {
            if(webElement.getText() == name) {
                return webElement
            }
        }
        return null
    }

    WebElement getNamedElement(WebElement webElement, String name) {
        webElement.findElement(By.xpath("//*[contains(text(), '${name}')]"))
        return webElement
    }

    void clickElementName(List<WebElement> webElements ,String name) {
        wait.until(ExpectedConditions.visibilityOfAllElements(webElements))
        WebElement webElement = getNamedElement(webElements, name)
        webElement.click()
    }

    void clickNavigationName(String name) {
        clickElementName(globalNavigationBar, name)
    }

    void clickHelpName(String name) {
        clickElementName(helpMenuList, name)
    }

    void verifyLogOut() {
        logOut()
        wait.until(ExpectedConditions.visibilityOf(signOutMessage))
        assertEquals("Sign Out Successful", signOutMessage.getText())
    }

    private void verifyMessage(WebElement webElement, String message) {
        wait.until(ExpectedConditions.visibilityOf(webElement))
        assertEquals(message, webElement.getText())
    }

    void verifyEmailSupportMessage(String message) {
        verifyMessage(emailSupportMessage, message)
    }

    /*
    ----------------
    Page Objects
    ----------------
    */

    @FindBy(how = How.ID, using = 'username')
    private WebElement usernameField

    @FindBy(how = How.ID, using = 'password')
    private WebElement passwordField

    @FindBy(how = How.CSS, using = '.idp3_form-submit.btn-lg')
    private WebElement signInButton

    @FindBy(how = How.CSS, using = "form[action='/logout'] button[type='submit']")
    private WebElement logOutButton

    @FindBy(how = How.CSS, using = '.ic-app-header__menu-list-link')
    public List<WebElement> globalNavigationBar

    @FindBy(how = How.CSS, using = "#help_tray a[role='button']")
    private List<WebElement> helpMenuList

    @FindBy(how = How.CSS, using = ".ic-HelpDialog__form-legend")
    public WebElement emailSupportMessage

    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Sign Out Successful')]")
    public WebElement signOutMessage
}
