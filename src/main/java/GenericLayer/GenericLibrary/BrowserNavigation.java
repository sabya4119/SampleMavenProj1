package GenericLayer.GenericLibrary;

import GenericLayer.Launchpad.BrowserHandler;
import com.google.common.collect.Iterables;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

import static GenericLayer.GenericLibrary.HAssert.fail;

public interface BrowserNavigation {
    public static final Logger logger = Logger.getLogger(BrowserNavigation.class.getName());

    public static void navigateTo(String url) {
        try {
            BrowserHandler.getCurrentDriver().navigate().to(url);
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void refreshPage() {
        try {
            BrowserHandler.getCurrentDriver().navigate().refresh();
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void backOnePage() {
        try {
            BrowserHandler.getCurrentDriver().navigate().back();
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void forwardOnePage() {
        try {
            BrowserHandler.getCurrentDriver().navigate().forward();
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void switchToChildWindow(String childWindowHandle) {
        try {
            BrowserHandler.getCurrentDriver().switchTo().window(childWindowHandle);
        } catch (NoSuchWindowException ex) {
            fail("No Such window located");
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void switchToMainWindow() {
        try {
            BrowserHandler.getCurrentDriver().switchTo().window(BrowserHandler.getMainWindowHandle());
        } catch (NoSuchWindowException ex) {
            fail("No Such window located");
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void switchToChildWindow(int pos) {
        try {
            BrowserHandler.getCurrentDriver().switchTo().window(BrowserHandler.getMainWindowHandle());
            String childWindowHandle = Iterables.get(BrowserHandler.getCurrentDriver().getWindowHandles(), pos);
            BrowserHandler.getCurrentDriver().switchTo().window(childWindowHandle);
        } catch (NoSuchWindowException | IndexOutOfBoundsException ex) {
            fail("No Such window located");
        } catch (Exception e) {
            fail("Webdriver not initialized");

        }
    }

    public static void switchToAlert() {
        try {
            BrowserHandler.getCurrentDriver().switchTo().alert();
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void switchToDefaultFrame() {
        try {
            BrowserHandler.getCurrentDriver().switchTo().defaultContent();
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void switchToFrame(int index) {
        try {
            BrowserHandler.getCurrentDriver().switchTo().frame(index);
        } catch (Exception e) {
            fail("Couldn't locate desired frame");
        }
    }

    public static void switchToFrame(String frameID) {
        try {
            BrowserHandler.getCurrentDriver().switchTo().frame(frameID);
        } catch (Exception e) {
            fail("Couldn't locate desired frame");
        }
    }

    public static void switchToFrame(WebElement frameElement) {
        try {
            BrowserHandler.getCurrentDriver().switchTo().frame(frameElement);
        } catch (Exception e) {
            fail("Couldn't switch to frame  element "+frameElement);
        }
    }

    public static void switchToFrame() throws InterruptedException {
        WebElement frameElem=DriverWaits.waitForElementPresent(By.tagName("iframe"));
        BrowserHandler.getCurrentDriver().switchTo().frame(frameElem);
    }
}
