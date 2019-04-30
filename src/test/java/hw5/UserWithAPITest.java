package hw5;

import static io.restassured.RestAssured.given;

import hw5.User.User;
import hw5.User.UserResponse;
import io.restassured.http.Header;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserWithAPITest {

    private Properties properties;
    private String baseUri;
    private Header header;

    @BeforeSuite(alwaysRun = true)
    public void setUps() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/hw5.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        baseUri = properties.getProperty("uri");
        String token = properties.getProperty("token");
        header = new Header("Authorization", token);
    }

    @Test
    public void userAPITest() {
        User user = new User(
                0,
                properties.getProperty("username"),
                properties.getProperty("realname"),
                properties.getProperty("email")
        );

        JSONObject userParams = new JSONObject();
        userParams.put("username", user.getName());
        userParams.put("real_name", user.getRealName());
        userParams.put("email", user.getEmail());
        userParams.put("password", properties.getProperty("password"));

        UserResponse response =
                given().
                        baseUri(baseUri).
                        header(header).
                        contentType(ContentType.JSON).
                        body(userParams.toString())
                .when().
                        post("/users/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().
                        response().
                        as(UserResponse.class);

        user = response.getUser();

        given()
                .baseUri(baseUri)
                .header(header)
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/" + user.getId())
                .then()
                .statusCode(204);
    }

    @Test
    public void userAPIErrorTest() {
        given()
                .baseUri(baseUri)
                .header(header)
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/12345")
                .then()
                .statusCode(404);
    }
}
