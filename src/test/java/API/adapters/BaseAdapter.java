package API.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    String url = "https://marymanzhos.testrail.io/index.php?/api/v2/";
    Gson converter = new Gson();

    public void auth(String user, String password) {
        given()
                .auth()
                .preemptive()
                .basic(user, password);

    }

    public String get(String uri) {
        return
                given()
                        .auth()
                        .preemptive()
                        .basic("mary_m@mailinator.com", "ssjBJyMTVDCkt/X4L5Im")
                        .header("Content-Type", "application/json")
                .when()
                        .get(url + uri)
                .then()
                        .log().all()
                        .extract().body().asString();

    }

    public Response post(String uri, String body) {
        return
                given()
                        .auth()
                        .preemptive()
                        .basic("mary_m@mailinator.com", "ssjBJyMTVDCkt/X4L5Im")
                        .header("Content-Type", "application/json")
                        .body(body)
                .when()
                        .post(url + uri)
                .then()
                        .log().all()
                        .extract().response();
    }

}
