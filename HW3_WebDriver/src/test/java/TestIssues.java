import objects.LoginPage;
import objects.NewIssuePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestIssues {
    private WebDriver driver;

    @Before
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        (new LoginPage(driver)).login("root", "itsasecret");
    }

    @After
    public void teardown() {
        driver.close();
    }
    
    private void createNewIssue(String summary, String description) throws InterruptedException {
        (new NewIssuePage(driver)).createNewIssue(summary, description);
    }

    private void assertCreated() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("http://localhost:8080/dashboard#newissue=yes")));

    }

    private void assertNotCreated() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        assertEquals("http://localhost:8080/dashboard#newissue=yes", driver.getCurrentUrl());
    }

    @Test
    public void addShortIssue() throws InterruptedException {
        createNewIssue("a", "b");
        assertCreated();
    }

    @Test
    public void emptyDescription() throws InterruptedException {
        createNewIssue("summary", "");
        assertCreated();
    }

    @Test
    public void sameSummaryAndDescription() throws InterruptedException {
        createNewIssue("meow", "meow");
        assertCreated();
    }

    @Test
    public void duplicateSummary() throws InterruptedException {
        createNewIssue("non-unique summary", "description1");
        assertCreated();
        createNewIssue("non-unique summary", "description2");
        assertCreated();
    }

    @Test
    public void duplicateDescription() throws InterruptedException {
        createNewIssue("summary1", "non-unique description");
        assertCreated();
        createNewIssue("summary2", "non-unique description");
        assertCreated();
    }

    @Test
    public void duplicateSummaryAndDescription() throws InterruptedException {
        createNewIssue("my summary", "my description");
        assertCreated();
        createNewIssue("my summary", "my description");
        assertCreated();
    }

    @Test
    public void uppercaseIssue() throws InterruptedException {
        createNewIssue("SUMMARY", "DESCRIPTION");
        assertCreated();
    }

    @Test
    public void spacesInSummaryIssue() throws InterruptedException {
        createNewIssue("Hello world", "Description");
        assertCreated();
    }

    @Test
    public void commasInSummaryIssue() throws InterruptedException {
        createNewIssue("Hello, world!!!", "Description");
        assertCreated();
    }

    @Test
    public void longSummaryAndDescription() throws InterruptedException {
        String longText = "A";
        for (int i = 0; i < 1000; i++) {
            longText += "A";
        }
        createNewIssue(longText, longText);
        assertCreated();
    }

    @Test
    public void longSummaryShortDescription() throws InterruptedException {
        String longText = "A";
        for (int i = 0; i < 1000; i++) {
            longText += "A";
        }
        createNewIssue(longText, "a");
        assertCreated();
    }

    @Test
    public void shortSummaryLongDescription() throws InterruptedException {
        String longText = "A";
        for (int i = 0; i < 1000; i++) {
            longText += "A";
        }
        createNewIssue("a", longText);
        assertCreated();
    }

    @Test
    public void emptySummaryNotAllowed() throws InterruptedException {
        createNewIssue("", "description");
        assertNotCreated();
    }

    @Test
    public void emptySummaryAndDescriptionNotAllowed() throws InterruptedException {
        createNewIssue("", "");
        assertNotCreated();
    }

}