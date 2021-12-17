import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TestBase {
     public final String baseUrl = RestAssured.baseURI = "https://reqres.in/";
     public RequestSpecification httpRequest = RestAssured.given();
}
