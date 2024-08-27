package prat_eng_soft_api_test_automatizado.service;

import java.util.Map;

import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class ExemploService {

    RequestSpecBuilder requestSpecBuilder;

    public ExemploService() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
            .setBaseUri("colocar base uri da API")
            .setBasePath("colocar base path da API")
            .setContentType("o formato de dado que vai ser enviado na requisição")
            .setRelaxedHTTPSValidation();
    }

    //cada metodo equivale a um endpoint da API
    public Response getEndpoint(Map<String,Object> pathParams){
        Allure.parameter("nome do parametro", "valor do parametro"); // para ficar bonitinho no report da allure
        
        Response resposta = RestAssured
                .given()
                .spec(requestSpecBuilder.build())
                .pathParams(pathParams)
                .filter(new AllureRestAssured()) // para ficar bonitinho no report no allure
                .get("colocar rota do endpoint");
        return resposta;
    }   

}