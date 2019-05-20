package elements;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ButtonElement {
    private WebElement button;

    public ButtonElement(WebDriver driver, String id) {
        button = driver.findElement(By.id(id));
    }

    public void click() {
        button.click();
    }
}