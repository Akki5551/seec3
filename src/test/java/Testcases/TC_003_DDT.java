package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.Homepage;
import PageObjects.Loginpage;
import PageObjects.Myaccount;
import TestBase.BaseClass;
import Utilities.Dataprovider;

public class TC_003_DDT extends BaseClass
{

	
	@Test(dataProvider = "data", dataProviderClass = Dataprovider.class, groups = { "DataDriven" })
	public void DDTLogin(String em, String pd, String exp)
	{
	    logger.info("** TC003_ DDTLogin testcase execution started **");

	    try
	    {
	        Homepage hp = new Homepage(dr);
	        hp.clickmyaccount();
	        hp.clicklogin();

	        Loginpage lp = new Loginpage(dr);
	        lp.enteremail(em);
	        lp.enterpassword(pd);
	        lp.clickonlogin();

	        Myaccount ma = new Myaccount(dr);
	        boolean status = ma.myaccount_verification();

	        if (exp.equalsIgnoreCase("valid"))
	        {
	            if (status == true)
	            {
	                ma.clicklogout();
	                Assert.assertTrue(true);
	            }
	            else
	            {
	                Assert.assertTrue(false);
	            }
	        }

	        if (exp.equalsIgnoreCase("invalid"))
	        {
	            if (status == true)
	            {
	                ma.clicklogout();
	                Assert.assertTrue(false);
	            }
	            else
	            {
	                Assert.assertTrue(true);
	            }
	        }
	    }
	    catch (Exception e)
	    {
	        Assert.fail();
	    }
	}
}