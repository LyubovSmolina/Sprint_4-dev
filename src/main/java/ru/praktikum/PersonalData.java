package ru.praktikum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;


public class PersonalData {
    private WebDriver driver;
    //Локаторы элементов на 1-й странице заказа - ввод персональных данных
    By inputName = By.xpath(".//input[@placeholder='* Имя']");
    By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    By inputDeliveryAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    By inputPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    By inputStationMetro = By.className("select-search__input");
    By stationMetro = By.xpath(".//button[@value='3']");
    By nextButton = By.cssSelector(".Button_Middle__1CSJM");
    By listAboutDilivery = By.className("Order_Header__BZXOb");
    //Локатор страницы "Для кого самокат"
    By listPersonalData = By.className("Order_Header__BZXOb");

    //Закрытие попапа и кнопки оформления заказа (в хедере и посередине страницы)
    By closePopupCoockie = By.id("rcc-confirm-button");
    By orderButtonHeader = By.xpath(".//button[@class='Button_Button__ra12g']");
    By orderButtonMiddle = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public void PersonalData(WebDriver driver){

        this.driver = driver;
    }
    //Открыть сайт
    public PersonalData openURL(WebDriver driver) {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    //Закрыть попап с куки
    public PersonalData closePopupCoockie(WebDriver driver) {
        driver.findElement(closePopupCoockie).click();
        return this;
    }

    //Клик по кнопке заказа в хедере страницы
    public void clickOrderButtonHeader(WebDriver driver) {

        driver.findElement(orderButtonHeader).click();
    }
    public void clickOrderButtonMiddle(WebDriver driver) {

        driver.findElement(orderButtonMiddle).click();
    }

    //Открытие страницы с персональными данными заказчика
    public void checkOpenPersonalData(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listPersonalData));

        assertTrue(driver.findElement(listPersonalData).isDisplayed());
    }

    //Ввод данных в поле "Имя"
    public void inputName(WebDriver driver, String name) {

        driver.findElement(inputName).sendKeys(name);
    }

    //Ввод данных в поле "Фамилия"
    public void inputSurname(WebDriver driver, String surname) {

        driver.findElement(inputSurname).sendKeys(surname);
    }

    //Ввод данных в поле "Адрес"
    public void inputDeliveryAddress(WebDriver driver, String address) {

        driver.findElement(inputDeliveryAddress).sendKeys(address);
    }

    //Выбор станции метро
    public void inputMetroStation(WebDriver driver) {
        driver.findElement(inputStationMetro).click();

        driver.findElement(stationMetro).click();

    }

    //Ввод данных в поле "Телефон"
    public void inputPhoneNumber(WebDriver driver, String numberPhone) {

        driver.findElement(inputPhoneNumber).sendKeys(numberPhone);
    }
    //Клик по кнопке "Далее"
    public void clickNextButton(WebDriver driver) {

        driver.findElement(nextButton).click();
    }
    //Переход на следующую страницу
    public void checkOpenListAboutDelivery(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listAboutDilivery));

        assertTrue(driver.findElement(listAboutDilivery).isDisplayed());
    }

}