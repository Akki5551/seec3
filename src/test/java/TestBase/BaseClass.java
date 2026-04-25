package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass
{

    public Logger logger;
    public static WebDriver dr;
    public Properties p;
    
    private static ThreadLocal<WebDriver>driver =new ThreadLocal();
    
    public static WebDriver dr()
    {
    	return driver.get();
    }

    @Parameters({"os","browser"})
    @BeforeClass(groups={ "Sanity", "Regression", "Master", "DataDriven" })
    public void setup(String os,String br) throws InterruptedException, IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Config.properties");

        p = new Properties();
        p.load(fis);

     logger=LogManager.getLogger(this.getClass());

     
     if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
     {
    	 DesiredCapabilities cap=new DesiredCapabilities();
    	 
    	 if(os.equalsIgnoreCase("windows"))
    	 {
    		 cap.setPlatform(Platform.WIN10);
    	 }
    	 else if(os.equalsIgnoreCase("Linux"))
    	 {
    		 cap.setPlatform(Platform.LINUX);
    	 }
    	 else if(os.equalsIgnoreCase("MAC"))
    	 {
    		 cap.setPlatform(Platform.MAC);
    	 }
    	 else
    	 {
    		 System.out.println("invalid os");
    		 return;
    	 }
    	 
    	 switch(br.toLowerCase())
    	 {
    	 case "chrome" : cap.setBrowserName("chrome");break;
    	 case "edge" : cap.setBrowserName("MicrosoftEdge");break;
    	 case "firefox":cap.setBrowserName("firefox");break;
    	 default : System.out.println("invalid browser");return;
    	 }
    	 
    	 dr=new RemoteWebDriver(new URL("http://192.168.147.1:4444/wd/hub"),cap);
     }
     
     
     
     
     if(p.getProperty("execution_env").equalsIgnoreCase("local"))
     {
     
        switch (br.toLowerCase())
        {
        case "chrome": dr = new ChromeDriver(); break;
        case "edge": dr = new EdgeDriver(); break;
        default:System.out.println("invalid");return;
        }
     }

     
     driver.set(dr);     
     
     
     
     dr().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     dr().manage().deleteAllCookies();
     dr().get(p.getProperty("url"));
     dr() .manage().window().maximize();
     Thread.sleep(1000);
    }
    

    public String Randomstring() 
    {
        String alpha = RandomStringUtils.randomAlphabetic(5);
        return alpha;
    }

    
    public String RandomNum()
    {
        String num = RandomStringUtils.randomNumeric(6);
        return num;
    }

    public String RandomAlphanum()
    {
        String alpha = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(3);
        return (alpha + num);
    }

    public String takescreenshot(String tname)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH-mm-ss");
        Date dd = new Date();
        String timestamp = df.format(dd);

        TakesScreenshot ts = (TakesScreenshot) dr();
        File f1 = ts.getScreenshotAs(OutputType.FILE);

        String screenshotpath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
        File f2 = new File(screenshotpath);

        f1.renameTo(f2);
        return screenshotpath;
    }
}