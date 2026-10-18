import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SmokeTest {
    @Test
    public void smokeTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-scooter.education-services.ru/");
        driver.quit();
    }

}
