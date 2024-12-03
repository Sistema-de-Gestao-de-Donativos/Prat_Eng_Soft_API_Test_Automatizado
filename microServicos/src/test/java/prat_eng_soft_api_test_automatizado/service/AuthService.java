package prat_eng_soft_api_test_automatizado.service;

import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;

/**
 * Propósito: Classe para controle do Token de Acesso
 */
public class AuthService extends GenericService {

    private RequestSpecBuilder requestBuilder;

    public AuthService(){}
    
    private static String getBaseUri() {
        Properties propriedades;
        try {
            propriedades = ConexaoBancoDados.getProperties("rotas");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar carregar arquivo com as Rotas", e);
        }
        return propriedades.getProperty("auth");
    }

    private void montarRequisicao() {
        setBaseUri(getBaseUri());
        setBasePath("/v1/users");
    }


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

        montarRequisicao();
        setRota("/authenticate");
        
      //  setBody(requestBuilder);
        //addHeader("Authorization", "Bearer MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmTts82P1BPCvfgd2jl/azyXiCzUqhGcKpRcHZ068e6EcyTx9q3bn9yLNWzvZGkWloXQR0U02fn6aI94xU1FHy/ssyzuF6CIJvYlDQlpQwyQoteigX+jwvx+97BwEgb8rA3fgw4Vd+EUwSARueIHjZ8jQAZ4qq0d6BVOZlw+ii28BCFJn+bHnJ7mXzCXfgJldoIYRV4IgLRwxUB21dSDwlVDks4giXPERpEVN7zldq6lXnLiZa0OSpwyoQSPdFJ9oU986ScSzIxUaB4tsGPJZhETfqv+kCy+j26DxBUbbP4afCj13KBMzEyOYoLsPIHkRj122CnNM9f5ajcDlR+1rTwIDAQAB");
        //MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmTts82P1BPCvfgd2jl/azyXiCzUqhGcKpRcHZ068e6EcyTx9q3bn9yLNWzvZGkWloXQR0U02fn6aI94xU1FHy/ssyzuF6CIJvYlDQlpQwyQoteigX+jwvx+97BwEgb8rA3fgw4Vd+EUwSARueIHjZ8jQAZ4qq0d6BVOZlw+ii28BCFJn+bHnJ7mXzCXfgJldoIYRV4IgLRwxUB21dSDwlVDks4giXPERpEVN7zldq6lXnLiZa0OSpwyoQSPdFJ9oU986ScSzIxUaB4tsGPJZhETfqv+kCy+j26DxBUbbP4afCj13KBMzEyOYoLsPIHkRj122CnNM9f5ajcDlR+1rTwIDAQAB

        Response response = get();
        response.then().log().all();

      //  String prefixToken = "Bearer ";
      //  String token = postRefreshGlobalToken()
     //           .then()
     //           .statusCode(200)
     //           .extract()
     //           .path("access_token");

        return "prefixToken + token";
    }

}