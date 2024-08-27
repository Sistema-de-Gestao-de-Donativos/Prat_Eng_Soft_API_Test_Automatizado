package prat_eng_soft_api_test_automatizado.validation;

import java.io.File;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ExemploValidation {
    private Response resposta;

    public void setResponse(Response resposta) {
        this.resposta = resposta;
        resposta.then().log().all();
    }

    public void validarStatusCode(int statusCode) {
        resposta.then().assertThat().statusCode(statusCode);
    }

    public void validarContrato(File jsonContrato) {
        resposta.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonContrato));
    }

}
