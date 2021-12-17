
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.sql.Timestamp;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static io.restassured.RestAssured.get;
import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;

public class ReqresInTests extends TestBase {

    @Test
    void canGetRequestListOfUsers() {
        get(baseUrl + "api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[1].first_name", is("Lindsay"));

    }
    @Test
    void canCreateNewUser() {
        given()
                .contentType(JSON)
                .body("{\n" +
                        "    \"name\": \"Test\",\n" +
                        "    \"job\": \"Testov\"\n" +
                        "}")
                .when()
                .post(baseUrl + "api/users")
                .then()
                .statusCode(201)
                .body("name", is("Test"));

    }

    @Test
    void canRegisterExistUser() {
        given()
                .contentType(JSON)
                .body("{\n" +
                        "    \"email\": \"tracey.ramos@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .when()
                .post(baseUrl + "api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X6"))
                .body("id", is(6));

    }

    @Test
    void checkUnsuccessfulRegistration() {
        given()
                .contentType(JSON)
                .body("{\n" +
                        "    \"email\": \"tracey.ramos@reqres.in\"\n" +
                        "}")
                .when()
                .post(baseUrl + "api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));

    }
    @Test
    void canRequestsSingleUser(){
        given()
                .get(baseUrl + "api/users/3")
                .then()
                .statusCode(200)
                .body("data.id", is(3))
                .body("data.email", is("emma.wong@reqres.in"))
                .body("data.first_name", is("Emma"));
    }
}



