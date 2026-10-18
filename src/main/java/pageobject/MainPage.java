package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private final WebDriver driver;
    private static final String QUESTION_ID = "accordion__heading-%d";
    private static final String ANSWER_ID = "accordion__panel-%d";

    // Логотип Яндекса
    private By yandexLogo = By.className("Header_LogoYandex__3TSOI");
    // Логотип Самокат
    private By samokatLogo = By.className("Header_LogoScooter__3lsAR");
    // Кнопка "Заказать" вверху страницы
    private By orderButtonTop = By.xpath(".//button[@class = 'Button_Button__ra12g']");
     // Кнопка "Статус заказа"
    private By orderStatusButton = By.className("Header_Link__1TAG7");
     // Кнопка "Заказать" внизу страницы
    private By orderButtonBottom = By.xpath("//button[contains(@class,'Button_UltraBig__UU3Lp')]");

    /*
    Стрелочки Вопросы о важном
    Индекс соответствует номеру в локаторе accordion__heading-x
     */

    // 0 Сколько это стоит? И как оплатить?
     // 1 Хочу сразу несколько самокатов! Так можно?
    // 2 Как рассчитывается время аренды?
    // 3 Можно ли заказать самокат прямо на сегодня?
    // 4 Можно ли продлить заказ или вернуть самокат раньше?
    // 5 Вы привозите зарядку вместе с самокатом?
    // 6 Можно ли отменить заказ?
    // 7 Я жизу за МКАДом, привезёте?

    /*
    Ответы на вопросы о важном
    Индекс соответствует номеру в локаторе accordion__panel-x
     */

    // 0 Ответ на вопрос про оплату
    // 1 Ответ на вопрос о количестве самокатов
    // 2 Ответ на вопрос о времени аренды
    // 3 Ответ на вопрос о возможности заказать самокат на сегодня
    // 4 Ответ на вопрос о продлении заказа
    // 5 Ответ на вопрос о зарядке
    // 6 Ответ на вопрос об отмене заказа
    // 7 Ответ на вопрос о доставке за МКАД

    //кнопка принятия куки
    private By cookieAcceptButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage(){
        driver.get(URL);
    }

    public void clickQuestion(int index) {
        WebElement question = driver.findElement(By.id(String.format(QUESTION_ID, index)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        question.click();
    }

    public String getAnswerText(int index) {
        By answer = By.id(String.format(ANSWER_ID, index));
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answer));
        return driver.findElement(answer).getText();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void clickSamokatLogo() {
        driver.findElement(samokatLogo).click();
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    public void clickOrderButtonBottom() {
        WebElement button = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    public void clickCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).click();
    }
}
