package GenericLayer.GenericLibrary;

import GenericLayer.Launchpad.BrowserHandler;
import GenericLayer.Launchpad.BrowserSetup;
import com.google.common.io.Files;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public interface UtilityMethods {
    public static final Logger logger = Logger.getLogger(UtilityMethods.class.getName());

    public static void takeScreenshot(String filename) {
        File dir = new File(BrowserSetup.getScreenshotsDirPath());
        dir.mkdirs();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hhmmss");
        Date curDate = new Date();
        String strDate = sdf.format(curDate);
        filename = filename.replace(".png","");
        filename = filename + strDate+".png";
        File imageFile = new File(dir, filename);
        try {
            TakesScreenshot ts = (TakesScreenshot) BrowserHandler.getCurrentDriver();
            File src = ts.getScreenshotAs(OutputType.FILE);
            Files.copy(src, imageFile);
            System.out.format("Wrote screen shot to '%s'", imageFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.format("Unable to write screen shot to '%s': %s", imageFile.getAbsolutePath(), e.getMessage());
        }
    }


    public static void handleAlerts() {
    }

    public static void clickOnElement(WebElement elem){
        try {
            Actions actions=new Actions(BrowserHandler.getCurrentDriver());
            DriverWaits.waitForElementClickable(elem);
            actions.moveToElement(elem).click().perform();
        } catch (Exception e) {
            logger.info("Element Click failed");
            throw e;
        }
    }

    public static void pressTabKey(){
        Actions actions=new Actions(BrowserHandler.getCurrentDriver());
        DriverWaits.popMillisecondTimeout(500);
        actions.sendKeys(Keys.TAB).perform();
    }

    public static void pressEnterKey(){
        Actions actions=new Actions(BrowserHandler.getCurrentDriver());
        DriverWaits.popMillisecondTimeout(500);
        actions.sendKeys(Keys.ENTER).perform();
    }
}
