package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Register extends BasePage
{
    public Register(WebDriver dr)
    {
        super(dr);
    }

    //locators

    @FindBy(xpath="//input[@id='input-firstname']") WebElement firstname;
    @FindBy(xpath="//input[@id='input-lastname']") WebElement lastname;
    @FindBy(xpath="//input[@id='input-email']") WebElement email;
    @FindBy(xpath="//input[@id='input-telephone']") WebElement telephone;
    @FindBy(xpath="//input[@id='input-password']") WebElement password;
    @FindBy(xpath="//input[@id='input-confirm']") WebElement confirmpwd;
    @FindBy(xpath="//input[@name='agree']") WebElement subscribe;
    @FindBy(xpath="//input[@name='agree']") WebElement agree;
    @FindBy(xpath="//input[@value='Continue']") WebElement continuebtn;
    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement message;

    //action methods

    public void setfirstname(String fn)
    {
        firstname.sendKeys(fn);
    }

    public void setlastname(String ln)
    {
        lastname.sendKeys(ln);
    }

    public void setemail(String mail)
    {
        email.sendKeys(mail);
    }

    public void settelephone(String phone)
    {
        telephone.sendKeys(phone);
    }

    public void setpassword(String pwd)
    {
        password.sendKeys(pwd);
    }

    public void setconfirmpwd(String pwd)
    {
        confirmpwd.sendKeys(pwd);
    }

    public void clicksubscribe()
    {
        subscribe.click();
    }

    public void clickagree()
    {
        agree.click();
    }

    public void clickcontinue()
    {
        continuebtn.click();
    }

    public String getmessage()
    {
        try
        {
            return message.getText();
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
}