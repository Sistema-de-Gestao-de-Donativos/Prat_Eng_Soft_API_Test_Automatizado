package prat_eng_soft_api_test_automatizado.service;

import java.util.Map;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GenericService {

    private RequestSpecBuilder requestSpecBuilder;

    public GenericService(String baseUri, String basePath) {
        requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .setRelaxedHTTPSValidation();
    }

    public Response get() {
        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .filter(new AllureRestAssured())
                .log().all()
                .get();
        return resposta;
    }

    public Response get(Map<String, Object> queryParams) {
        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .queryParams(queryParams)
                .filter(new AllureRestAssured())
                .log().all()
                .get();
        return resposta;
    }

    public Response get(String rota, Map<String, Object> pathParams) {

        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .pathParams(pathParams)
                .filter(new AllureRestAssured())
                .log().all()
                .get(rota);
        return resposta;
    }

    public Response get(String rota, Map<String, Object> pathParams, Map<String, Object> queryParams) {

        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .pathParams(pathParams)
                .queryParams(queryParams)
                .filter(new AllureRestAssured())
                .log().all()
                .get(rota);
        return resposta;
    }

    public Response post(Object body) {

        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .body(body)
                .filter(new AllureRestAssured())
                .log().all()
                .post();
        return resposta;
    }

    public Response post(String rota, Map<String, Object> pathParams, Object body) {

        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .pathParams(pathParams)
                .body(body)
                .filter(new AllureRestAssured())
                .log().all()
                .post(rota);
        return resposta;
    }

    public Response post(Map<String, Object> pathParams, Object body) {

        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .pathParams(pathParams)
                .body(body)
                .filter(new AllureRestAssured())
                .log().all()
                .post();
        return resposta;
    }

    public Response delete(String rota, Map<String, Object> pathParams) {

        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .pathParams(pathParams)
                .filter(new AllureRestAssured())
                .log().all()
                .delete(rota);
        return resposta;
    }

    public Response delete(String rota, Map<String, Object> pathParams, Map<String, Object> queryParams) {

        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .pathParams(pathParams)
                .queryParams(queryParams)
                .filter(new AllureRestAssured())
                .log().all()
                .delete(rota);
        return resposta;
    }

    public Response put(String rota, Map<String, Object> pathParams, Object body) {

        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .pathParams(pathParams)
                .body(body)
                .filter(new AllureRestAssured())
                .log().all()
                .put(rota);
        return resposta;
    }
}