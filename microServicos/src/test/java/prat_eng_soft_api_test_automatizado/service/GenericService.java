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
    String token ="";

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

    public void removeHeader(String key) {
        this.headers.remove(key);
    }

    public void removePathParam(String key) {
        this.pathParams.remove(key);
    }

    public void removeQueryParams(String key) {
        this.queryParams.remove(key);
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

    public void setToken(String token) {
        this.token = token;
        //this.headers.put("Authorization", "Bearer " + token);
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
                    //.auth().preemptive().oauth2(this.token)
                    //.auth().preemptive().oauth2("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiZW1haWwiOiJKb2huIERvZSIsInJvbGUiOiJhZG1pbiIsImV4cCI6MTgxNjIzOTAyMn0.h5il33HQmf24lmyomZHarCQYhaeErSixVHlwzY7iCmq7aZ644IzkIeHcG4JmaK8tmh1187oJURDIX5lMdkKcpFojqroFXIg3nVUwo-B16L7sWaamzzaM2EZT2DJXTjYx0KaFbDqTWzkZN-16Z482miqg7v-UbvHDbZpHfTri28ZhaLbVJ4qgNxM8jhD-ZDtV6iVyP473xTVwk7oTsW2dH-Iimc-AeGtYCVUDDXM0BeiapHYRzG6lC1l6IMJEeOHR50-GHaQtDx8wzc1cpZoXzjlrBZjgpl9tOdV24kaWELG-lIMsMjDPpFriKpMVvUK9_trxjfbv14Eog61ilZNvJA")
                    //.auth().preemptive().oauth2("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiZW1haWwiOiJKb2huIERvZSIsInJvbGUiOiJhZG1pbiIsImV4cCI6MTgxNjIzOTAyMn0.h5il33HQmf24lmyomZHarCQYhaeErSixVHlwzY7iCmq7aZ644IzkIeHcG4JmaK8tmh1187oJURDIX5lMdkKcpFojqroFXIg3nVUwo-B16L7sWaamzzaM2EZT2DJXTjYx0KaFbDqTWzkZN-16Z482miqg7v-UbvHDbZpHfTri28ZhaLbVJ4qgNxM8jhD-ZDtV6iVyP473xTVwk7oTsW2dH-Iimc-AeGtYCVUDDXM0BeiapHYRzG6lC1l6IMJEeOHR50-GHaQtDx8wzc1cpZoXzjlrBZjgpl9tOdV24kaWELG-lIMsMjDPpFriKpMVvUK9_trxjfbv14Eog61ilZNvJA")
                    //.auth().preemptive().oauth2("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2NzUxMGQwOTcwZjhiYmM3MDE2M2JlYTUiLCJlbWFpbCI6InRlc3RlbGVvQGV4YW1wbGUuY29tIiwicm9sZSI6InN1cGVyYWRtaW4iLCJleHAiOjE3MzM0NTg1ODR9.NIFpk3nrUcboPbmNXDRifiq3njeqUPcO3prqis4TO2tGOkiqddsGlVS9PiZTZPiNRj35cpY8vqWdZVSgavkEJ7W4opZYlR7v2O3jOs0uhVDL19A7NwGyr52jul7MVawFCBo4eomOn2EdABvOmUs-M6qFxDZ0vV3afPqcBr08joE")
                    .auth().preemptive().oauth2("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2NzQ4MDRhODNmM2MwNjYzZDg2YjBiNjUiLCJlbWFpbCI6ImFuYWNsYXJhLnZpZWlyYUBnbWFpbC5jb20iLCJyb2xlIjoic3VwZXJhZG1pbiIsImV4cCI6MTczMzQ2NzYwMn0.MT-iAmUKsCZY3oPb1zKX64JgLGZgzItH7LBioLmqjyU5zbtws_ROqcU4B7U_TPtY1fojiNXkrFJde46zhpa8g2D23WteHjk14wxEph0WU9JO3n116L9BqHWPjo3PGD8DYjS5CCwxTkTJPNjRhoJJ5Fm_9lt_bOD-aQCLN7G2GZE")
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