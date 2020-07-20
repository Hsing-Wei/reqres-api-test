package Utils;
import io.restassured.response.Response;

import static org.testng.AssertJUnit.assertEquals;

public class HelperMethods {
    public static void checkStatusIs200 (Response res) {
        assertEquals("Status Check Failed!", 200, res.getStatusCode());
    }

    public static void checkStatusIs201 (Response res) {
        assertEquals("Status Check Failed!", 201, res.getStatusCode());
    }

    public static void checkStatusIs400 (Response res) {
        assertEquals("Status Check Failed!", 400, res.getStatusCode());
    }

    public static void checkStatusIs404 (Response res) {
        assertEquals("Status Check Failed!", 404, res.getStatusCode());
    }
}
