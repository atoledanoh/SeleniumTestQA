/**
 * Alejandro Toledano
 * 12-04-2021
 */

package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class testProject {

    public static void main(String[] args) {
        String driverpath = "C:\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverpath);
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://adactinhotelapp.com/index.php");

            logIn(driver, "NBCC2021QA", "56oPQBuLFldV");
            searchHotel(driver);
            selectHotel(driver, "radiobutton_0");
            bookHotel(driver);

            //wait for BookingConfirm.php to load
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            confirmOderNumber(driver);

        } catch (Exception e) {
            System.out.println(e);
        }
        driver.close();
    }

    /**
     * Fills the login form with the given credentials
     *
     * @param driver
     */
    public static void logIn(WebDriver driver, String username, String password) {
        //selecting elements
        WebElement webElementUsername = driver.findElement(By.id("username"));
        WebElement webElementPassword = driver.findElement(By.id("password"));
        WebElement login = driver.findElement(By.id("login"));

        //filling elements
        webElementUsername.sendKeys(username);
        webElementPassword.sendKeys(password);

        login.click();
    }

    /**
     * Fills the form to perform a search in the Select Hotel page
     * (http://adactinhotelapp.com/SearchHotel.php)
     *
     * @param driver
     */
    public static void searchHotel(WebDriver driver) {
        //selecting elements
        WebElement location = driver.findElement(By.id("location"));
        Select locationSelect = new Select(location);
        WebElement hotels = driver.findElement(By.id("hotels"));
        Select hotelsSelect = new Select(hotels);
        WebElement roomType = driver.findElement(By.id("room_type"));
        Select roomTypeSelect = new Select(roomType);
        WebElement numberOfRooms = driver.findElement(By.id("room_nos"));
        Select numberOfRoomsSelect = new Select(numberOfRooms);
        WebElement submit = driver.findElement(By.id("Submit"));

        //filling elements
        locationSelect.selectByValue("London");
        hotelsSelect.selectByValue("Hotel Sunshine");
        roomTypeSelect.selectByValue("Deluxe");
        numberOfRoomsSelect.selectByIndex(3);

        submit.click();
    }

    /**
     * Selects the hotel defined by the "id" parameter in the Select Hotel page
     * (http://adactinhotelapp.com/SelectHotel.php)
     *
     * @param driver
     * @param id
     */
    public static void selectHotel(WebDriver driver, String id) {
        //selecting elements
        WebElement radioButton = driver.findElement(By.id(id));
        WebElement aContinue = driver.findElement(By.id("continue"));

        radioButton.click();
        aContinue.click();
    }

    /**
     * Completes the booking form in the "Book A Hotel" page (http://adactinhotelapp.com/BookHotel.php)
     *
     * @param driver
     */
    public static void bookHotel(WebDriver driver) {
        //selecting elements
        WebElement firstName = driver.findElement(By.id("first_name"));
        WebElement lastName = driver.findElement(By.id("last_name"));
        WebElement billingAddress = driver.findElement(By.id("address"));
        WebElement creditCardNumber = driver.findElement(By.id("cc_num"));
        WebElement creditCardType = driver.findElement(By.id("cc_type"));
        Select creditCardTypeSelect = new Select(creditCardType);
        WebElement creditCardExpirationMonth = driver.findElement(By.id("cc_exp_month"));
        Select creditCardExpirationMonthSelect = new Select(creditCardExpirationMonth);
        WebElement creditCardExpirationYear = driver.findElement(By.id("cc_exp_year"));
        Select creditCardExpirationYearSelect = new Select(creditCardExpirationYear);
        WebElement creditCardCvv = driver.findElement(By.id("cc_cvv"));
        WebElement bookNow = driver.findElement(By.id("book_now"));

        //filling elements
        firstName.sendKeys("Alejandro");
        lastName.sendKeys("Toledano");
        billingAddress.sendKeys("107 Killam Drive");
        creditCardNumber.sendKeys("1234123412341234");
        creditCardTypeSelect.selectByValue("MAST");
        creditCardExpirationMonthSelect.selectByIndex(10);
        creditCardExpirationYearSelect.selectByValue("2022");
        creditCardCvv.sendKeys("666");

        bookNow.click();
    }

    /**
     * Attempts to retrieve the order number element.
     * when found prints its value, when not, an error message and the exception are shown
     *
     * @param driver
     */
    public static void confirmOderNumber(WebDriver driver) {
        try {
            WebElement orderNumber = driver.findElement(By.id("order_no"));
            System.out.println("Order Number found, order number is: " + orderNumber.getAttribute("value"));
        } catch (NotFoundException e) {
            System.out.println("Order number not found");
            System.out.println(e);
        }
    }
}
