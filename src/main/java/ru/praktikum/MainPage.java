package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPage {
    private WebDriver driver;
    //Локатор изображения "Заказ не найден"
    By imgNotFound = By.cssSelector("img[alt='Not found']");
    //Кнопка "Go" в хедере страницы (появляется по клику на кнопку "Статус заказа")
    By goButton = By.className("Header_Button__28dPO");
    //Поле ввода id заказа в хедере страницы (поле открывается по клику на кнопку "Статус заказа")
    By fieldInputIdHeader = By.className("Input_Input__1iN_Z");
    //Кнопка "Статус заказа" в хедере страницы
    By buttonStatusHeader = By.className("Header_Link__1TAG7");
    //Закрыть попап Куки
    By closePopupCoockie = By.id("rcc-confirm-button");

    //Локаторы элементов списка вопросов
    static final String[] questionArray = new String[]{
            "accordion__heading-0",
            "accordion__heading-1",
            "accordion__heading-2",
            "accordion__heading-3",
            "accordion__heading-4",
            "accordion__heading-5",
            "accordion__heading-6",
            "accordion__heading-7"
    };
    //Локаторы элементов списка ответов
    static final String[] answerArray = new String[]{
            "accordion__panel-0",
            "accordion__panel-1",
            "accordion__panel-2",
            "accordion__panel-3",
            "accordion__panel-4",
            "accordion__panel-5",
            "accordion__panel-6",
            "accordion__panel-7"
    };


    public MainPage(WebDriver driver){

        this.driver = driver;
    }
    //Загрузка главной страницы сайта
    public void openURL(WebDriver driver) {

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    //Скролл страницы до последнего элемента в блоке "Вопросы о важном"
    public void scrollPage(WebDriver driver) {
        WebElement lastQuestion = driver.findElement(By.id(questionArray[7]));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lastQuestion);

    }
    //Закрыть попап с куки
    public void closePopupCoockie(WebDriver driver) {
        driver.findElement(closePopupCoockie).click();
    }
    //Раскрыть список ответов в блоке "Вопросы о важном"
    public void clickQuestion(int questionNumber) {
        driver.findElement(By.id(questionArray[questionNumber])).click();
    }
    //Проверка текста ответа ОР и ФР
    public void checkTextAnswers (String expectedText, int answerNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id(questionArray[answerNumber])));
        String actualText = driver.findElement(By.id(answerArray[answerNumber])).getText();
        assertEquals(expectedText, actualText);
    }



    public void checkMassageErrorId(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(imgNotFound));

        assertTrue(driver.findElement(imgNotFound).isDisplayed());
    }

    public void clickGoInHeader(WebDriver driver) {
        driver.findElement(goButton).click();
    }

    public void inputOrderId(WebDriver driver) {

        driver.findElement(fieldInputIdHeader).sendKeys("147");
    }

    public void clickOnStatusHeader(WebDriver driver) {
        driver.findElement(buttonStatusHeader).click();
    }

}
