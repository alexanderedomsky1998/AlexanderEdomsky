package hw5;

import static io.restassured.RestAssured.given;

import hw5.Project.Project;
import hw5.Project.ProjectResponse;
import hw5.Project.Status;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectWithAPITest {

    private Properties properties;
    private String baseUri;
    private Header header;

    @BeforeClass(alwaysRun = true)
    public void setUps() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/hw5.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        baseUri = properties.getProperty("uri");
        String token = properties.getProperty("token");
        header = new Header("Authorization", token);
    }

    @Test
    public void projectAPITest() {
        Status status = new Status(0,
                properties.getProperty("project.status"),
                properties.getProperty("project.status"));

        Project project = new Project(0,
                properties.getProperty("project.name"),
                properties.getProperty("project.description"),
                status);

        Project subproject = new Project(0,
                properties.getProperty("subproject.name"),
                properties.getProperty("project.description"),
                status);

        JSONObject statusJson = new JSONObject();
        statusJson.put("label", status.getLabel());
        statusJson.put("name", status.getName());

        JSONObject projectJson = new JSONObject();
        projectJson.put("name", project.getName());
        projectJson.put("description", project.getDescription());
        projectJson.put("status", statusJson.toString());

        JSONObject subProjectJson = new JSONObject();
        subProjectJson.put("name", status.getName());

        JSONObject addSubJson = new JSONObject();
        addSubJson.put("project", subProjectJson);
        addSubJson.put("inherit_parent", true);

        subProjectJson.put("description", subproject.getDescription());
        subProjectJson.put("status", statusJson.toString());

        ProjectResponse response =
                given().
                        baseUri(baseUri).
                        header(header).
                        contentType(ContentType.JSON).
                        body(projectJson.toString())
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().
                        response().
                        as(ProjectResponse.class);

        project = response.getProject();

        response =
                given().
                        baseUri(baseUri).
                        header(header).
                        contentType(ContentType.JSON).
                        body(subProjectJson.toString())
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().
                        response().
                        as(ProjectResponse.class);

        subproject = response.getProject();

        given().
                baseUri(baseUri).
                header(header).
                contentType(ContentType.JSON).
                body(addSubJson.toString())
        .when().
                post("/projects/" + project.getId() + "/subprojects")
        .then().
                statusCode(204);

        given().
                baseUri(baseUri).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + project.getId())
                .then().
                statusCode(200);

        given().
                baseUri(baseUri).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + subproject.getId())
                .then().
                statusCode(200);
    }

    @Test
    public void deleteAPIErrorTest() {
        given().
                baseUri(baseUri).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/12345")
                .then().
                statusCode(403);
    }
}
