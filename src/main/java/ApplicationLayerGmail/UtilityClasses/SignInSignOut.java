package ApplicationLayerGmail.UtilityClasses;

import ApplicationLayerGmail.ApplicationConfig;
import ApplicationLayerGmail.DataClasses.GmailCredentials;
import ApplicationLayerGmail.DataClasses.NameField;
import ApplicationLayerGmail.PageClasses.GmailDashboard.GmailDashboard;
import ApplicationLayerGmail.PageClasses.Login.GmailHomePage;
import ApplicationLayerGmail.PageClasses.Login.GmailLoginPage;
import GenericLayer.GenericLibrary.BrowserNavigation;
import GenericLayer.GenericLibrary.DriverWaits;
import GenericLayer.GenericLibrary.UtilityMethods;
import GenericLayer.Launchpad.BrowserHandler;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class SignInSignOut {
    private static final Logger logger = Logger.getLogger(SignInSignOut.class.getName());
    private static final GmailHomePage GMAIL_HOME_PAGE = PageFactory.initElements(BrowserHandler.getCurrentDriver(), GmailHomePage.class);
    private static final GmailLoginPage GMAIL_LOGIN_PAGE = PageFactory.initElements(BrowserHandler.getCurrentDriver(), GmailLoginPage.class);
    private static final GmailDashboard GMAIL_DASHBOARD = PageFactory.initElements(BrowserHandler.getCurrentDriver(), GmailDashboard.class);

    public static void signInToGmail(GmailCredentials credentials){


    }

    public static void signInToGmail(){
        UtilityMethods.clickOnElement(GMAIL_HOME_PAGE.getSignInLink());
        BrowserNavigation.switchToChildWindow(1);
        DriverWaits.waitForElementVisible(GMAIL_LOGIN_PAGE.getuserNameField());
        GenericMethods.setTextField(new NameField("Email", ApplicationConfig.getDefaultCredentials().getUsername()));
        GenericMethods.clickButtonWithValue("Next");
        DriverWaits.waitForElementVisible(GMAIL_LOGIN_PAGE.getpasswordField());
        GenericMethods.setTextField(new NameField("Passwd", ApplicationConfig.getDefaultCredentials().getPassword()));
        GenericMethods.clickButtonWithValue("Sign in");
        DriverWaits.waitForElementVisible(GMAIL_DASHBOARD.getdashboardLogo());
        DriverWaits.popShortTimeout(2);
    }

    public static void signOutOfGmail(){

    }
}
