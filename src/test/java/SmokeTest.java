import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import pageobject.OrderPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmokeTest {
    @Test
    public void smokeTest(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://qa-scooter.education-services.ru/");
        driver.quit();
    }

    @Test
    @DisplayName("Тест для проверки happy path")
    public void test(){
        WebDriver driver = new FirefoxDriver();
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        mainPage.openPage();
        mainPage.clickCookieAcceptButton();
        mainPage.clickOrderButtonTop();
        orderPage.executeFirstStep("Иван", "Петров", "ул. Ягодная, д.20", "Бульвар Рокоссовского", "+79101234567");
        orderPage.clickNext();
        orderPage.executeSecondStep("19-20-2026", "сутки", "черный", "");
        orderPage.clickOrderButton();
        orderPage.confirmOrder();
        orderPage.isSuccessOrderWindowVisible();
        assertEquals(true,orderPage.isSuccessOrderWindowVisible());
        driver.quit();
    }

}
