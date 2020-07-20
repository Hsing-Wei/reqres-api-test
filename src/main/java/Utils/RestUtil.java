package Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class RestUtil {
    public static String path;

    public static void setBaseURI (String baseURI){
        RestAssured.baseURI = baseURI;
    }

    public static void setBasePath(String basePathTerm){
        RestAssured.basePath = basePathTerm;
    }

    public static void setContentType (ContentType Type){
        given().contentType(Type);
    }

    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    public static void  createSearchQueryPath(String searchTerm, String jsonPathTerm, String param, String paramValue) {
        path = searchTerm + "/" + jsonPathTerm + "?" + param + "=" + paramValue;
    }

    public static Response getResponse() {
        //System.out.print("path: " + path +"\n");
        return get(path);
    }

    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
//        System.out.print("returned json: " + json +"\n");
        return new JsonPath(json);
    }
}
