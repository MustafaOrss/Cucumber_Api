package stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class jsonPHstepdefinitions {

    String endpoint;
    Response response;

    JSONObject requestBody;
    JsonPath responseJsonPath;
    @Given("Kullanici {string} base URL'ini kullanir")
    public void kullanici_base_url_ini_kullanir(String string) {
      //Kullanici "jPHBaseUrl" base URL'ini kullanir

        endpoint = ConfigReader.getProperty("jPHBaseUrl"); //https://jsonplaceholder.typicode.com
    }
    @Then("Path parametreleri icin {string} kullanilir")
    public void path_parametreleri_icin_kullanilir(String pathParams) {
      //Path parametreleri icin "post/44" kullanilir
        endpoint = endpoint+ "/"+ pathParams;
    }
    @Then("jPH server a GET request gonderir ve testleri yapmak icin response degerini kaydeder")
    public void j_ph_server_a_get_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {
      //jPH server a GET request gonderir ve testleri yapmak icin response degerini kaydeder
        response = given()
                .when()
                .get(endpoint);
        response.prettyPrint();
    }
    @Then("jPH respons'da status degerinin {int}")
    public void j_ph_respons_da_status_degerinin(Integer statusCode) {
      //jPH respons'da status degerinin 200
        Assert.assertEquals(statusCode,(Integer) response.statusCode());
    }
    @Then("jPH respons'da content type degerinin {string}")
    public void j_ph_respons_da_content_type_degerinin(String contentType) {
        //And jPH respons'da content type degerinin "application/json; charset=utf-8"
        Assert.assertEquals(contentType,response.contentType());
    }
    @Then("jPH GET response body'sinde {string} degerinin Integer {int}")
    public void j_ph_get_response_body_sinde_degerinin_ınteger(String attribute, Integer expectedValue) {
      //jPH GET response body'sinde "userId" degerinin Integer 5
        responseJsonPath = response.jsonPath();
        Assert.assertEquals(expectedValue,(Integer) responseJsonPath.getInt(attribute));

    }
    @Then("jPH GET respons body'sinde {string} degerinin String {string}")
    public void j_ph_get_respons_body_sinde_degerinin_string(String attribute, String expectedValue) {
      //jPH GET respons body'sinde "title" degerinin String "optio dolor molestias sit"
        responseJsonPath = response.jsonPath();
        Assert.assertEquals(expectedValue,responseJsonPath.getString(attribute));
    }

    @Then("POST request icin {string}, {string}, {int} {int} bilgileri ile request body olusturur")
    public void post_request_icin_bilgileri_ile_request_body_olusturur(String title, String body, Integer userId, Integer id) {
      //POST request icin "Ahmet", "Merhaba", 10 70 bilgileri ile request body olusturur
        JSONObject requestBody = new JSONObject();
        requestBody.put("title","Ahmet");
        requestBody.put("body","Merhaba");
        requestBody.put("userId",10);
        requestBody.put("id",70);
    }
    @Then("jPH server a POST request gonderir ve testleri yapmak icin response degerini kaydeder")
    public void j_ph_server_a_post_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {
      //jPH server a POST request gonderir ve testleri yapmak icin response degerini kaydeder
        response = given()
                .when()
                   .body(requestBody.toString())
                .contentType(ContentType.JSON)
                .post(endpoint);
        response.prettyPrint();
    }
    @Then("jPH respons daki {string} header degerinin {string}")
    public void j_ph_respons_daki_header_degerinin(String header, String attiributeValue) {
      //jPH respons daki "Connection" header degerinin "keep-alive"
        Assert.assertEquals(attiributeValue,response.header(header));

    }
    @Then("response attiribute degerlerinin {string}, {string}, {int} {int}")
    public void response_attiribute_degerlerinin(String title, String body, Integer userId, Integer id) {
      //response attiribute degerlerinin "Ahmet", "Merhaba", 10 70
       responseJsonPath= response.jsonPath();
        Assert.assertEquals(title,responseJsonPath.getString("title"));
        Assert.assertEquals(body,responseJsonPath.getString("body"));
        Assert.assertEquals(userId,(Integer) responseJsonPath.getInt("userId"));
        Assert.assertEquals(id,(Integer) responseJsonPath.getInt("id"));
    }

}
