package ru.praktikum.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.PageObject.MainPage;

import java.time.Duration;

public class questionArrayTest {
    private static WebDriver driver;

    @RunWith(Parameterized.class)
    public static class questionTest {
        private final int questionNumber;
        private final int answerNumber;
        private final String expectedText;


        public questionTest(int questionNumber, int answerNumber, String expectedText) {
            this.questionNumber = questionNumber;
            this.answerNumber = answerNumber;
            this.expectedText = expectedText;

        }

        //Тестовые данные
        @Parameterized.Parameters
        public static Object[][] personalInfo() {
            return new Object[][]{
                    {0, 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    {1, 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    {2, 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    {3, 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    {4, 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    {5, 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    {6, 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    {7, 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
                    //Дополнительный негативная проверка
                    {0, 0, "Ой-ой, текст то не совпадает"},
            };
        }


        @Before
        public void startFireFox() {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
        }


        @Test
        public void questionTestMainPage() {
            var questions = new MainPage(driver);

            questions.openURL(driver);
            questions.scrollPage(driver);
            questions.closePopupCoockie(driver);
            questions.clickQuestion(questionNumber);
            questions.checkTextAnswers(expectedText, answerNumber);
        }

        @After
        public void tearDown() {
            driver.quit();
        }
    }
}
