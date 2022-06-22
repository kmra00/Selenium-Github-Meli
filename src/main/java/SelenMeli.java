import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Collectors;
import java.util.List;

public class SelenMeli {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mercadolibre.cl");
        driver.manage().window().maximize();

        WebElement searchInput = driver.findElement(By.cssSelector("#cb1-edit"));

        String fraseBusqueda = "xiaomi 11";
        searchInput.sendKeys(fraseBusqueda);
        searchInput.sendKeys(Keys.ENTER);
        //busqueda por xpath del h2
        List<String> itemActuales = driver.findElements(By.xpath("h2.ui-search-item__title"))
                .stream().map(el ->el.getText().toLowerCase()+ el.getAttribute("href").toLowerCase()).collect(Collectors.toList());

        List<String> itemEsperados = itemActuales.stream()
                .filter(item->item.contains(fraseBusqueda))
                .collect(Collectors.toList());

        Assert.assertEquals(itemEsperados,itemActuales);

        driver.quit();
    }
}


