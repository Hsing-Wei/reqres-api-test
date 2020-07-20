package DataProvider;

import Utils.ReadExcelCases;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class RegisterCasesDataProvider {
    @DataProvider(name = "registerCasesProvider")
    public static Object[][] caseProvider() throws IOException, BiffException {
        String filePath = "src/test/testCases/register.xls"; //测试案例相对路径
        Object[][] cases = ReadExcelCases.readCases(filePath);
        return cases;
    }
}
