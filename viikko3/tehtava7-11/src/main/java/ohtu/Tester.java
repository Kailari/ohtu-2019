package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        //testLoginWithValidUsernameAndWrongPasswordFails(driver);
        //sleep(3);

        testNewUserIsCreatedWhenUsernameAndPasswordAreValid(driver);
        sleep(3);

        testLoggingOutAfterCreatingUser(driver);
        sleep(3);

        driver.quit();
    }

    private static void testLoginWithValidUsernameAndWrongPasswordFails(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private static void testNewUserIsCreatedWhenUsernameAndPasswordAreValid(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        element = driver.findElement(By.name("username"));
        element.sendKeys("jaskajokunen");
        element = driver.findElement(By.name("password"));
        element.sendKeys("kissatkoiratkirahvit123");

        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private static void testLoggingOutAfterCreatingUser(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        element = driver.findElement(By.linkText("logout"));
        element.click();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
