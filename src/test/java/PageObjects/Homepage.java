package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Homepage extends BasePage
{
    public Homepage(WebDriver dr)
    {
        super(dr);
    }

    // locators
    @FindBy(xpath="//span[normalize-space()='My Account']") WebElement myaccount;

    @FindBy(xpath="//a[normalize-space()='Register']") WebElement register;

    @FindBy(xpath="//a[normalize-space()='Login']") WebElement login;


    //Action methods
    public void clickmyaccount()
    {
        wait.until(ExpectedConditions.visibilityOf(myaccount)).click();
    }

    public void clickregister()
    {
        register.click();
    }

    public void clicklogin()
    {
        login.click();
    }
}