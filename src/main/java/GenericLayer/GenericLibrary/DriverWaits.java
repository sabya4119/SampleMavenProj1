package GenericLayer.GenericLibrary;

import GenericLayer.Launchpad.BrowserHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface DriverWaits {
    public static void restoreDefaultTimeout() {
        BrowserHandler.getCurrentDriver().manage().timeouts().implicitlyWait(BrowserHandler.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        BrowserHandler.getCurrentDriver().manage().timeouts().pageLoadTimeout(BrowserHandler.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        BrowserHandler.getCurrentDriver().manage().timeouts().setScriptTimeout(BrowserHandler.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public static void setImplicitWait(int implicitWait) {
        BrowserHandler.getCurrentDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        BrowserHandler.getCurrentDriver().manage().timeouts().pageLoadTimeout(implicitWait, TimeUnit.SECONDS);
        BrowserHandler.getCurrentDriver().manage().timeouts().setScriptTimeout(implicitWait, TimeUnit.SECONDS);

    }

    static WebElement waitForElementPresent(By by, int... timeout) throws InterruptedException {
        WebElement visible = null;
        long maxTime = BrowserHandler.DEFAULT_TIMEOUT * 1000; // time in milliseconds
        if (timeout.length > 0) maxTime = timeout[0] * 1000;
        long waitTime = 350;
        long elapsedTime = 0;
        do {
            try {
                Thread.sleep(waitTime);
                elapsedTime += waitTime;
                visible = BrowserHandler.getCurrentDriver().findElement(by);
                if (!visible.isDisplayed()) {
                    visible = null;
                } else {
                    System.out.println("element visible" + by);
                }
            } catch (NoSuchElementException e) {
                System.out.println("waiting for element to be visible" + by);
            }
        } while (visible == null && elapsedTime < maxTime);
        return visible;
    }

    static void waitForElementClickable(WebElement elem, int... timeout) {
        int timout = timeout.length > 0 ? timeout[0] : BrowserHandler.DEFAULT_TIMEOUT;
        WebDriverWait wait = new WebDriverWait(BrowserHandler.getCurrentDriver(), timout);
        wait.until(ExpectedConditions.visibilityOf(elem));
        wait.until(ExpectedConditions.elementToBeClickable(elem));
    }

    static void waitForElementClickable(By by, int... timeout) {
        int timout = timeout.length > 0 ? timeout[0] : BrowserHandler.DEFAULT_TIMEOUT;
        WebDriverWait wait = new WebDriverWait(BrowserHandler.getCurrentDriver(), timout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void popShortTimeout(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void popMillisecondTimeout(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }


    static void waitForTextVisibleOnPage(String text, int... timeout) {
        int timout = timeout.length > 0 ? timeout[0] : BrowserHandler.DEFAULT_TIMEOUT;

        for (int time = 500; time < 1000 * timout; time += 500) {
            if (BrowserHandler.getWebElementsWithXpath(".//*[text()='" + text + "']").size() > 0) return;
            else if (BrowserHandler.getWebElementsWithXpath(".//*[contains(text(),'" + text + "')]").size() > 0) return;
            else {
                List<WebElement> pageElems = BrowserHandler.getWebElementsWithTagname("*");
                for (WebElement pageElem : pageElems) {
                    if (pageElem.getText().contains(text)) return;
                }
            }
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void waitForElementVisible(WebElement webElement, int... timeout) {
        int timout = timeout.length > 0 ? timeout[0] : BrowserHandler.DEFAULT_TIMEOUT;
        WebDriverWait wait = new WebDriverWait(BrowserHandler.getCurrentDriver(), timout);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
