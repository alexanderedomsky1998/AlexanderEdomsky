package hw5;

import hw5.Issues.*;
import hw5.Project.Project;
import hw5.Project.ProjectResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class IssuesWithAPITest {

    private Properties properties;
    private String baseUrl;
    private Header header;

    @BeforeSuite
    public void setUp() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/hw5.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        baseUrl = properties.getProperty("uri");
        String token = properties.getProperty("token");
        header = new Header("Authorization", token);
    }

    @Test
    public void issueAPITest() {


        hw5.Project.Status projectStatus = new hw5.Project.Status(0,
                properties.getProperty("project.status"),
                properties.getProperty("project.status"));

        Project project = new Project(0,
                properties.getProperty("project.name"),
                properties.getProperty("project.description"),
                projectStatus);

        Files firstFile = new Files(properties.getProperty("file1.name"),
                properties.getProperty("file1_content"));
        Files secondFile = new Files(properties.getProperty("file2.name"),
                properties.getProperty("file2_content"));

        IssueField category = new IssueField(Integer.parseInt(properties.getProperty("category.id")),
                properties.getProperty("category.name"));

        LabeledIssueField severity = new LabeledIssueField(Integer.parseInt(properties.getProperty("severity.id")),
                properties.getProperty("severity.name"),
                properties.getProperty("severity.name"));

        LabeledIssueField priority = new LabeledIssueField(Integer.parseInt(properties.getProperty("priority.id")),
                properties.getProperty("priority.name"),
                properties.getProperty("priority.name"));

        Status status = new Status(Integer.parseInt(properties.getProperty("status.id")),
                properties.getProperty("status.name"),
                properties.getProperty("status.name"),
                properties.getProperty("status.color"));

        ProjectResponse projectResponce = given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .body(project)
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().response().as(ProjectResponse.class);

        project = projectResponce.getProject();

        Issue issue =
                new Issue(
                        0,
                        properties.getProperty("issue.summary"),
                        properties.getProperty("issue.description"),
                        project,
                        category,
                        severity,
                        priority,
                        status
                );
        IssueWithFiles issueWithFiles = new IssueWithFiles(
                issue,
                new Files[]{firstFile, secondFile}
        );


        IssueResponse issueResponse =
                given().
                        baseUri(baseUrl).
                        header(header).
                        contentType(ContentType.JSON).
                        body(issueWithFiles)
                        .when().
                        post("/issues/")
                        .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                        .extract().response().as(IssueResponse.class);

        issue = issueResponse.getIssue();

        JSONObject issueJson = new JSONObject();
        issueJson.put("summary", properties.getProperty("issue.updated_summary"));

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON).
                body(issueJson.toString())
                .when().
                patch("/issues/" + issue.getId())
                .then().
                contentType(ContentType.JSON).
                statusCode(200);

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + project.getId())
                .then().
                statusCode(200);
    }

    @Test
    public void issueAPIErrorTest() {

        JSONObject issueJson = new JSONObject();
        issueJson.put("summary", properties.getProperty("issue.updated_summary"));

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON).
                body(issueJson.toString())
                .when().
                patch("/issues/47568")
                .then().
                contentType(ContentType.JSON).
                statusCode(404);
    }

}
