package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public static WebDriver create() {
        String browser = System.getProperty("browser", "chrome");
        switch (browser.toLowerCase()) {
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
    }
}
