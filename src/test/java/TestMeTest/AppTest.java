package TestMeTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;


public class AppTest {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://timvroom.com/selenium/playground/");

        //1. Grab page title and place title text in answer slot #1

        driver.findElement(By.id("answer1")).sendKeys(driver.getTitle());

        //2. Fill out name section of form to be Kilgore Trout

        driver.findElement(By.id("name")).sendKeys("Kilgore Trout");

        //3. Set occupation on form to Sci-Fi Author

        driver.findElement(By.id("occupation")).sendKeys("Science Fiction Author");

        //4. Count number of blue_boxes on page after form and enter into answer box #4

        List<WebElement> count_blue_boxes = driver.findElements(By.className("bluebox"));
        driver.findElement(By.id("answer4")).sendKeys(String.valueOf(count_blue_boxes.size()));

        //5. Click link that says 'click me'

        driver.findElement(By.linkText("click me")).click();

        //6. Find red box on its page find class applied to it, and enter into answer box #6

        String element = driver.findElement(By.id("redbox")).getAttribute("class");
        driver.findElement(By.id("answer6")).sendKeys(element);

        //7. Run JavaScript function as: ran_this_js_function() from your Selenium script

        ((JavascriptExecutor)driver).executeScript("ran_this_js_function();");

        //8. Run JavaScript function as: got_return_from_js_function() from your Selenium script, take returned value and place it in answer slot #8

        Object something = ((JavascriptExecutor)driver).executeScript("return got_return_from_js_function();");
        String other = String.valueOf(something);
        driver.findElement(By.id("answer8")).sendKeys(other);

        //9. Mark radio button on form for written book? to Yes

        driver.findElement(By.name("wrotebook")).click();

        //10. Get the text from the Red Box and place it in answer slot #10

        String RedText = driver.findElement(By.id("redbox")).getText();
        driver.findElement(By.id("answer10")).sendKeys(RedText);

        //11. Which box is on top? orange or green -- place correct color in answer slot #11

        int orangebox = driver.findElement(By.id("orangebox")).getLocation().y;
        int greenbox = driver.findElement(By.id("greenbox")).getLocation().y;
        if(orangebox<greenbox)
            driver.findElement(By.id("answer11")).sendKeys("orange");
        else
            driver.findElement(By.id("answer11")).sendKeys("green");

        //12. Set browser width to 850 and height to 650

        Dimension dimension = new Dimension(850, 650);
        driver.manage().window().setSize(dimension);

        //13. Type into answer slot 13 yes or no depending on whether item by id of ishere is on the page

        int is = driver.findElements(By.id("ishere")).size();
        if(is == 0)
            driver.findElement(By.id("answer13")).sendKeys("no");
        else
            driver.findElement(By.id("answer13")).sendKeys("yes");

        //14. Type into answer slot 14 yes or no depending on whether item with id of purplebox is visible

        if(driver.findElement(By.id("purplebox")).isDisplayed())
            driver.findElement(By.id("answer14")).sendKeys("yes");
        else
            driver.findElement(By.id("answer14")).sendKeys("no");

        //15. Waiting game: click the link with link text of 'click then wait' a random wait will occur (up to 10 seconds) and then a new link will get added with link text of 'click after wait' - click this new link within 500 ms of it appearing to pass this test

        driver.findElement(By.linkText("click then wait")).click();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(100, MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("click after wait"))).click();

        //16. Click OK on the confirm after completing task 15

        Alert popup = driver.switchTo().alert();
        popup.accept();

        //17. Click the submit button on the form

        driver.findElement(By.id("submitbutton")).click();

        //Check Results
        driver.findElement(By.id("checkresults")).click();

    }
}