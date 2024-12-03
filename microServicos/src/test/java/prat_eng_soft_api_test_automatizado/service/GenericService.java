package prat_eng_soft_api_test_automatizado.service;

import java.util.HashMap;
import java.util.Map;

import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Classe responsavel por realizar as chamadas HTTPS utilizando o RestAssured
 */
public class GenericService {

    private RequestSpecBuilder requestSpecBuilder;
    private Map<String, Object> pathParams = new HashMap<>();
    private Map<String, Object> queryParams = new HashMap<>();
    private Map<String, Object> headers = new HashMap<>();
    private String rota = "";
    private Object body = "";

    public GenericService() {
        this.requestSpecBuilder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured()
                        .setRequestAttachmentName("Requisição Realizada")
                        .setResponseAttachmentName("Resposta"))
                .setRelaxedHTTPSValidation();
    }

    public void setBaseUri(String baseUri) {
        this.requestSpecBuilder.setBaseUri(baseUri);
    }

    public void setBasePath(String basePath) {
        this.requestSpecBuilder.setBasePath(basePath);
    }

    public void addHeader(String key, Object value) {
        this.headers.put(key, value);
    }

    public void addPathParam(String key, Object value) {
        this.pathParams.put(key, value);
    }

    public void addQueryParams(String key, Object value) {
        this.queryParams.put(key, value);
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public void prepararParaNovaRequisicao() {
        if (!pathParams.isEmpty()) {
            this.pathParams.clear();
        }

        if (!queryParams.isEmpty()) {
            this.queryParams.clear();
        }

        if (!headers.isEmpty()) {
            this.headers.clear();
        }

        this.body = "";
    }

    private Response executeRequest(String method) {
        return Allure.step("Requisição " + method.toUpperCase() + " Realizada", () -> {
            return RestAssured
                    .given()
                    .spec(this.requestSpecBuilder.build())
                    .pathParams(this.pathParams)
                    .queryParams(this.queryParams)
                    .headers(this.headers)
                    .body(this.body)
                    .log().all()
                    .request(method, this.rota);
        });
    }

    public Response get() {
        return executeRequest("get");
    }

    public Response post() {
        return executeRequest("post");
    }

    public Response put() {
        return executeRequest("put");
    }

    public Response delete() {
        return executeRequest("delete");
    }
}