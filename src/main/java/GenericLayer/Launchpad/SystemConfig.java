package GenericLayer.Launchpad;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

import static GenericLayer.GenericLibrary.HAssert.assertNotNull;
import static GenericLayer.GenericLibrary.HAssert.fail;
import static com.google.common.base.Strings.isNullOrEmpty;


public class SystemConfig extends HashMap<String, Object> {
    private static final String BROWSER = "application.browser";
    private static final String APPLN_URL = "application.url";
    private static final Logger logger = Logger.getLogger(SystemConfig.class.getName());
    private static SystemConfig config = new SystemConfig();
    // The properties file that defines the application url and credentials.
    private static final String RESOURCE_FILE = "SampleMavenProj1.properties";

    private SystemConfig() {
        loadSystemCnfiguration();
    }

    @Override
    public String toString() {
        return "BROWSER=" + config.get(BROWSER) + "\nAPPLN_URL" + config.get(BROWSER) + "\nRESOURCE_FILE=" + RESOURCE_FILE;
    }

    public static String getSystemConfig() {
        return config.toString();
    }

    public static String getBrowserType() {
        return (String) config.get(BROWSER);
    }

    public static String getApplnUrl() {
        return (String) config.get(APPLN_URL);
    }

    private static URL setURLProperty(String name, String value) {
        assertNotNull(String.format("Required property '%s' not specified", name), value);

        try {
            URL url = new URL(value);
            config.put(APPLN_URL, url);
            return url;
        } catch (MalformedURLException e) {
            String msg2 = String.format(
                    "Property '%s' specifies an invalid URL '%s': %s",
                    name,
                    value,
                    e.getMessage()
            );
            fail(msg2);
            return null; // will never happen due to fail() call above.
        }
    }

    private static void setBrowserProperty(String value) {
        assertNotNull("Required property 'browser' not specified", value);
        String browserType;
        switch (value) {
            case "FIREFOX":
                browserType = value;
                break;
            case "IE":
                browserType = value;
                break;
            case "CHROME_HEADLESS":
                browserType = value;
                break;
            case "CHROME":
                browserType = value;
                break;
            default:
                logger.warning("Illegal browser type parameter, using default");
                browserType = "CHROME";
        }
        config.put(BROWSER, browserType);
    }

    private static String getPropertyValue(Properties props, String name) {
        String value = System.getenv(name);

        if (!isNullOrEmpty(value)) {
            logger.info(String.format("Property [%s] value [%s] set by environment variable", name, value));
            return value;
        }

        value = System.getProperty(name);

        if (!isNullOrEmpty(value)) {
            logger.info(String.format("Property [%s] value [%s] set by system property", name, value));
            return value;
        }

        if (props != null) {
            value = props.getProperty(name);
            logger.info(String.format("Property [%s] value [%s] set by maven profile", name, value));
        }

        return value;
    }

    /**
     * Returns the value of the specified property by consulting two sources:
     * Environment and JVM Property in that order (1st one wins).
     *
     * @param name - name of desired property
     */
    private static String getPropertyValue(String name) {
        return getPropertyValue(null, name);
    }


    /**
     * Load the properties as defined by the 'SampleMavenProj1.properties' file
     * with optional JVM overrides.
     */
    private static void loadSystemCnfiguration() {
        InputStream in = SystemConfig.class.getClassLoader().getResourceAsStream(RESOURCE_FILE);
        assertNotNull(in, "Resource '" + RESOURCE_FILE + "' not found in classpath!");

        try {
            Properties props = new Properties();
            props.load(in);

            for (Object key : props.keySet()) {
                String propertyname = (String) key;
                if (APPLN_URL.equals(propertyname)) {
                    setURLProperty(propertyname, getPropertyValue(props, propertyname));
                } else if (BROWSER.equals(propertyname)) {
                    setBrowserProperty(getPropertyValue(props, propertyname));
                }
            }

        } catch (IOException e) {
            String msg = String.format(
                    "Unable to load '%s' from classpath: %s",
                    RESOURCE_FILE,
                    e.getMessage()
            );
            fail(msg);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }
}

