import ApplicationLayerGmail.Controller;
import ApplicationLayerGmail.DataClasses.LabelField;
import ApplicationLayerGmail.DataClasses.NameField;
import ApplicationLayerGmail.PageClasses.GmailDashboard.GmailDashboard;
import ApplicationLayerGmail.UtilityClasses.GenericMethods;
import GenericLayer.GenericLibrary.DriverWaits;
import GenericLayer.GenericLibrary.UtilityMethods;
import GenericLayer.Launchpad.BrowserHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static ApplicationLayerGmail.UtilityClasses.DialogBoxes.*;

public class SendNewMailAndVerifyMailMovedToOutboxTest extends Controller {
    private static final GmailDashboard GMAIL_DASHBOARD = PageFactory.initElements(BrowserHandler.getCurrentDriver(), GmailDashboard.class);

    @Test
    public void composeAndSendMailTest() {
        DriverWaits.waitForElementClickable(GMAIL_DASHBOARD.getComposeButton());
        UtilityMethods.clickOnElement(GMAIL_DASHBOARD.getComposeButton());

        waitForDialogBoxToOpen("New Message");
        if (isDialogBoxMinimized("New Message")) expandDialogBox("New Message");
        verifyDialogBoxExpanded("New Message");

        selectMailIDsFromList("samalashish720@gmail.com");
        verifyAllReciepientsSelected("samalashish720@gmail.com");
        GenericMethods.setTextField(new NameField("subjectbox", "Test mail"));
        GenericMethods.setTextField(new LabelField("Message Body", "Test mail : Body"));

        GenericMethods.clickButtonWithText("Send");
//        MessagePopups.waitForAndVerifySentMailConfirmation();
        verifyDialogBoxClosed("New Message");
    }

    @Test
    public void checkSentMailInOutboxTest() {

    }
}