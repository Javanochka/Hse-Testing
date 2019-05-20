package objects;

import elements.ButtonElement;
import elements.TextElement;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class NewIssuePage {

    private TextElement summary;
    private TextElement description;
    private ButtonElement create;

    public NewIssuePage(WebDriver driver) throws InterruptedException {
        driver.navigate().to("http://localhost:8080/dashboard#newissue=yes");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        summary = new TextElement(driver,"l.D.ni.ei.eit.summary");
        description = new TextElement(driver, "l.D.ni.ei.eit.description");
        create = new ButtonElement(driver, "id_l.D.ni.ei.submitButton_74_0");
    }

    public void createNewIssue(String summary, String description) {
        this.summary.setText(summary);
        this.description.setText(description);
        create.click();
    }
}
