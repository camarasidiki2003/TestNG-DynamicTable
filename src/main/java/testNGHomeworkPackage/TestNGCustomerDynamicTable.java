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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class TestNGCustomerDynamicTable {
	// declaration of the webDriver
	WebDriver driver;
	String browser;
	String url;
	// Element List
	By USER_NAME_FIELD = By.id("username");
	By PASSWORD_FIELD = By.id("password");
	By SIGNIN_BUTTON_FIELD = By.name("login");
	By DASHBOARD_HEADER_FIELD = By.xpath("//*[@id=\\\"page-wrapper\\\"]/div[2]/div/h2");
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
	By SEARCH_BAR_ON_LIST_CUSTOMER = By.xpath("//*[@id=\"foo_filter\"]");
	// Test Data
	String userName = "demo@techfios.com";
	String password = "abc123";
	String fullname = "My name"+  generateRandomNo(999);
	String company = "Techfios";
	String email = "myenmail@techfios.com";
	String phone = "2145368579";
	String address = "8657 Road here st";
	String city = "Dallas";
	String state = "TX";
	String zip = "75256";
	String country = "United States";
	String currency = "USD";
	// Paths definitions for the dynamic table
	String before_xpath = "//tbody/tr[";
	String after_xpath = "]/td[3]";
	String after_xpath_profile = "]/td[3]/following-sibling::td[4]/a[1]";
			
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
			System.out.println("Bowser used is" + browser);

		} catch (IOException e) {
			e.getStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
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
		driver.get(url);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void myLogin() {
		System.out.println("test method");
		// identify user name element & data insertion
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		// identify password element & data insertion
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		// identify sign in element & click
		driver.findElement(SIGNIN_BUTTON).click();
		// assertion

		// Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(),
		// "Dashboard", "Wrong page!!!");
	}

	@Test(priority = 2)
	public void d() {
		myLogin();
		System.out.println("This is the account opening part");
		// clicking on Bank & Cash
		driver.findElement(By.partialLinkText("Bank & Cash")).click();
		driver.findElement(By.partialLinkText("New Account")).click();
		// Thread.sleep(5000);
		// file upload
		// identify the elemnt, use sendkeys as long as it is tag input
		// driver.findElement("")).sendkeys;
		// any other ytage you will have to use robot class

		// filing out the new account information
		System.out.println("This is ti fill out the account information");
		driver.findElement(By.id("account")).sendKeys("Saving Account");
		driver.findElement(By.id("description")).sendKeys("I am keeeping my saving here");
		driver.findElement(By.id("balance")).sendKeys("$129,000");
		driver.findElement(By.id("account_number")).sendKeys("214723387300");
		driver.findElement(By.id("contact_person")).sendKeys("Bintu C");
		driver.findElement(By.id("ib_url")).sendKeys("www.techfios.com/billing/?ng=admin/");
		// Thread.sleep(3200);
		driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
		// driver.findElement(By.partialLinkText("alert alert-success fade in"));
		// WebElement expected=
		// driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div[1]"));
		// String actualUrl="";
		// Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getTagName(),
		// "Dashboard", "wrong page");

		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]/a/span[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]/ul/li[1]/a")).click();
		driver.findElement(By.partialLinkText("Dashboard")).click();

		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]")).click();
		driver.findElement(By.linkText("Add Customer")).click();

		driver.findElement(FULL_NAME_LOCATOR).sendKeys(fullname);

		By COmpany_DROPDOWN_FIELD = By.xpath("//select[@id='cid']");
		Select sel = new Select(driver.findElement(COMPANY_DROPDOWN_LOCATOR));
		sel.selectByVisibleText("Amazon");
		sel.getOptions();// homework
		driver.findElement(EMAIL_LOCATOR).sendKeys(email);
		driver.findElement(PHONE_LOCATOR).sendKeys(phone);
		driver.findElement(ADDRESS_LOCATOR).sendKeys(address);
		driver.findElement(CITY_LOCATOR).sendKeys(city);
		driver.findElement(STATE_LOCATOR).sendKeys(state);
		driver.findElement(ZIP_LOCATOR).sendKeys(zip);
		// Drop Down to handle list of countries
		Select sel2 = new Select(driver.findElement(COUNTRY_DROPDOWN_FIELD));
		sel2.selectByVisibleText(country);
		// driver.findElement(By.xpath("//ul[@class='select2-selection__rendered']")).sendKeys("QA
		// training");
		driver.findElement(By.id("submit")).click();
		// Drop Down to handle list of currencies
		Select sel3 = new Select(driver.findElement(CURRENCYY_DROPDOWN_FIELD));
		sel3.selectByVisibleText("USD");
		// Drop Down to handle list of countries
		// By GROUP_DROPDOWN_FIELD = By.xpath("//*[@id=\'group\']");
		Select sel4 = new Select(driver.findElement(GROUP_DROPDOWN_FIELD));
		sel4.selectByVisibleText("SQL");
		// driver.findElement(By.xpath("//*[@id=\\'submit\\']\"")).click();
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]/a/span[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]/ul/li[1]/a")).click();
		driver.findElement(By.partialLinkText("Dashboard")).click();
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[2]/a"));
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2")).click();
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2")).click();
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[2]/a")).click();
		// driver.findElement(By.xpath("//input[@id='foo_filter']")).sendKeys("2140002010");
		// dynamanic table handling how to find an element in
		// Let insert the search bare
		String insertedName;
		String enteredName = fullname;
		insertedName = enteredName;
		driver.findElement(SEARCH_BAR_ON_LIST_CUSTOMER).sendKeys(insertedName);
		// Let verify the entered name for usinf for loop
		for (int i = 1; i <= 5; i++) {

			enteredName = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			if (enteredName.contains(insertedName)) {
				System.out.println("Entered name exist!!!");
				driver.findElement(By.xpath(before_xpath + i + after_xpath_profile)).click();
			}
			break;
		}

	}

	private int generateRandomNo(int boundaryNo) {

		Random rnd = new Random();
		int generatedNo = rnd.nextInt(boundaryNo);
		return generatedNo;

	}

}
