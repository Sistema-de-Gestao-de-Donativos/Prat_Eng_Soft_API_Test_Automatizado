package prat_eng_soft_api_test_automatizado.Validation;

import java.io.File;

import io.qameta.allure.Allure;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class GenericValidation {
    private Response resposta;

    public void setResponse(Response resposta) {
        this.resposta = resposta;
        resposta.then().log().all();
    }

    public void validarStatusCode(int statusCode) {
        Allure.step("Validando o status code da resposta", () -> {
            resposta.then().assertThat().statusCode(statusCode);
        });
    }

    public void validarContrato(File jsonContrato) {
        Allure.step("Validando o contrato da resposta", () -> {
            resposta.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonContrato));
        });
    }

}