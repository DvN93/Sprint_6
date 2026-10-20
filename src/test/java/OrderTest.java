import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import pageobject.OrderPage;
import utils.BrowserFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    @BeforeEach
    public void setUp() {
        driver = BrowserFactory.create();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        mainPage.openPage();
        mainPage.clickCookieAcceptButton();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    static Stream<Arguments> orderData(){
        String actualDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        List<Arguments> result = new ArrayList<>();
        for (String button : new String[]{"top", "bottom"}) {
            result.add(Arguments.of(button, "Иван", "Петров", "Ягодная,1",
                    "Бульвар Рокоссовского", "+79011238967", actualDate, "сутки", "черный", ""));
            result.add(Arguments.of(button, "Мария", "Петрова", "Москва, Арбат 10",
                    "Черкизовская", "+79999999999", actualDate, "двое суток", "серый", "Позвоните за полчаса"));
        }
        return result.stream();
    }

    @ParameterizedTest(name = "Кнопка: {0}, клиент: {1}")
    @MethodSource("orderData")
    public void orderSamokat(String button, String name, String lastName, String address,
                             String metro, String phone, String actualDate, String rentTime,
                             String color, String comment) {
        if (button.equals("top")) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }
        orderPage.executeFirstStep(name, lastName, address, metro, phone);
        orderPage.clickNext();
        orderPage.executeSecondStep(actualDate, rentTime, color, comment);
        orderPage.clickOrderButton();
        orderPage.confirmOrder();
        assertTrue(orderPage.isSuccessOrderWindowVisible());
    }
}
