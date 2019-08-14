package ApplicationLayerGmail;

import ApplicationLayerGmail.DataClasses.GmailCredentials;
import GenericLayer.Launchpad.Credentials;
import GenericLayer.Launchpad.SystemConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

import static GenericLayer.GenericLibrary.HAssert.assertNotNull;
import static GenericLayer.GenericLibrary.HAssert.fail;
import static com.google.common.base.Strings.isNullOrEmpty;

public class ApplicationConfig extends HashMap<String, Object> {
    private static final String GMAIL_USERNAME = "gmail.username";
    private static final String GMAIL_PASSWORD = "gmail.password";
    private static final String LOGIN_CREDENTIALS = "gmail.default.credentials";
    // The properties file that defines the application url and credentials.
    private static final String RESOURCE_FILE = "SampleMavenProj1.properties";
    private static ThreadLocal<ApplicationConfig> configThreadLocal = ThreadLocal.withInitial(()->new ApplicationConfig());
    private static final Logger logger = Logger.getLogger(ApplicationConfig.class.getName());

    private ApplicationConfig() {
        loadSystemCnfiguration();
    }

    @Override
    public String toString() {
        return configThreadLocal.get().get(LOGIN_CREDENTIALS).toString() + "\nRESOURCE_FILE: " + RESOURCE_FILE;
    }

    public static String getdefaultApplicationConfig() {
        return configThreadLocal.get().toString();
    }

    public static Credentials getDefaultCredentials() {
        return (GmailCredentials) configThreadLocal.get().get(LOGIN_CREDENTIALS);
    }

    private static void setDefaultCredentials(String username, String password) {
        GmailCredentials credentials = new GmailCredentials(username, password);
        configThreadLocal.get().put(LOGIN_CREDENTIALS, credentials);
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
                if (LOGIN_CREDENTIALS.equals(propertyname)) {
                    setDefaultCredentials(getPropertyValue(props, GMAIL_USERNAME), getPropertyValue(props, GMAIL_PASSWORD));
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
