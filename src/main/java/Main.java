import com.aventstack.extentreports.ExtentReports;
import util.UniqueNamingUtil;

public class Main {

    public static void main(String[] args){

        ExtentReports extentReports = new ExtentReports();
        extentReports.createTest("src/test/results/GetBookingApi" + UniqueNamingUtil.getUniqueName() + ".html");
    }
}
