package API.adapters;

import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    String url = "https://marymanzhos.testrail.io/index.php?/api/";
    Gson converter = new Gson();

    public void auth(String user, String password) {
        given()
                .auth()
                .preemptive()
                .basic(user, password);

    }

    public Response get(String uri) {
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
                        .extract().response();

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
