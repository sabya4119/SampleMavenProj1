package ApplicationLayerGmail.UtilityClasses;

import ApplicationLayerGmail.DataClasses.IDField;
import ApplicationLayerGmail.DataClasses.LabelField;
import ApplicationLayerGmail.DataClasses.NameField;
import ApplicationLayerGmail.PageClasses.GenericPages;
import GenericLayer.GenericLibrary.DriverWaits;
import GenericLayer.GenericLibrary.UtilityMethods;
import GenericLayer.Launchpad.BrowserHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Logger;

import static GenericLayer.GenericLibrary.HAssert.fail;

public class GenericMethods {
    private static final Logger logger = Logger.getLogger(GenericMethods.class.getName());
    private static final GenericPages GENERIC_PAGES = PageFactory.initElements(BrowserHandler.getCurrentDriver(), GenericPages.class);


    public static void setTextField(NameField field) {
        try {
            DriverWaits.popShortTimeout(1);
            DriverWaits.waitForElementClickable(By.cssSelector("[name='" + field.getName() + "']"));
            WebElement fld = BrowserHandler.getWebElementWithName(field.getName());
            fld.clear();

            Actions actions = new Actions(BrowserHandler.getCurrentDriver());
            actions.moveToElement(fld).click().sendKeys(field.getValue()).perform();
        } catch (Exception e) {
            logger.warning("Field not set:" + field.getName());
            fail("FieldNotSet");
        }
    }

    public static void setTextField(LabelField field) {
        try {
            DriverWaits.popShortTimeout(1);
            DriverWaits.waitForElementClickable(By.cssSelector("[aria-label='" + field.getLabel() + "']"));
            WebElement fld = BrowserHandler.getWebElementWithName(field.getLabel());
            fld.clear();

            Actions actions = new Actions(BrowserHandler.getCurrentDriver());
            actions.moveToElement(fld).click().sendKeys(field.getValue()).perform();
        } catch (Exception e) {
            logger.warning("Field not set:" + field.getLabel());
            fail("FieldNotSet");
        }
    }

    public static void setTextField(IDField field) {
        try {
            DriverWaits.popShortTimeout(1);
            DriverWaits.waitForElementClickable(By.cssSelector("[id='" + field.getID() + "']"));
            WebElement fld = BrowserHandler.getWebElementWithName(field.getID());
            fld.clear();

            Actions actions = new Actions(BrowserHandler.getCurrentDriver());
            actions.moveToElement(fld).click().sendKeys(field.getValue()).perform();
        } catch (Exception e) {
            logger.warning("Field not set:" + field.getID());
            fail("FieldNotSet");
        }
    }

    public static void setTextField(List<NameField> fields) {
        for (NameField field : fields) {
            setTextField(field);
            UtilityMethods.pressTabKey();
            DriverWaits.popShortTimeout(1);
        }
    }

    public static void clickButtonWithValue(String buttonVal) {
        try {
            WebElement element = BrowserHandler.getWebElementWithCSSSelector("input[value='" + buttonVal + "']");
            DriverWaits.waitForElementClickable(By.cssSelector("input[value='" + buttonVal + "']"));
            UtilityMethods.clickOnElement(element);
        } catch (Exception e) {
            logger.warning("Failed to click button with value " + buttonVal);
            fail("ClickOnButtonWithValueFailed");
        }
    }

    public static void clickButtonWithText(String buttonText) {
        try {
            WebElement element = BrowserHandler.getWebElementWithXpath(".//*[@role='button'][text()='" + buttonText + "']");
            DriverWaits.waitForElementClickable(By.xpath(".//*[@role='button'][text()='" + buttonText + "']"));
            UtilityMethods.clickOnElement(element);
        } catch (Exception e) {
            logger.warning("Failed to click button with text " + buttonText);
            fail("ClickOnButtonWithValueFailed");
        }
    }
}
