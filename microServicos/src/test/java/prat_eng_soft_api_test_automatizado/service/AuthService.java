package prat_eng_soft_api_test_automatizado.service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Propósito: Classe para controle do Token de Acesso
 */
public class AuthService {

    private RequestSpecBuilder requestBuilder;

    public AuthService(String baseUri, String basePath, String clientId, String clientSecret) {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder
                .setContentType(ContentType.URLENC)
                .setAccept(ContentType.ANY)
                .addParam("grant_type", "client_credentials")
                .addParam("client_id", clientId)
                .addParam("client_secret", clientSecret)
                .setBaseUri(baseUri)
                .setBasePath(basePath);
    }

    /**
     * Método responsavel por realizar a chamada para obter o Token de acesso
     * 
     * @return A resposta contendo o Token de acesso
     */
    private Response postRefreshGlobalToken() {
        Response response = RestAssured
                .given()
                .spec(requestBuilder.build())
                .when()
                .post();
        return response;
    }

    /**
     * Método responsavel por montar o Token para ser usado nas requisições
     * 
     * @return o Token já formatado para ser usado nas requisições
     */
    public String getToken() {
        String prefixToken = "Bearer ";
        String token = postRefreshGlobalToken()
                .then()
                .statusCode(200)
                .extract()
                .path("access_token");

        return prefixToken + token;
    }

}