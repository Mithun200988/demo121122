package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility {
	
	public ExtentReports getExtentReports()
	{
		String path = System.getProperty("user.dir")+"/Reports/report.html";
		
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Web Automation Test");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name", "Mithun Kumbhar");
		return extent;
		
	}

}
