package GenericLayer.Launchpad;

import GenericLayer.GenericLibrary.DriverWaits;
import com.google.common.collect.Iterables;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static GenericLayer.GenericLibrary.HAssert.fail;

public class BrowserHandler {
    private static Logger logger = Logger.getLogger(BrowserHandler.class.getName());
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> mainWindowHandle = new ThreadLocal<>();
    public static final int DEFAULT_TIMEOUT = 30;
    public static String screenshotsDirPath;

    public static void startWebDriver() {
        WebDriver driver = BrowserSetup.createBrowser();
        webDriverThreadLocal.set(driver);
        mainWindowHandle.set(driver.getWindowHandle());
        Runtime.getRuntime().addShutdownHook(new Thread(new TerminateDriver(driver), "terminateDriver"));
    }

    public static void startWebDriver(String BrowserType) {
        WebDriver driver = BrowserSetup.createBrowser(BrowserType);
        webDriverThreadLocal.set(driver);
        mainWindowHandle.set(driver.getWindowHandle());
        Runtime.getRuntime().addShutdownHook(new Thread(new TerminateDriver(driver), "terminateDriver"));
    }

    static class TerminateDriver implements Runnable {
        WebDriver driver;

        TerminateDriver(WebDriver driver) {
            this.driver = driver;
        }

        @Override
        public void run() {
            if (driver == null)
                logger.info("Driver has been terminated");
            else {
                driver.quit();
                logger.info("Driver has been terminated");
            }
        }
    }

    public static WebDriver getCurrentDriver() {
        return webDriverThreadLocal.get();
    }

    public static String getCurrentURL() {
        try {
            return webDriverThreadLocal.get().getCurrentUrl();
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
        return null;
    }

    public static String getMainWindowHandle() {
        return mainWindowHandle.get();
    }

    public static void loadURL(String url) {
        try {
            webDriverThreadLocal.get().get(url);
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void maximize() {
        try {
            webDriverThreadLocal.get().manage().window().maximize();
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static Dimension getWindowSize() {
        try {
            return webDriverThreadLocal.get().manage().window().getSize();
        } catch (Exception e) {
            fail("Webdriver not initialized");
            return null;
        }
    }

    public static void setWindowSize(int width, int height) {
        Dimension dims = new Dimension(width, height);
        try {
            webDriverThreadLocal.get().manage().window().setSize(dims);
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void clearAllCookies() {
        try {
            webDriverThreadLocal.get().manage().deleteAllCookies();
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void closeAllWindows() {
        try {
            webDriverThreadLocal.get().switchTo().window(mainWindowHandle.get());
            Set<String> windowHandles = webDriverThreadLocal.get().getWindowHandles();
            for (int i = 1; i < windowHandles.size(); i++) {
                webDriverThreadLocal.get().switchTo().window(Iterables.get(windowHandles, i)).close();
            }
            webDriverThreadLocal.get().switchTo().window(mainWindowHandle.get()).close();
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static void closeAllChildWindows() {
        try {
            webDriverThreadLocal.get().switchTo().window(mainWindowHandle.get());
            Set<String> windowHandles = webDriverThreadLocal.get().getWindowHandles();
            for (int i = 1; i < windowHandles.size(); i++) {
                webDriverThreadLocal.get().switchTo().window(Iterables.get(windowHandles, i)).close();
            }
        } catch (Exception e) {
            fail("Webdriver not initialized");
        }
    }

    public static WebElement getWebElementWithXpath(String xpath, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElement(By.xpath(xpath));
        } catch (NoSuchElementException ex) {
            logger.warning("Element not located");
            throw ex;
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid xpath");
            ex.printStackTrace();
        } finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return null;
    }

    public static WebElement getWebElementWithCSSSelector(String cssSelector, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElement(By.cssSelector(cssSelector));
        } catch (NoSuchElementException ex) {
            logger.warning("Element not located");
            throw ex;
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid cssSelector");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return null;
    }

    public static WebElement getWebElementWithName(String name, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElement(By.name(name));
        } catch (NoSuchElementException ex) {
            logger.warning("Element not located");
            throw ex;
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid name");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return null;
    }

    public static WebElement getWebElementWithID(String id, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElement(By.id(id));
        } catch (NoSuchElementException ex) {
            logger.warning("Element not located");
            throw ex;
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid id");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return null;
    }

    public static WebElement getWebElementWithLinkText(String linkText, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElement(By.linkText(linkText));
        } catch (NoSuchElementException ex) {
            logger.warning("Element not located");
            throw ex;
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid linkText");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return null;
    }

    public static WebElement getWebElementWithPartialLinkText(String partialLinkText, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElement(By.partialLinkText(partialLinkText));
        } catch (NoSuchElementException ex) {
            logger.warning("Element not located");
            throw ex;
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid partialLinkText");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return null;
    }

    public static WebElement getWebElementWithTagname(String tagName, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElement(By.tagName(tagName));
        } catch (NoSuchElementException ex) {
            logger.warning("Element not located");
            throw ex;
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid tagName");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return null;
    }


    public static List<WebElement> getWebElementsWithXpath(String xpath, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElements(By.xpath(xpath));
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid xpath");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return new ArrayList<>();
    }

    public static List<WebElement> getWebElementsWithCSSSelector(String cssSelector, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElements(By.cssSelector(cssSelector));
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid cssSelector");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return new ArrayList<>();
    }

    public static List<WebElement> getWebElementsWithName(String name, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElements(By.name(name));
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid name");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return new ArrayList<>();
    }

    public static List<WebElement> getWebElementsWithID(String id, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElements(By.id(id));
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid id");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return new ArrayList<>();
    }

    public static List<WebElement> getWebElementsWithLinkText(String linkText, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElements(By.linkText(linkText));
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid linkText");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return new ArrayList<>();
    }

    public static List<WebElement> getWebElementsWithPartialLinkText(String partialLinkText, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElements(By.partialLinkText(partialLinkText));
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid partialLinkText");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return new ArrayList<>();
    }

    public static List<WebElement> getWebElementsWithTagname(String tagName, int... timeout) {
        try {
            if (timeout.length > 0) DriverWaits.setImplicitWait(timeout[0]);
            return getCurrentDriver().findElements(By.tagName(tagName));
        } catch (NullPointerException ex) {
            logger.warning("webdriver not initialized");
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.warning("Invalid tagName");
            ex.printStackTrace();
        }finally {
            DriverWaits.restoreDefaultTimeout();
        }
        return new ArrayList<>();
    }
}
