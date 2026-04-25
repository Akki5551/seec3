package Testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageObjects.Homepage;
import PageObjects.Register;
import TestBase.BaseClass;

public class TC_001_Register extends BaseClass
{
    public SoftAssert sa;

    @Test(groups = { "Regression", "Master" })
    public void register() throws InterruptedException
    {
        

        try
        {
            Homepage hp = new Homepage(dr());
            hp.clickmyaccount();
            Thread.sleep(1000);
            hp.clickregister();
            Thread.sleep(1000);

            Register rg = new Register(dr());
            rg.setfirstname(Randomstring().toUpperCase());
            rg.setlastname(Randomstring().toLowerCase());
            rg.setemail(Randomstring() + "@gmail.com");
            rg.settelephone(RandomNum());

            String pwd = RandomAlphanum();
            rg.setpassword(pwd);
            rg.setconfirmpwd(pwd);
            rg.clicksubscribe();
            Thread.sleep(1000);
//            rg.clickagree();
            Thread.sleep(1000);
            rg.clickcontinue();

            String message = rg.getmessage();

            sa = new SoftAssert();

            if (message.equals("Your Account Has Been Created!"))
            {
                sa.assertTrue(true);
            }
            else
            {
                sa.assertTrue(false);
            }

           
        }
        catch (Exception e)
        {
            sa.assertTrue(false);
        }
        finally
        {
        	 sa.assertAll();
        }
    }
}