package resources;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification res;
    public RequestSpecification requestSpecification() throws IOException
    {
        if (res == null)
        {
            PrintStream log = new PrintStream(new FileOutputStream("logging.text"));
            res = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return res;
        }
        else
            {
                return res;
            }
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream("D:\\work\\BDD API framework\\src\\test\\java\\resources\\global.properties");
        prop.load(fs);
        return prop.getProperty(key);
    }
    public String getJsonPath(Response response,String key)
    {
        String strresponse = response.asString();
        JsonPath js = new JsonPath(strresponse);
        return js.get(key).toString();

    }


}
