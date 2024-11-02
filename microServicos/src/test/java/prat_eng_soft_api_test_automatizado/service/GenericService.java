package prat_eng_soft_api_test_automatizado.service;

import java.util.Map;

import io.qameta.allure.Allure;
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
                .addFilter(new AllureRestAssured()
                        .setRequestAttachmentName("Requisição Realizada")
                        .setResponseAttachmentName("Resposta"))
                .setRelaxedHTTPSValidation();
    }

    public Response get() {
        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .log().all()
                    .get();
        });
    }

    public Response get(Map<String, Object> queryParams) {

        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .queryParams(queryParams)
                    .log().all()
                    .get();
        });
    }

    public Response get(String rota, Map<String, Object> pathParams) {

        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .pathParams(pathParams)
                    .log().all()
                    .get(rota);

        });
    }

    public Response get(String rota, Object body) {

        return Allure.step("Requisição Realizada", () -> {

            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .body(body)
                    .log().all()
                    .get(rota);

        });
    }

    public Response get(String rota, Map<String, Object> pathParams, Map<String, Object> queryParams) {

        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .pathParams(pathParams)
                    .queryParams(queryParams)
                    .log().all()
                    .get(rota);
        });
    }

    public Response post(Object body) {

        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .body(body)
                    .log().all()
                    .post();
        });
    }

    public Response post(String rota, Map<String, Object> pathParams, Object body) {

        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .pathParams(pathParams)
                    .body(body)
                    .log().all()
                    .post(rota);
        });
    }

    public Response post(Map<String, Object> pathParams, Object body) {

        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .pathParams(pathParams)
                    .body(body)
                    .log().all()
                    .post();

        });

    }

    public Response delete(String rota, Map<String, Object> pathParams) {

        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .pathParams(pathParams)
                    .log().all()
                    .delete(rota);
        });
    }

    public Response delete(String rota, Map<String, Object> pathParams, Map<String, Object> queryParams) {

        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .pathParams(pathParams)
                    .queryParams(queryParams)
                    .log().all()
                    .delete(rota);

        });
    }

    public Response put(String rota, Map<String, Object> pathParams, Object body) {

        return Allure.step("Requisição Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(requestSpecBuilder.build())
                    .pathParams(pathParams)
                    .body(body)
                    .log().all()
                    .put(rota);
        });
    }
}