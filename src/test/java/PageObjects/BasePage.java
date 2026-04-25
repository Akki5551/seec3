package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage 
{

	public WebDriver dr;
	public static WebDriverWait wait;
	
	public BasePage(WebDriver dr)
	{
		this.dr=dr;
		PageFactory.initElements(dr, this);
		this.wait=new WebDriverWait(dr,Duration.ofSeconds(10));
	}
}