package ApplicationLayerGmail;

import ApplicationLayerGmail.UtilityClasses.SignInSignOut;
import GenericLayer.GenericLibrary.BrowserNavigation;
import GenericLayer.GenericLibrary.UtilityMethods;
import GenericLayer.Launchpad.BrowserHandler;
import GenericLayer.Launchpad.SystemConfig;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * TO BE INHERITED BY ALL TEST CLASSES
 * This class contains all basic setup methods which would be required to run the test cases
 */
public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    /*
    Will be executed once before every test class
     */
    @BeforeClass
    public static void beforeClassTest() {
        System.out.println("In beforeClassTest\n");
        BrowserHandler.startWebDriver();
    }

    /*
    Will be executed once after every test class
     */
    @AfterClass
    public static void afterClassTest() {
        System.out.println("In afterClassTest\n");
        UtilityMethods.handleAlerts();
        BrowserNavigation.switchToMainWindow();
        SignInSignOut.signOutOfGmail();
        BrowserHandler.clearAllCookies();
        BrowserHandler.closeAllWindows();
    }

    /*
    Will be executed once before every test method
     */
    @BeforeMethod
    public static void beforeMethodTest(Method method) {
        System.out.println("In beforeMethodTest\n");
        logger.info("Executing Scenario :" + method.getName());
        BrowserHandler.getCurrentDriver().get(SystemConfig.getApplnUrl());
        SignInSignOut.signInToGmail();
    }

    /*
        Will be executed once after every test method
    */
    @AfterMethod
    public static void afterMethodTest(Method method) {
        System.out.println("In afterMethodTest\n");
        logger.info("Finished Scenario :" + method.getName());
        UtilityMethods.takeScreenshot("Scecenario_"+method.getName());
    }

    /*
        Will be executed once after every test group.
    */

    @BeforeGroups
    public static void beforeGroupsTest() {
        System.out.println("In beforeGroupsTest\n");
    }

    /*
        Will be executed once after every test group.
    */
    @AfterGroups
    public static void afterGroupsTest() {
        System.out.println("In afterGroupsTest\n");
    }
}
