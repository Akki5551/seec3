package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends BasePage
{
    public Loginpage(WebDriver dr)
    {
        super(dr);
    }

    // locators

    @FindBy(xpath="//input[@id='input-email']") WebElement email;
    @FindBy(xpath="//input[@id='input-password']") WebElement password;
    @FindBy(xpath="//input[@value='Login']") WebElement login;


    // Action methods

    public void enteremail(String mail)
    {
        email.sendKeys(mail);
    }

    public void enterpassword(String pass)
    {
        password.sendKeys(pass);
    }

    public void clickonlogin()
    {
        login.click();
    }
}