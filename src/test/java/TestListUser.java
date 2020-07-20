import Utils.HelperMethods;
import Utils.RestUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestListUser {
    final Logger logger = LoggerFactory.getLogger(getClass());

    private Response res = null;
    private JsonPath jp = null;

    @BeforeClass
    public void setup (){
        RestUtil.setBaseURI("https://reqres.in/");
        RestUtil.createSearchQueryPath("api", "users", "page", "2");
        RestUtil.setContentType(ContentType.JSON);
        res = RestUtil.getResponse();
        jp = RestUtil.getJsonPath(res);
    }

    @Test
    public void T01_StatusCodeTest() {
        HelperMethods.checkStatusIs200(res);
    }

    @Test
    public void T02_DataTest() {
        Assert.assertNotNull(jp.get("data"), "data为空");
    }

    @Test
    public void T03_PageTest() {
        int per_page = jp.getInt("per_page");
        int total = jp.getInt("total");
        int total_pages = jp.getInt("total_pages");

        Assert.assertTrue(per_page * total_pages >= total, "分页错误");
    }

    @AfterClass
    public void afterTest (){
        RestUtil.resetBaseURI();
        RestUtil.resetBasePath();
    }

}
