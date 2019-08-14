package ApplicationLayerGmail.PageClasses.Login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GmailLoginPage {
    @FindBy(how = How.CSS, using = "[name='Email']")
    private WebElement userNameField;

    @FindBy(how = How.CSS, using = "[name='Passwd']")
    private WebElement passwordField;

    public WebElement getuserNameField() {
        return userNameField;
    }

    public WebElement getpasswordField() {
        return passwordField;
    }
}
