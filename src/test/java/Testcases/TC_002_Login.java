package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.Homepage;
import PageObjects.Loginpage;
import PageObjects.Myaccount;
import TestBase.BaseClass;

public class TC_002_Login extends BaseClass
{

	
	@Test(groups = { "Sanity", "Master" })
	public void login()
	{
	    logger.info("** TC002_Login testcase execution started **");

	    try
	    {
	        Homepage hp = new Homepage(dr);
	        hp.clickmyaccount();
	        hp.clicklogin();

	        Loginpage lp = new Loginpage(dr);
	        lp.enteremail(p.getProperty("email"));
	        lp.enterpassword(p.getProperty("password"));
	        lp.clickonlogin();

	        Myaccount ma = new Myaccount(dr);
	        boolean status = ma.myaccount_verification();

	        if (status == true)
	        {
	            Assert.assertTrue(status);
	        }
	        else
	        {
	            Assert.assertTrue(false);
	        }
	    }
	    catch (Exception e)
	    {
	        Assert.fail();
	    }

	    logger.info("** TC002_Login testcase execution completed **");
	}
}