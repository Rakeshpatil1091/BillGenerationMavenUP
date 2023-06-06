package PomPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CCBBillGenerationComplete {
public static void main(String[] args) throws InterruptedException, IOException {
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream("C:\\Users\\301584\\eclipse-workspace\\UatInstanceLoginMavenProject\\config.properties");
		prop.load(fis);
		
		System.setProperty("webdriver.chrome.driver", "D:\\data\\Software\\chromedriver_win32\\chromedriver.exe");
			
		WebDriver driver = new ChromeDriver();
			
		driver.manage().window().maximize();
		//driver.get("http://10.96.8.161:8500/ouaf/loginPage.jsp");
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//driver.findElement(By.id("userId")).sendKeys("RDUSER");
		//driver.findElement(By.id("password")).sendKeys("Ccb@#9671!");
		driver.findElement(By.id("userId")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("loginButton")).click();
		
		WebElement parentIframe=driver.findElement(By.xpath("//frame[@title='Main Frame']"));
		driver.switchTo().frame(parentIframe);
		
		WebElement childIframe=driver.findElement(By.xpath("//iframe[@id='tabPage']"));
		driver.switchTo().frame(childIframe);
		
		WebElement dropdown=driver.findElement(By.xpath("//select[@id='multiQueryZoneFilters1']"));
		Select se=new Select(dropdown);
		se.selectByVisibleText("Account ID");
		
		WebElement AccIDTextBox= driver.findElement(By.xpath("//input[@id='accountId1']"));
		
		AccIDTextBox.sendKeys(prop.getProperty("AccountID")); //Here pass Account ID
		
		WebElement SearchButton = driver.findElement(By.xpath(" //input[@id='anTLZ1Refresh']"));
		SearchButton.click();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(parentIframe);
		WebElement TabMenuIframe=driver.findElement(By.xpath("//iframe[@id='tabMenu']"));
		driver.switchTo().frame(TabMenuIframe);
		WebElement PremiseTree = driver.findElement(By.xpath("//td[@title='Premise Tree']"));
		PremiseTree.click();
		
		Thread.sleep(2000);
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame(parentIframe);
//		driver.switchTo().frame(childIframe);
//		
//		//WebElement PremiseTreeIframe=driver.findElement(By.xpath("//iframe[@id='premiseTreeIFrame']"));
//		driver.switchTo().frame(PremiseTreeIframe);
//		
//		WebElement SPDropdown = driver.findElement(By.xpath("//img[@id='c208504']"));
//		SPDropdown.click();
		
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB)
		.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		
		act.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_RIGHT)
		.sendKeys(Keys.ENTER).build().perform();
		
		Thread.sleep(3000);
        String parentid2 = driver.getWindowHandle();
		
		System.out.println("parentid" +parentid2);
		
		Set<String> allwindowid2 = driver.getWindowHandles();
		
		System.out.println("All window id"+ allwindowid2);
		
		for(String winid:allwindowid2)
		{
			// to switch to the child id and perform the actions
			if(!(winid.equalsIgnoreCase(parentid2)))
			{
				driver.switchTo().window(winid);
				WebElement DataIframe = driver.findElement(By.xpath("//iframe[@id='dataframe']"));
				driver.switchTo().frame(DataIframe);
				
				//Thread.sleep(1000);
				WebElement LastMeterRead = driver.findElement(By.xpath("(//span[@id='SEARCH_RESULTS:0$READ_DTTM'])[1]"));
				LastMeterRead.click();
			}
			System.out.println(winid);
		}
		
		Thread.sleep(1000);
		driver.switchTo().window(parentid2);
		driver.switchTo().defaultContent();
        driver.switchTo().frame(parentIframe);
        driver.switchTo().frame(childIframe);
		
        JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("window.scrollBy(0, 200)");
		
		Thread.sleep(2000);
		
		WebElement DataIframe=driver.findElement(By.xpath("//iframe[@id='dataframe']"));
		driver.switchTo().frame(DataIframe);
		
		WebElement RevHighLow = driver.findElement(By.xpath("//span[@id='L_REVIEW_HILO_SW']"));
		
		js.executeScript("arguments[0].scrollIntoView();",RevHighLow);
		
		System.out.println("in data frame");
		
		WebElement KWHRead1 = driver.findElement(By.xpath("//input[@id='REG_READ:0$REG_READING']"));
		
		WebElement KWRead2 = driver.findElement(By.xpath("//input[@id='REG_READ:1$REG_READING']"));
		
		System.out.println("read1");
		
		Thread.sleep(2000);
		
		String KWHOldRead =KWHRead1.getAttribute("value");
		String KWOldRead =KWRead2.getAttribute("value");
		
		System.out.println("KWHOldRead ="+ KWHOldRead);
		System.out.println("KWOldRead ="+ KWOldRead);
		
		js.executeScript("window.scrollBy(0,0)");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(parentIframe);
		
		driver.switchTo().frame(childIframe);
		
		WebElement MeterReadIDDropdown = driver.findElement(By.xpath("//img[@id='IM_MRmain_mrIdBtn']"));
		MeterReadIDDropdown.click();
		
		System.out.println("Meter read id dropdown click");
		
		act.sendKeys(Keys.ARROW_RIGHT).perform();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(parentIframe);
		
		//WebElement GoToMeterRead = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/ul[1]/li[1]/span[1]"));
		//Thread.sleep(1000);
		//System.out.println("go to meter read found");
		
		
		
		WebElement MRAdd = driver.findElement(By.xpath("//span[normalize-space()='Add']"));
		MRAdd.click();
		
		Thread.sleep(2000);
		
		driver.switchTo().frame(childIframe);
		
		WebElement MeterReadDate = driver.findElement(By.xpath("//input[@id='READ_DTTM_FWDTTM_P1']"));
		MeterReadDate.clear();
		MeterReadDate.sendKeys(prop.getProperty("Date"));
		
		System.out.println("meter read date passed");
		
        WebElement dataframe = driver.findElement(By.xpath("//iframe[@id='dataframe']"));
        driver.switchTo().frame(dataframe);
		
		System.out.println("in data frame");
		WebElement KWHReadNew = driver.findElement(By.xpath("//input[@id='REG_READ:0$REG_READING']"));
		System.out.println("KWHReadNew found");
		KWHReadNew.clear();
		 double KWHNewReadInt =Double.valueOf(KWHOldRead)+1000.000000;
		 int KWHNewReadInt1=(int)KWHNewReadInt;
		 
		//int KWHNewReadInt = Integer.parseInt(KWHOldRead);
		System.out.println("KWHNewReadInt1 ="+KWHNewReadInt1);
		
		KWHReadNew.sendKeys(String.valueOf(KWHNewReadInt1));
		//KWHReadNew.sendKeys(KWHOldRead+1000);
		
		WebElement KWReadNew = driver.findElement(By.xpath("//input[@id='REG_READ:1$REG_READING']"));
		KWReadNew.clear();
		
		double KWNewReadInt =Double.valueOf(KWOldRead)+2.0000;
		int KWNewReadInt1=(int)KWNewReadInt;
		//int KWNewReadInt = Integer.parseInt(KWOldRead);
		System.out.println("KWNewReadInt1 ="+KWNewReadInt1);
		
		KWReadNew.sendKeys(String.valueOf(KWNewReadInt1));
		//KWReadNew.sendKeys(KWOldRead+2);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(parentIframe);
		
		System.out.println("in parent Iframe");
		
		WebElement Save = driver.findElement(By.xpath("//input[@id='IM_SAVE']"));
		Save.click();
		
		WebElement DashBoard=driver.findElement(By.xpath("//iframe[@id='dashboard']"));
		driver.switchTo().frame(DashBoard);
		
		WebElement CurrentContextAccDropDown = driver.findElement(By.xpath("//img[@title='Show Account Context Menu']"));
		CurrentContextAccDropDown.click();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(parentIframe);
		
		System.out.println("in main frame for bill +");
		WebElement GoToBill = driver.findElement(By.xpath("//span[normalize-space()='Go To Bill']"));
		GoToBill.click();
		
		act.sendKeys(Keys.ARROW_RIGHT).perform();
		
		WebElement BillAdd = driver.findElement(By.xpath("//span[normalize-space()='Add']"));
		BillAdd.click();
		
		Thread.sleep(1000);
		
		driver.switchTo().frame(childIframe);
		
		System.out.println("in child iframe");
		
		WebElement Generate = driver.findElement(By.xpath("//input[@id='ACTION_GENERATE_SW']"));
		System.out.println("generate found");
		Generate.click();
		Thread.sleep(1000);
		
		String parentid = driver.getWindowHandle();
		
		System.out.println("parentid" +parentid);
		
		Set<String> allwindowid = driver.getWindowHandles();
		
		System.out.println("All window id"+ allwindowid);
		
		for(String winid:allwindowid)
		{
			// to switch to the child id and perform the actions
			if(!(winid.equalsIgnoreCase(parentid)))
			{
				driver.switchTo().window(winid);
				WebElement CutOffDate = driver.findElement(By.xpath("//input[@id='CUTOFF_DT']"));
				CutOffDate.clear();
				CutOffDate.sendKeys(prop.getProperty("Date"));
				
				WebElement AccountingDate = driver.findElement(By.xpath("//input[@id='ACCOUNTING_DT']"));
				AccountingDate.clear();
				AccountingDate.sendKeys(prop.getProperty("Date"));
				
				WebElement calculate = driver.findElement(By.xpath("//input[@id='ACTION_GENERATE_SW']"));
				calculate.click();
			}
			System.out.println(winid);
		}
		Thread.sleep(2000);
		driver.switchTo().window(parentid);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(parentIframe);
		driver.switchTo().frame(childIframe);
		
		System.out.println("want here in child frame");
		
		js.executeScript("window.scrollBy(0, 300)");
		
		Thread.sleep(1000);
		
		WebElement Freeze = driver.findElement(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']"));
		Freeze.click();
		Thread.sleep(3000);
       String parentid3 = driver.getWindowHandle();
		
		System.out.println("parentid" +parentid3);
		
		Set<String> allwindowid3 = driver.getWindowHandles();
		
		System.out.println("All window id"+ allwindowid3);
		
		for(String winid3:allwindowid3)
		{
			// to switch to the child id and perform the actions
			if(!(winid3.equalsIgnoreCase(parentid3)))
			{
				driver.switchTo().window(winid3);
				WebElement BillID = driver.findElement(By.xpath("//input[@id='OBC_BILL_DT']"));
				BillID.clear();
				BillID.sendKeys(prop.getProperty("Date"));
				Thread.sleep(1000);
				WebElement complete = driver.findElement(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']"));
				complete.click();
			}
			System.out.println(winid3);
		}

}
}
