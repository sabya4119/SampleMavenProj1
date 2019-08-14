package ApplicationLayerGmail.UtilityClasses;

import ApplicationLayerGmail.DataClasses.NameField;
import ApplicationLayerGmail.PageClasses.GenericPages;
import GenericLayer.GenericLibrary.DriverWaits;
import GenericLayer.GenericLibrary.UtilityMethods;
import GenericLayer.Launchpad.BrowserHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static GenericLayer.GenericLibrary.HAssert.assertFalse;
import static GenericLayer.GenericLibrary.HAssert.fail;
import static org.testng.Assert.assertTrue;

public class DialogBoxes {
    private static final Logger logger = Logger.getLogger(DialogBoxes.class.getName());
    private static final GenericPages GENERIC_PAGES = PageFactory.initElements(BrowserHandler.getCurrentDriver(), GenericPages.class);

    public static void waitForDialogBoxToOpen(String headerText) {
        try {
            int count = 0;
            List<WebElement> dialogBoxes = GENERIC_PAGES.getDialogBoxes();
            do {
                for (WebElement dialogBox : dialogBoxes) {
                    if (dialogBox.findElement(By.cssSelector("td div.aYF")).getText().contains(headerText)) return;
                    DriverWaits.popShortTimeout(1);
                }
            } while (++count < 5);
            logger.warning("req Dialog box: " + headerText + " not present");
            fail("DialogBoxNotPresent");
        } catch (Exception e) {
            logger.warning("req Dialog box: " + headerText + " not present");
            fail("DialogBoxNotPresent");
        }
    }

    public static void verifyDialogBoxClosed(String headerText) {
        List<WebElement> dialogBoxes = GENERIC_PAGES.getDialogBoxes();
        List<String> dialogBoxHeaderTexts = new ArrayList<>();
        for (WebElement dialogBox : dialogBoxes)
            dialogBoxHeaderTexts.add(dialogBox.findElement(By.cssSelector("td div.aYF")).getText());
        if (dialogBoxHeaderTexts.contains(headerText)) {
            logger.warning("Dialog box with header text = " + headerText + " still open");
            fail("DialogboxNotClosed");
        }
    }

    public static boolean isDialogBoxMinimized(String headerText) {
        try {
            WebElement elem = null;
            try {
                elem = GENERIC_PAGES.getDialogWindow(headerText).findElement(By.xpath(".//ancestor::div[@class='aaZ']/parent::div[@class='nH']"));
            } catch (NoSuchElementException e) {
                logger.warning("req Dialog box: " + headerText + " not present");
                fail("DialogBoxNotPresent");
            }
            if (elem.getAttribute("style").contains("display: none;")) return true;
            else return false;
        } catch (Exception e) {
            logger.warning("Couldn't check for Dialog box: " + headerText + " is minimized");
            fail("IsDialogBoxMinimized");
            return true;
        }
    }

    public static void expandDialogBox(String headerText) {
        try {
            if (isDialogBoxMinimized(headerText)) {
                for (WebElement dialogBox : GENERIC_PAGES.getDialogBoxes()) {
                    WebElement elem = dialogBox.findElement(By.cssSelector("td div.aYF"));
                    if (elem.getText().contains(headerText)) {
                        UtilityMethods.clickOnElement(elem);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.warning("Failed to Expand Dialog box: " + headerText);
            fail("ExpandDialogBox");
        }
    }

    public static void verifyDialogBoxExpanded(String headerText) {
        assertFalse(isDialogBoxMinimized(headerText), "verifyDialogBoxExpanded");
    }

    public static void selectMailIDsFromList(String... mailIds) {
        try {
            for (String mailId : mailIds) {
                GenericMethods.setTextField(new NameField("to", mailId));
                if (GENERIC_PAGES.getRecepientList().getAttribute("aria-expanded").equals("false"))
                    UtilityMethods.pressTabKey();
                else {
                    List<WebElement> reciepientList = GENERIC_PAGES.getRecepientList().findElements(By.cssSelector("div[role='option'] [data-hovercard-id]"));
                    for (WebElement reciepient : reciepientList) {
                        if (reciepient.getAttribute("data-hovercard-id").equals(mailId)) {
                            UtilityMethods.clickOnElement(reciepient);
                            waitForReciepientListToClose();
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.warning("Failed to select all reciepients");
            fail("SelectReciepients");
        }
    }

    private static void waitForReciepientListToClose() {
        int counter = 0;
        do {
            if (GENERIC_PAGES.getRecepientList().getAttribute("aria-expanded").equals("false")) return;
            DriverWaits.popMillisecondTimeout(300);
        } while (++counter < 50);
    }

    public static void verifyAllReciepientsSelected(String... mailIds) {
        assertTrue(GENERIC_PAGES.getSelectedReciepients().containsAll(Arrays.asList(mailIds)), "SelectedReciepientsNotMatched");
    }
}
