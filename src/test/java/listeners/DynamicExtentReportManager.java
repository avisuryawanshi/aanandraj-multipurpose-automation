package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//import testng.email.SendEmailReport;

/**
 * 🔹 ExtentReportManager Listener
 
 * - Dynamically generates detailed Extent Reports for TestNG test execution
 * - Collects system/environment data at runtime
 * - Logs test status: PASS, FAIL, SKIP
 */

public class DynamicExtentReportManager implements ITestListener {
	
		public ExtentSparkReporter sparkReporter;   // Responsible for the UI of the report
	    public ExtentReports extent;                // Responsible for common info in the report
	    public ExtentTest test;                     // Responsible for updating each test result
	    
// ======================================================================================================================	
// Called before any test method is run in the suite. Initializes the Extent of report configuration.
// ======================================================================================================================
	    @Override
	    public void onStart(ITestContext context) {
	        
	    	System.out.println("[INFO] Test Suite Execution Started... Initializing Extent Report...");
	
	        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/MyReport.html");
	
// -------- Set appearance ----------------------------------------------------------
	        sparkReporter.config().setDocumentTitle("Automation Test Report");
	        sparkReporter.config().setReportName("Functional Test Suite");
	        sparkReporter.config().setTheme(Theme.STANDARD);
	
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	
// ======== Dynamically fetch system info ==========================================================================
	        try 
	        {
	            String hostName = InetAddress.getLocalHost().getHostName();
	            extent.setSystemInfo("Computer Name", hostName);
	        } 
	        catch (UnknownHostException e) 
	        {
	            extent.setSystemInfo("Computer Name", "Unknown");
	        }
	
// -------- Basic System Info --------------------------------------------------------------------------------------
	        extent.setSystemInfo("OS", System.getProperty("os.name"));                //✅
	        extent.setSystemInfo("OS Version", System.getProperty("os.version"));
	        extent.setSystemInfo("Architecture", System.getProperty("os.arch"));
	        extent.setSystemInfo("Java Version", System.getProperty("java.version")); //✅
	        extent.setSystemInfo("Java Vendor", System.getProperty("java.vendor"));
	        extent.setSystemInfo("Tester Name", System.getProperty("user.name"));     //✅
	        
// -------- Browser & Environment, can be passed via -Dbrowser or -Denv --------------------------------------------
	        extent.setSystemInfo("Browser", System.getProperty("browser", "Chrome")); //✅
	        extent.setSystemInfo("Environment", System.getProperty("env", "QA"));     //✅
	        
// -------- Time & Locale Info -------------------------------------------------------------------------------------
	        extent.setSystemInfo("User TimeZone", System.getProperty("user.timezone"));
	        extent.setSystemInfo("User Country", System.getProperty("user.country"));
	        extent.setSystemInfo("User Language", System.getProperty("user.language"));
	        
// -------- Memory Info ---------------------------------------------------------------------------------------------
	        extent.setSystemInfo("Available Processors", String.valueOf(Runtime.getRuntime().availableProcessors()));
	        extent.setSystemInfo("Max Memory (MB)", String.valueOf(Runtime.getRuntime().maxMemory() / (1024 * 1024)));

// -------- Java Runtime ----------------------------------------------------------------------------
	        //extent.setSystemInfo("Java Home", System.getProperty("java.home"));
	        //extent.setSystemInfo("Java Classpath", System.getProperty("java.class.path"));
	      
// -------- (set in CI/CD)-specific or Optional Properties ------------------------------------------
	        extent.setSystemInfo("Build Number", System.getProperty("build.number", "N/A"));        //✅
	        extent.setSystemInfo("Git Branch", System.getProperty("git.branch", "Not Specified"));
	        extent.setSystemInfo("Git Commit ID", System.getProperty("git.commit", "Not Specified"));
	        extent.setSystemInfo("Execution Trigger", System.getProperty("triggeredBy", "Manual"));
	        
// -------- Env variables (from shell or Jenkins/GitLab CI/CD) --------------------------------------------------------
	        extent.setSystemInfo("CI/CD Pipeline", System.getenv().getOrDefault("CI_PIPELINE_NAME", "Local Execution")); //✅
	        extent.setSystemInfo("Jenkins Job Name", System.getenv().getOrDefault("JOB_NAME", "N/A"));
	        extent.setSystemInfo("Jenkins Build ID", System.getenv().getOrDefault("BUILD_ID", "N/A"));
	        extent.setSystemInfo("Jenkins URL", System.getenv().getOrDefault("JENKINS_URL", "N/A"));
	        extent.setSystemInfo("Execution URL", System.getenv().getOrDefault("BUILD_URL", "N/A"));
	
	        System.out.println("[INFO] Extent Report setup completed.");
	    }
// ======================================================================================================================	
// Called when a test method passes.
// ======================================================================================================================
	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("[PASS] Test Passed: " + result.getName());
	        
	        test = extent.createTest(result.getName());
	        test.log(Status.PASS, "Test case PASSED is: " + result.getName());
	    }
// ======================================================================================================================	
// Called when a test method fails.
// ======================================================================================================================	
	    @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println("[FAIL] Test Failed: " + result.getName());
	        System.out.println("[FAIL] Reason: " + result.getThrowable());
	        
	        test = extent.createTest(result.getName());
	        test.log(Status.FAIL, "Test case FAILED is: " + result.getName());
	        test.log(Status.FAIL, "Test case FAILED due to: " + result.getThrowable());	       
	    }
// ======================================================================================================================	
// Called when a test method skipped.
// ======================================================================================================================
	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("[SKIP] Test Skipped: " + result.getName());
	        
	        test = extent.createTest(result.getName());
	        test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName());
	    }
// ======================================================================================================================	
// Called after all test methods are executed.
// ======================================================================================================================
	    @Override
	    public void onFinish(ITestContext context) {
	        System.out.println("[INFO] Test Suite Execution Completed. Flushing Extent Report...");
	        
	        extent.flush();  // Finalize the report
	        System.out.println("[INFO] Report generated at: " + System.getProperty("user.dir") + "/reports/MyReport.html");
	        
// Send email ===========================================================================================================
// Trigger email sending here
        /*try
        {
            SendEmailReport.sendReport();  // Email utility class call
        } 
        catch (Exception e) 
        {
            System.out.println("[ERROR] Failed to send email report.");
            e.printStackTrace();
        }*/
    }

}
