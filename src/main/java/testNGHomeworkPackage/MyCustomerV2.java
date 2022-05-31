package testNGHomeworkPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyCustomerV2 {
	// declaration of the webDriver
				WebDriver driver;
				String browser;
				String url;
				// Element List
				By USER_NAME_FIELD = By.id("username");
				By PASSWORD_FIELD = By.id("password");
				By SIGNIN_BUTTON_FIELD = By.name("login");
				//By DASHBOARD_HEADER_FIELD = By.xpath("//*[@id=\\\"page-wrapper\\\"]/div[2]/div/h2");
				By DASHBOARD_HEADER_FIELD = By.partialLinkText("Dashboard");
				By CUSTOMER_MENU_LOCATOR = By.xpath("//span[normalize-space()='Customers']");
				By ADD_CUSTOMER_MENU_LOCATOR = By.xpath("//a[normalize-space()='Add Customer']");
				By ADD_CONTACT_HEADER_LOCATOR = By.xpath("//h5[normalize-space()='Add Contact']");
				By FULL_NAME_LOCATOR = By.xpath("//input[@id='account']");
				By COMPANY_DROPDOWN_LOCATOR = By.xpath("//select[@id='cid']");
				By EMAIL_LOCATOR = By.xpath("//input[@id='email']");
				By PHONE_LOCATOR = By.xpath("//input[@id='phone']");
				By ADDRESS_LOCATOR = By.name("address");
				By CITY_LOCATOR = By.name("city");
				By STATE_LOCATOR = By.name("state");
				By ZIP_LOCATOR = By.id("zip");
				By COUNTRY_DROPDOWN_FIELD = By.xpath("//select[@id=\"country\"]");
				By SIGNIN_BUTTON = By.name("login");
				By GROUP_DROPDOWN_FIELD = By.xpath("//*[@id=\'group\']");
				By CURRENCYY_DROPDOWN_FIELD = By.xpath("//SELECT[@ID='currency']");
				// Test Data
				String userName = "demo@techfios.com";
				String password = "abc123";
				String firstName = "John";
				String lastName = "King";
				String fullname=firstName+" "+lastName;
				String company="Techfios";
				String email=fullname+"@techfios.com";
				String phone="2015479";
				String phoneGenerated = phone+generateRandomNo(999); 
				String address="8657 Road here st";
				String city="Dallas";
				String state="TX";
				String zip="75256";
				String country="United States";
				String currency="USD";
				String author = "Sidiki Camara";

				@BeforeClass
				public void readConfig() {

					// FileReader //Scanner //InputStream //BufferedReader

					try {
						InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
						Properties prop = new Properties();
						prop.load(input);
						browser = prop.getProperty("browser");
						System.out.println("Browser used: " + browser);
						url = prop.getProperty("url");
						System.out.println("Bowser used is"+browser);

					} catch (IOException e) {
						e.getStackTrace();
					}
				}
				@BeforeMethod
				public void init() {

					if (browser.equalsIgnoreCase("chrome")) {
						System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
						driver = new ChromeDriver();
					} else if (browser.equalsIgnoreCase("firefox")) {
						System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
						driver = new FirefoxDriver();
					}
					driver.manage().deleteAllCookies();
					//driver.get(url);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				}
				@Test
				public void myLogin() {
					System.out.println("test method"); 
					driver.get(url);
					driver.findElement(USER_NAME_FIELD).sendKeys(userName); // identify user name element & data insertion
					// identify password element & data insertion
					driver.findElement(PASSWORD_FIELD).sendKeys(password);
					// identify sign in element & click
					driver.findElement(SIGNIN_BUTTON).click();
					// assertion
														
					driver.findElement(By.partialLinkText("Customers")).click();
					driver.findElement(By.linkText("Add Customer")).click();

					driver.findElement(FULL_NAME_LOCATOR).sendKeys(fullname+ generateRandomNo(999));
					//By COMPANY_DROPDOWN_FIELD = By.xpath("//select[@id='cid']");
					selectFromDropdown(COMPANY_DROPDOWN_LOCATOR, "Techfios");
					driver.findElement(EMAIL_LOCATOR).sendKeys(generateRandomNo(99)+firstName+lastName);
					driver.findElement(PHONE_LOCATOR).sendKeys(phoneGenerated);
					driver.findElement(ADDRESS_LOCATOR).sendKeys(+generateRandomNo(99)+address);
					driver.findElement(CITY_LOCATOR).sendKeys(city);
					driver.findElement(STATE_LOCATOR).sendKeys(state);
					driver.findElement(ZIP_LOCATOR).sendKeys(zip+generateRandomNo(99));
					// Drop Down to handle list of countries
					selectFromDropdown(COUNTRY_DROPDOWN_FIELD, "Guinea");
					//driver.findElement(By.id("submit")).click();
					// Drop Down to handle list of currencies
					selectFromDropdown(CURRENCYY_DROPDOWN_FIELD, "USD");
					// Drop Down to handle list of countries
					// By GROUP_DROPDOWN_FIELD = By.xpath("//*[@id=\'group\']");
					selectFromDropdown(GROUP_DROPDOWN_FIELD, "SQL");
					driver.findElement(By.id("submit")).click();
					// driver.findElement(By.xpath("//*[@id=\\'submit\\']\"")).click();
					driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]/a/span[1]")).click();
					driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]/ul/li[1]/a")).click();
					driver.findElement(By.partialLinkText("Dashboard")).click();
					
					//waitForElement(driver, 5, DASHBOARD_HEADER_FIELD);
					driver.findElement(DASHBOARD_HEADER_FIELD).click();
					Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(), "Dashboard", "Wrong page!!!");
					driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[2]/a"));
					driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2")).click();
					driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2")).click();
					driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]")).click();
					driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[2]/a")).click();
					driver.findElement(By.xpath("//input[@id='foo_filter']")).sendKeys(phoneGenerated);
				}
				
				@AfterMethod
				public void tearDown() {
					// driver.close();
					// driver.quit();
				}
				@AfterSuite
				public void author() {
					System.out.println("Un big Thanks from the author "+author);
				}
				public void MyNegLoginPassword(){
					System.out.println("This is the negative test, it should not pass"); 
					driver.get(url);
					driver.findElement(USER_NAME_FIELD).sendKeys(userName); // identify user name element & data insertion
					// identify password element & data insertion
					driver.findElement(PASSWORD_FIELD).sendKeys(password+generateRandomNo(999));
					// identify sign in element & click
					driver.findElement(SIGNIN_BUTTON).click();
					
				}
				public void MyNegLoginUserName(){
					System.out.println("This is the negative test, it should not pass"); 
					driver.get(url);
					driver.findElement(USER_NAME_FIELD).sendKeys(userName+generateRandomNo(999)); // identify user name element & data insertion
					// identify password element & data insertion
					driver.findElement(PASSWORD_FIELD).sendKeys(password);
					// identify sign in element & click
					driver.findElement(SIGNIN_BUTTON).click();
					
				}
				//Explicit wait
				private void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
					//WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
					/*WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);

					WebDriverWait wait = new WebDriverWait(driver, i, by locator);
					wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
*/
}
				private void selectFromDropdown(By locator, String visibleText) {

					Select sel = new Select(driver.findElement(locator));
					sel.selectByVisibleText(visibleText);

				}
				private int generateRandomNo(int boundaryNo) {

					Random rnd = new Random();
					int generatedNo = rnd.nextInt(boundaryNo);
					return generatedNo;
				}
				
}
