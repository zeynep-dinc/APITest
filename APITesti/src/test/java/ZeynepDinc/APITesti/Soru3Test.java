package ZeynepDinc.APITesti;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Soru3Test {
	WebDriver driver;

	@BeforeTest
	void MyBeforeTest() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// örnek url adresine gidilir
		driver.get("http://generator.swagger.io/");
		// Adres kontrol edilir
		Assert.assertEquals(driver.getTitle(), "Swagger UI");
		// Sayfanın en üstte olması sağlanır
	}

	@Test
	public void Test()
			throws InterruptedException, NoSuchElementException, InvalidSelectorException, NullPointerException {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_UP).build().perform();
		Thread.sleep(5000);

		// Client Post sekmesi açılır
		driver.findElement(By.className("opblock-summary-post")).click();
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(5000);
		System.out.println("Client Post sekmesi açılır");

		// Try out butonuna basılır
		driver.findElement(By.className("try-out")).click();
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(3000);
		System.out.println("Try-Out butonuna basıldı");

		// Execute butonuna basılır
		driver.findElement(By.className("execute-wrapper")).click();
		Thread.sleep(5000);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(5000);
		System.out.println("post işlemi çalıştırıldı");

		// Parola kopyalanır
		String pass = driver.findElement(By.xpath(
				"//*[@id=\"operations-clients-generateClient\"]/div[2]/div/div[4]/div[2]/div/div/table/tbody/tr/td[2]/div[1]/div/pre/span[6]"))
				.getText();
		System.out.println("pass = " + pass.substring(1, pass.length() - 1));

		// Sayfa en üste taşınır
		action.sendKeys(Keys.PAGE_UP).build().perform();
		action.sendKeys(Keys.PAGE_UP).build().perform();
		action.sendKeys(Keys.PAGE_UP).build().perform();
		Thread.sleep(5000);

		// Post sekmesi kapatılır
		driver.findElement(By.className("opblock-summary-post")).click();
		Thread.sleep(5000);

		// Get Download FileId sekmesi açılır
		driver.findElement(By.id("operations-clients-downloadFile")).click();
		System.out.println("Get Download sekmesi açılır");
		Thread.sleep(5000);

		// try out tuşuna basılır
		driver.findElement(By.className("try-out")).click();
		Thread.sleep(5000);
		System.out.println("Try-Out tuşuna basılır");
		action.sendKeys(Keys.PAGE_DOWN).build().perform();

		// file input değeri girilir
		driver.findElement(By.xpath(
				"//*[@id=\"operations-clients-downloadFile\"]/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[2]/input"))
				.click();
		driver.findElement(By.xpath(
				"//*[@id=\"operations-clients-downloadFile\"]/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[2]/input"))
				.sendKeys(pass.substring(1, pass.length() - 1));
		Thread.sleep(5000);
		System.out.println("Dosya kodu girilir");

		// Execute tuşuna basılır
		driver.findElement(By.className("execute-wrapper")).click();
		Thread.sleep(5000);
		System.out.println("execute tuşuna basılır");
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		String code = driver.findElement(By.className("response-col_description__inner")).getText();

		// Doğrulama 200 kodunun açılımıyla yapılır
		Assert.assertEquals(code, "successful operation");
		System.out.println("Doğrulama başarılı");
	}

	@AfterTest
	void MyAfterTest() {
		System.out.println("Test bitirilir");
		driver.close();
		
	}
}
