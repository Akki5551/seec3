package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReport implements ITestListener
{
    public ExtentSparkReporter sr;
    public ExtentReports er;
    public ExtentTest et;

    String reportname;

    public void onStart(ITestContext context)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH-mm-ss");
        Date d = new Date();
        String currentdatetimestamp = df.format(d);

        reportname = "Test-Report" + "_" + currentdatetimestamp + ".html";

        sr = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + reportname);

        sr.config().setDocumentTitle("OpenCart Automation Report");
        sr.config().setReportName("OpenCart Functional Report");
        sr.config().setTheme(Theme.DARK);

        er = new ExtentReports();
        er.attachReporter(sr);

        er.setSystemInfo("Application", "Opencart");
        er.setSystemInfo("module", "admin");
        er.setSystemInfo("Environment", "QA");
        er.setSystemInfo("username", System.getProperty("user.name"));

        String os = context.getCurrentXmlTest().getParameter("os");
        er.setSystemInfo("windows", os);

        String browser = context.getCurrentXmlTest().getParameter("browser");
        er.setSystemInfo("browser", browser);

        List<String> groups = context.getCurrentXmlTest().getIncludedGroups();

        if (!groups.isEmpty())
        {
            er.setSystemInfo("groups", groups.toString());
        }
    }

    public void onTestSuccess(ITestResult result)
    {
        et = er.createTest(result.getTestClass().getName());
        et.assignCategory(result.getMethod().getGroups());
        et.log(Status.PASS, result.getTestClass().getName() + " got successfully executed");
    }

    public void onTestFailure(ITestResult result)
    {
        et = er.createTest(result.getTestClass().getName());
        et.assignCategory(result.getMethod().getGroups());
        et.log(Status.FAIL, result.getTestClass().getName() + " test case failed");
        et.log(Status.INFO, "test case is failed because of : " + result.getThrowable().getMessage());

        try
        {
            String screenshotpathtaken = new BaseClass().takescreenshot(result.getName());
            et.addScreenCaptureFromPath(screenshotpathtaken);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result)
    {
        et = er.createTest(result.getTestClass().getName());
        et.assignCategory(result.getMethod().getGroups());
        et.log(Status.SKIP, result.getTestClass().getName() + " test case is skipped");
        et.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context)
    {
        er.flush();

        String reportpath = System.getProperty("user.dir") + "\\reports\\" + reportname;

        File extentreports = new File(reportpath);

        try
        {
            Desktop.getDesktop().browse(extentreports.toURI());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}