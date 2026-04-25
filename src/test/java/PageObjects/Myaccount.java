package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Myaccount extends BasePage
{
    public Myaccount(WebDriver dr)
    {
        super(dr);
    }

    // locators

    @FindBy(xpath="//h2[normalize-space()='My Account']") WebElement myaccount;
    @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement logout;


    // Action methods

    public boolean myaccount_verification()
    {
        try
        {
            return (myaccount.isDisplayed());
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public void clicklogout()
    {
        logout.click();
    }
}