package ru.praktikum.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;


public class AboutRent {
    WebDriver driver;

    //Кнопка "Заказать"
    By orderButton = By.cssSelector("button.Button_Middle__1CSJM:nth-child(2)");

    //Поле даты доставки
    By deliveryDate = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/div/div/input");
    By todayDelivery = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div[3]/div[6]");
    //Поле срок аренды
    By rentalPeriod = By.className("Dropdown-placeholder");
    By rentalPeriodDropdown = By.xpath(".//div[(@role ='option' and text()='двое суток')]");

    // Чекбокс выбора цвета самоката: черный, серый
    By blackColor = By.id("black");
    By greyColor = By.id("grey");

    //Поле комменатрий для курьера
    By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Всплывающее окно "Хотите оформить заказ?"
    By orderPopup = By.xpath("/html/body/div/div/div[2]/div[5]/div[1]");

    //Кнопка "Да" в попапе "Хотите оформить заказ?"
    By orderPopupButtonYes = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Modal__YZ-d3 > div.Order_Buttons__1xGrp > button:nth-child(2)");

    //Всплывающее окно "Заказ оформлен"
    By orderSuccess = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    //конструктор класса страницы "Про аренду"
    public void AboutRent(WebDriver driver) {
        this.driver = driver;
    }


    //Ввод данных в поле "Когда привезти самокат"
    public void inputDeliveryDate(WebDriver driver) {

        driver.findElement(deliveryDate).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(todayDelivery).click();
    }

    //Выбор срока аренды самоката
    public void rentalTime(WebDriver driver) {

        driver.findElement(rentalPeriod).click();
        driver.findElement(rentalPeriodDropdown).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Выбор цвета самоката "Чёрный жемчуг"
    public void chooseBlackColor(WebDriver driver) {

        driver.findElement(blackColor).click();

    }

    //Выбор цвета самоката "Серая безысходность"
    public void chooseGreyColor(WebDriver driver) {

        driver.findElement(greyColor).click();

    }

    //Ввод значений в поле "Комментарий для курьера"
    public void inputComment(WebDriver driver, String comment) {

        driver.findElement(commentField).sendKeys(comment);
    }

    //Клик по кнопке "Заказать"
    public void clickOrderButton(WebDriver driver) {

        driver.findElement(orderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderPopup));
        assertTrue(driver.findElement(orderPopup).isDisplayed());
    }

    //Ожидание появления попапа "Хотите офорить заказ?"
    public void checkOpenOrderPopup(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderPopup));

        assertTrue(driver.findElement(orderPopup).isDisplayed());
    }

    //Клик по кнопке "Да"
    public void clickNextButton(WebDriver driver) {

        driver.findElement(orderPopupButtonYes).click();

        assertTrue(driver.findElement(orderSuccess).isDisplayed());
    }

    //Ожидание появления попапа "Хотите офорить заказ?"
    public void checkOpenPopupOrderSuccess(WebDriver driver) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderSuccess));

        assertTrue(driver.findElement(orderSuccess).isDisplayed());

    }


    public boolean checkOrderSuccess(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(7));
        //.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderSuccess));

        return driver.findElement(orderSuccess).isDisplayed();
    }

}