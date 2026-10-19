package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    /*
    Поля формы Для кого самокат
     */

    // Имя
    private By nameInput = By.xpath("//input[@placeholder='* Имя']");
    // Фамилия
    private By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    // Адрес
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Станция метро - поле ввода
    private By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    // Станция метро - выпадающий список
    private static final String METRO_OPTION = "//div[@class='select-search__select']//div[text()='%s']";
    // Телефон
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private By nextButton = By.xpath("//button[text()='Далее']");

    /*
    Поля формы Про аренду
     */

    // Когда привезти самокат
    private By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Срок аренды - поле ввода
    private By loanTimeInput = By.className("Dropdown-placeholder");
    // Срок аренды - выпадающий список
    private static final String LOAN_TIME_OPTION = "//div[contains(@class,'Dropdown-option') and text()='%s']";
    // Цвет самоката: черный жемчуг
    private By blackSamokat = By.id("black");
    // Цвет самоката: серая безысходность
    private By greySamokat = By.id("grey");
    // Комментарий для курьера
    private By commentForCourier = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка заказать
    private By orderFinalButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    // Кнопка "Да" для подтверждения
    private By confirmOrderButton = By.xpath("//button[text()='Да']");
    // Окно успешного заказа
    private By successOrderWindow = By.xpath("//div[contains(text(),'Заказ оформлен')]");
    // Кнопка Посмотреть статус
    private By checkStatus = By.xpath("//button[text()='Посмотреть статус']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait waitFor() {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void executeFirstStep(String name, String lastName, String address, String metro, String phone){
        waitFor().until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        selectMetroStation(metro);
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void selectMetroStation(String station){
        driver.findElement(metroInput).click();
        driver.findElement(metroInput).sendKeys(station);
        By option = By.xpath(String.format(METRO_OPTION, station));
        waitFor().until(ExpectedConditions.elementToBeClickable(option));
        driver.findElement(option).click();
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void executeSecondStep(String date, String rentTime, String color, String comment){
        waitFor().until(ExpectedConditions.visibilityOfElementLocated(deliveryDateInput));
        driver.findElement(deliveryDateInput).sendKeys(date, Keys.ENTER);
        selectRentTime(rentTime);
        selectColor(color);
        driver.findElement(commentForCourier).sendKeys(comment);
    }

    private void selectRentTime(String period) {
        driver.findElement(loanTimeInput).click();
        By option = By.xpath(String.format(LOAN_TIME_OPTION, period));
        waitFor().until(ExpectedConditions.elementToBeClickable(option));
        driver.findElement(option).click();
    }

    private void selectColor(String color) {
        switch (color) {
            case "черный":
                driver.findElement(blackSamokat).click();
                break;
            case "серый":
                driver.findElement(greySamokat).click();
                break;
            default:
                throw new IllegalArgumentException("Неизвестный цвет: " + color);
        }
    }

    public void clickOrderButton(){
        driver.findElement(orderFinalButton).click();
    }

    public void confirmOrder() {
        waitFor().until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        driver.findElement(confirmOrderButton).click();
    }

    public boolean isSuccessOrderWindowVisible() {
        try {
            waitFor().until(ExpectedConditions.visibilityOfElementLocated(successOrderWindow));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
