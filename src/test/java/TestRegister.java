import DataProvider.RegisterCasesDataProvider;
import Utils.RestUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class TestRegister {
    final Logger logger = LoggerFactory.getLogger(getClass());

    private Response res = null;
    private JsonPath jp = null;

    @BeforeClass
    public void setup() {
        RestUtil.setBaseURI("https://reqres.in/api/");
        RestUtil.setBasePath("/register");
    }

    @Test(dataProvider = "registerCasesProvider", dataProviderClass = RegisterCasesDataProvider.class)
    public void runRegisterCases(String caseNo, String title, String code, String des, String runnable, String email, String password) {
        System.out.println("开始执行测试用例");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", email);
        requestParams.put("password", password);
        request.body(requestParams.toJSONString());
        res = request.post();
        jp = RestUtil.getJsonPath(res);
        logger.info("响应状态码： " + res.getStatusCode());
        logger.info("返回结果： " + res.asString());

        Assert.assertEquals(String.valueOf(res.getStatusCode()), code.trim(), "与预期响应码不符");

        if(res.getStatusCode() == 200){
            assert(res.asString().contains("id"));
            assert(res.asString().contains("token"));
            Assert.assertNotNull(jp.get("id"));
            Assert.assertNotNull(jp.get("token"));
        }else{
            Assert.assertEquals(res.asString(),des);
        }
    }

    @AfterClass
    public void afterTest (){
        RestUtil.resetBaseURI();
        RestUtil.resetBasePath();
    }

}
