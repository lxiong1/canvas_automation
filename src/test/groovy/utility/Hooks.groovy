package utility

import cucumber.api.Scenario
import cucumber.api.java.After
import cucumber.api.java.Before
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.interactions.Actions
//import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait

import java.text.SimpleDateFormat

class Hooks {

    static WebDriver driver
    static WebDriverWait wait
    static Actions actions
    private static final testPath = "${System.getProperty('user.dir')}/src/test"

    @Before(order=0)
    static void initializeDriver()
    {
        initializeDriverFactory()
    }

    @Before(order=1)
    static void beforeScenario(Scenario scenario)
    {
        println '-----------------------------------'
        println "Starting - ${scenario.name}"
        println '-----------------------------------\n'
    }

    @After(order=0)
    static void afterScenario(Scenario scenario)
    {
        println "\nSTATUS: ${scenario.status}\n"
        println '-----------------------------------'
        println "Closing - ${scenario.name}"
        println '-----------------------------------\n'
    }

    @After(order=1)
    static void tearDown(Scenario scenario)
    {
        screenshotOnFail(scenario)
        closeDriver()
    }

    private static void initializeDriverFactory() {
        System.setProperty('webdriver.chrome.driver', "${testPath}/resources/chromedriver")
        ChromeOptions chromeOptions = new ChromeOptions()
        chromeOptions.addArguments("start-fullscreen")
        chromeOptions.addArguments("--disable-notifications")
        driver = new ChromeDriver(chromeOptions)
        wait = new WebDriverWait(driver, 5)
        actions = new Actions(driver)
    }

    private static void screenshotOnFail(Scenario scenario) {
        if(scenario.failed) {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE)
            FileUtils.copyFile(screenshot, new File(
                    "${testPath}/screenshots/${scenario.name.replaceAll('\\s', '_')}_${currentDateTime()}.jpg"))
        }
    }

    private static String currentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy.HH:mm:ss")
        Date date = new Date()
        return dateFormat.format(date)
    }

    private static void closeDriver() {
        try {
            driver.quit()
        } catch(Exception e){
            println e.message
        }
    }
}
