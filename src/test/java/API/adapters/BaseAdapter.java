package API.adapters;

import com.google.gson.Gson;
import io.restassured.response.Response;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    String urlAPI = System.getenv().getOrDefault("url", PropertyReader.getProperty("url")) + "index.php?/api/v2/";
    Gson converter = new Gson();

    public String get(String uri) {
        return
                given()
                        .auth()
                        .preemptive()
                        .basic(System.getenv().getOrDefault("user1", PropertyReader.getProperty("user1")), System.getenv().getOrDefault("pass1", PropertyReader.getProperty("pass1")))
                        .header("Content-Type", "application/json")
                        .when()
                        .get(urlAPI + uri)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().body().asString();

    }

    public Response post(String uri, String body) {
        return
                given()
                        .auth()
                        .preemptive()
                        .basic(System.getenv().getOrDefault("user1", PropertyReader.getProperty("user1")), System.getenv().getOrDefault("pass1", PropertyReader.getProperty("pass1")))
                        .header("Content-Type", "application/json")
                        .body(body)
                        .when()
                        .post(urlAPI + uri)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();
    }

}
