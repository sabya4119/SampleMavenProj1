package GenericLayer.Launchpad;

import GenericLayer.GenericLibrary.FileUtilities;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static GenericLayer.GenericLibrary.HAssert.assertNotNull;


public class BrowserSetup {
    private static final Logger logger = Logger.getLogger(BrowserSetup.class.getName());
    private static Map<String, String> webDriverManagerProperties;
    private static String downloadsDirPath;
    private static String profilesDirPath;
    private static String screenshotsDirPath;

    public static String getDownloadsDirPath() {
       return downloadsDirPath;
    }

    public static String getScreenshotsDirPath() {
        return screenshotsDirPath;
    }

    public static WebDriver createBrowser() {
        String browserType = SystemConfig.getBrowserType();
        assertNotNull("Required property 'application.browser' not defined!", browserType);
        return createBrowser(browserType);
    }

    public static void createFileDownloadsDirectory() {
        File downloadDir = new File(FileUtilities.getCWD(), ".\\target\\DownloadedFiles");
        FileUtilities.createDirectory(downloadDir.getPath());
        downloadsDirPath = downloadDir.getAbsolutePath();
    }

    public static void createScreenshotsDirectory() {
        File screenshotsDir = new File(FileUtilities.getCWD(), ".\\target\\Screenshots");
        FileUtilities.createDirectory(screenshotsDir.getPath());
        screenshotsDirPath = screenshotsDir.getAbsolutePath();
    }

    public static void createProfilesDirectory() {
        File profilesDir = new File(FileUtilities.getCWD(), ".\\target\\Profiles");
        FileUtilities.createDirectory(profilesDir.getPath());
        profilesDirPath = profilesDir.getAbsolutePath();
    }

    public static WebDriver createBrowser(String browserType) {
        WebDriver driver = null;
        DesiredCapabilities capabilities;
        createFileDownloadsDirectory();
        createScreenshotsDirectory();
        createProfilesDirectory();

        setWebDriverManagerProperties();
        switch (browserType) {
            case "IE":
                driver = launchBrowser(InternetExplorerDriver.class);
                break;
            case "FIREFOX":
                driver = launchFirefoxBrowser();
                break;
            case "CHROME_HEADLESS":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-gpu");
                options.addArguments("headless");
                driver = launchChromeBrowser(options);
                break;
            case "CHROME":
            default:
                driver = launchChromeBrowser();
        }
        initialBrowserSetup(driver);
        return driver;
    }

    /*Default values for webDriverManage properties present in webdrivermanager.properties file :

        wdm.properties=webdrivermanager.properties
        wdm.targetPath=~/.m2/repository/webdriver
        wdm.forceCache=false
        wdm.override=false
        wdm.useMirror=false
        wdm.useBetaVersions=false
        wdm.avoidExport=false
        wdm.avoidOutputTree=false
        wdm.avoidAutoVersion=false
        wdm.avoidAutoReset=false
        wdm.avoidPreferences=false
        wdm.timeout=30
        wdm.serverPort=4041
        wdm.ttl=86400

        wdm.chromeDriverUrl=https://chromedriver.storage.googleapis.com/
        wdm.chromeDriverMirrorUrl=http://npm.taobao.org/mirrors/chromedriver
        wdm.chromeDriverExport=webdriver.chrome.driver
        wdm.geckoDriverUrl=https://api.github.com/repos/mozilla/geckodriver/releases
        wdm.geckoDriverMirrorUrl=http://npm.taobao.org/mirrors/geckodriver
        wdm.geckoDriverExport=webdriver.gecko.driver
        wdm.operaDriverUrl=https://api.github.com/repos/operasoftware/operachromiumdriver/releases
        wdm.operaDriverMirrorUrl=http://npm.taobao.org/mirrors/operadriver
        wdm.operaDriverExport=webdriver.opera.driver
        wdm.phantomjsDriverUrl=https://bitbucket.org/ariya/phantomjs/downloads/
        wdm.phantomjsDriverMirrorUrl=http://npm.taobao.org/mirrors/phantomjs
        wdm.phantomjsDriverExport=phantomjs.binary.path
        wdm.edgeDriverUrl=https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
        wdm.edgeDriverExport=webdriver.edge.driver
        wdm.internetExplorerDriverUrl=https://selenium-release.storage.googleapis.com/
        wdm.internetExplorerDriverExport=webdriver.ie.driver
        wdm.seleniumServerStandaloneUrl=https://selenium-release.storage.googleapis.com/
        wdm.versionsPropertiesUrl=https://raw.githubusercontent.com/bonigarcia/webdrivermanager/master/src/main/resources/versions.properties

        Other configurable property values (keys) :
        wdm.chromeDriverVersion, wdm.operaDriverVersion, wdm.internetExplorerDriverVersion, wdm.edgeDriverVersion, wdm.phantomjsDriverVersion, wdm.geckoDriverVersion
        wdm.architecture=32 (or 64)
        wdm.os=WIN (or LINUX or MAC)
        wdm.proxy=<MyProxy>:<port> ( OR <username>:<password>@<MyProxy>:<port>)
        wdm.proxyUser
        wdm.proxyPass
        wdm.ignoreVersions
        wdm.gitHubTokenName
        wdm.gitHubTokenSecret
        wdm.binaryPath

        This method provides default overrides for these keys if necessary. Use proper key names.
         */
    private static void initializeWebDriverManagerProperties() {
        webDriverManagerProperties = new HashMap<>();
        webDriverManagerProperties.put("wdm.targetPath", System.getProperty("user.dir") + ".\\src\\main\\resources");
        webDriverManagerProperties.put("wdm.avoidOutputTree","true");
    }

    private static void setWebDriverManagerProperties() {
        initializeWebDriverManagerProperties();
        for (String key : webDriverManagerProperties.keySet()) {
            System.setProperty(key, webDriverManagerProperties.get(key));
        }
    }

    private static WebDriver launchFirefoxBrowser(FirefoxOptions... opts) {
        WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
        FirefoxProfile profile = new FirefoxProfile();
        String mimetypes = "text/html;"
                + "text/xml;application/xml;"
                + "application/excel; application/vnd.ms-excel; application/x-excel; application/x-msexcel;"
                + "application/pdf;"
                + "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", downloadsDirPath);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", mimetypes);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        options.setAcceptInsecureCerts(true);

        DesiredCapabilities cap = DesiredCapabilities.firefox();
        if (opts.length > 0)
            for (FirefoxOptions op : opts) cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, op);
        return new FirefoxDriver(options.merge(cap));
    }

    private static WebDriver launchChromeBrowser(ChromeOptions... opts) {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximised", "--make-default-browser", "--disable-infobars", "user-data-dir=" + profilesDirPath);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadsDirPath);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("safebrowsing.enabled", "false");
        options.setExperimentalOption("prefs", prefs);
//        cap.setCapability(ChromeOptions.CAPABILITY,options);
        if (opts.length > 0)
            for (ChromeOptions op : opts) cap.setCapability(ChromeOptions.CAPABILITY, op);
        return new ChromeDriver(options.merge(cap));
    }

    private static WebDriver launchBrowser(Class<? extends WebDriver> driverclass) {
        WebDriverManager.getInstance(driverclass).setup();
        try {
            return driverclass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.warning("Couldn't create an instance of " + driverclass.getName());
            return null;
        }
    }

    private static void initialBrowserSetup(WebDriver driver) {
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(BrowserHandler.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1600, 800));
        driver.manage().window().maximize();
    }
}
