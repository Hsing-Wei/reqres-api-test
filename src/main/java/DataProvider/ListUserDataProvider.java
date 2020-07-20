package DataProvider;

import org.testng.annotations.DataProvider;

public class ListUserDataProvider {
    @DataProvider(name = "listUserDataProvider")
    public static Object[][] caseProvider(){
        Object[][] cases = new Object[4][2];
        for (int i = 0; i < 4; i++) {
            cases[i][0] = "paramValue";
            cases[i][1] = i;
        }
        return cases;
    }
}
