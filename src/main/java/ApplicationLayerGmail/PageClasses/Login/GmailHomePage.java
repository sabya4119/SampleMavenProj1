package ApplicationLayerGmail.PageClasses.Login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GmailHomePage {
    @FindBy(how = How.CSS, using = "ul.header__nav--ltr a[ga-event-action='sign in']")
    private WebElement signInLink;

    public WebElement getSignInLink() {
        return signInLink;
    }
}
