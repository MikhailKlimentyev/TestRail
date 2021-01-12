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
                        .basic(System.getenv().getOrDefault("user", PropertyReader.getProperty("user")),
                                System.getenv().getOrDefault("pass", PropertyReader.getProperty("pass")))
                        .header("Content-Type", "application/json")
                        .when()
                        .log().all()
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
                        .basic(System.getenv().getOrDefault("user", PropertyReader.getProperty("user")),
                                System.getenv().getOrDefault("pass", PropertyReader.getProperty("pass")))
                        .header("Content-Type", "application/json")
                        .body(body)
                        .when()
                        .log().all()
                        .post(urlAPI + uri)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();
    }
}
