package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextElement {
    private WebElement element;

    public TextElement(WebDriver driver, String name) {
        element = driver.findElement(By.name(name));
    }

    public void setText(String text) {
        element.sendKeys(text);
    }
}