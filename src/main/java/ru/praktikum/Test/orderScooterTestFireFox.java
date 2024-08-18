package ru.praktikum.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.AboutRent;
import ru.praktikum.PersonalData;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class orderScooterTestFireFox {
    private static WebDriver driver;

    @RunWith(Parameterized.class)
    public static class ScooterTest {
        private final String name;
        private final String surname;
        private final String address;
        private final String phoneNumber;
        private final String comment;

        public ScooterTest(String name, String surname, String address, String phoneNumber, String comment) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.comment = comment;
        }

        // Тестовые данные
        @Parameterized.Parameters
        public static Object[][] personalInfo() {
            return new Object[][]{
                    {"Пирожок", "Пирожкович", "Москва", "79858585855", "Не могу дождаться своего заказа в Хроме"},
                    {"Тест", "Тестович", "Королев", "79878963255", "Скорее, кататься"},
            };
        }


        @Before
        public void startFireFox() {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
        }


        @Test
        public void orderInputValidData() {

            //создаем объект класса страницы с персональныи данными заказчика
            var PersonalData = new PersonalData();

            //Открываем сайт
            PersonalData.openURL(driver);

            PersonalData.closePopupCoockie(driver);
            //клик по кнопке "Статус заказа" в хедере страницы
            PersonalData.clickOrderButtonHeader(driver);

            //Ожидание открытия страницы заказа
            PersonalData.checkOpenPersonalData(driver);

            //Ввод данных в поле "Имя"
            PersonalData.inputName(driver, name);

            //Ввод данных в поле "Фамилия"
            PersonalData.inputSurname(driver, surname);

            //Ввод данных в поле "Адрес"
            PersonalData.inputDeliveryAddress(driver, address);

            //Выбор станции метро
            //PersonalData.clickMetroStatio(driver);

            PersonalData.inputMetroStation(driver);


            //Ввод данных в поле "Телефон"
            PersonalData.inputPhoneNumber(driver, phoneNumber);

            //Клик по кнопке "Далее"
            PersonalData.clickNextButton(driver);

            //Загрузка страницы "Про аренду"
            PersonalData.checkOpenListAboutDelivery(driver);


            AboutRent AboutRentPage = new AboutRent();

            AboutRentPage.inputDeliveryDate(driver);
            AboutRentPage.rentalTime(driver);
            AboutRentPage.chooseBlackColor(driver);
            AboutRentPage.inputComment(driver, comment);
            AboutRentPage.clickOrderButton(driver);
            AboutRentPage.checkOpenOrderPopup(driver);
            AboutRentPage.clickNextButton(driver);

            assertEquals("Заказ не оформлен", true, AboutRentPage.checkOrderSuccess(driver));

        }

        @Test //Тест оформления заказа через кнопку в середине страницы
        public void orderMiddleButton() {

            //Создание объекта класса страницы с персональныи данными заказчика
            var PersonalData = new PersonalData();
            PersonalData.openURL(driver);
            PersonalData.closePopupCoockie(driver);

            //клик по кнопке "Статус заказа" в середине страницы и ожидание открытия страницы
            PersonalData.clickOrderButtonMiddle(driver);
            PersonalData.checkOpenPersonalData(driver);

            //Ввод данных в поля формы
            PersonalData.inputName(driver, name);
            PersonalData.inputSurname(driver, surname);
            PersonalData.inputDeliveryAddress(driver, address);
            PersonalData.inputMetroStation(driver);
            PersonalData.inputPhoneNumber(driver, phoneNumber);

            //Клик по кнопке "Далее" и загрузка страницы "Про аренду"
            PersonalData.clickNextButton(driver);
            PersonalData.checkOpenListAboutDelivery(driver);

            //Создание объекта класса страницы "Про аренду"
            AboutRent AboutRentPage = new AboutRent();
            //Ввод данных на странице "Про аренду"
            AboutRentPage.inputDeliveryDate(driver);
            AboutRentPage.rentalTime(driver);
            AboutRentPage.chooseBlackColor(driver);
            AboutRentPage.inputComment(driver, comment);
            AboutRentPage.clickOrderButton(driver);
            AboutRentPage.checkOpenOrderPopup(driver);
            AboutRentPage.clickNextButton(driver);

            assertEquals("Заказ не оформлен", true, AboutRentPage.checkOrderSuccess(driver));

        }

        @After
        public void tearDown() {
            driver.quit();
        }
    }
}
