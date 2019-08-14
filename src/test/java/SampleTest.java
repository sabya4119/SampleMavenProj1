import ApplicationLayerGmail.Controller;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class SampleTest extends Controller{

    @Test
    public void helloWorld() {
        System.out.println("Hello World!!!");

        Properties prop=new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("SampleMavenProj1.properties"));

        } catch (IOException e) {
            Assert.fail("Properties file not read");
        }
        System.out.println("Current user credentials : \nusername :" +  (prop.get("gmail.username") + "\nPassword :" + prop.get("gmail.password")));
    }
}
