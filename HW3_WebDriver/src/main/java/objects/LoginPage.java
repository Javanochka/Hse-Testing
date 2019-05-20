package objects;

import elements.ButtonElement;
import elements.TextElement;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    private final TextElement login;
    private final TextElement password;
    private final ButtonElement loginButton;

    public LoginPage(WebDriver driver) throws InterruptedException {
        driver.navigate().to("http://localhost:8080/login");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        login = new TextElement(driver, "l.L.login");
        password = new TextElement(driver, "l.L.password");
        loginButton = new ButtonElement(driver, "id_l.L.loginButton");
    }

    public void login(String login, String password) {
        this.login.setText(login);
        this.password.setText(password);
        loginButton.click();
    }
}