package ApplicationLayerGmail.PageClasses;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class GenericPages {
    @FindBy(how = How.CSS, using = "div[role='dialog']")
    private List<WebElement> dialogBoxes;

    @FindBy(how = How.CSS, using = "div.aoI")
    private List<WebElement> dialogWindows;

    @FindBy(how = How.NAME, using = "div.ah.aiv.aJS[role='listbox']")
    private WebElement recepientList;

    @FindBy(how = How.NAME, using = ".//textarea[@name='to']/preceding-sibling::div[@class='vR']/span")
    private List<WebElement> selectedReciepients;


    public List<WebElement> getSelectedReciepients() {
        return selectedReciepients;
    }

    public List<WebElement> getDialogBoxes(){
        return dialogBoxes;
    }

    public WebElement getDialogWindow(String headerText) {
        for (WebElement dialogWindow : dialogWindows) {
            if (dialogWindow.getAttribute("aria-label").equals(headerText)) return dialogWindow;
        }
        throw new NoSuchElementException("Element not Found :" + headerText + " Dialog window");
    }

    public WebElement getRecepientList() {
        return recepientList;
    }
}
