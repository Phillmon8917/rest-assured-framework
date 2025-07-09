package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;


public class ExtentReportManager {

    private static ExtentReports extentReports;

    private ExtentReportManager() {
    }

    public static ExtentReports getExtentReporter(String reportName) {

        reportName = reportName.replace(" ", "");

        if (extentReports == null) {

            ExtentSparkReporter spark = new ExtentSparkReporter("src/test/results/" + reportName + "_" + UniqueNamingUtil.getUniqueName() + ".html");
            extentReports = new ExtentReports();

            try {
                final File CONF = new File("config/extent-config.xml");
                spark.loadXMLConfig(CONF);
                spark.config().setDocumentTitle("ExtentReport");

                extentReports.attachReporter(spark);
            } catch (Exception e) {
                LoggingUtil.log("ExtentReportManager", "getExtentReporter", e.toString());
                throw new RuntimeException("Refer to the Logs for a detailed exception");
            }

        }

        return extentReports;
    }
}
