
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static io.restassured.RestAssured.get;
import static org.hamcrest.core.Is.is;

public class ReqresInTests extends TestBase {
    @Test
    void canGetRequestListOfUsers() {
        get(baseUrl + "api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[1].first_name", is("Lindsay"));

    }


}
