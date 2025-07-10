import com.aventstack.extentreports.ExtentReports;
import util.UniqueNamingUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception{

        ExtentReports extentReports = new ExtentReports();
        extentReports.createTest("src/test/results/GetBookingApi" + UniqueNamingUtil.getUniqueName() + ".html");

        File file = new File("src/main/java/config/extent-config.xml");
        String line = "";
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null){

            System.out.println(line);
        }
        reader.close();
    }
}
