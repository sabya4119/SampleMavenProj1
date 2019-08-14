package ApplicationLayerGmail.PageClasses.GmailDashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GmailDashboard {
    @FindBy(how = How.CSS, using = "div.gb_nc img.gb_pa")
    private WebElement dashboardLogo;

    @FindBy(how = How.XPATH, using = ".//div[@class='aic']//div[text()='Compose']")
    private WebElement composeButton;

    public WebElement getdashboardLogo() {
        return dashboardLogo;
    }
    public WebElement getComposeButton() {
        return composeButton;
    }

}
