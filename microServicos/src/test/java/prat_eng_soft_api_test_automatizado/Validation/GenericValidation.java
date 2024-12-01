package prat_eng_soft_api_test_automatizado.Validation;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

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

    public void validarMensagem(String campo, String valor) {
        Allure.step("Validando o campo " + campo + " com o valor " + valor, () -> {
            resposta.then().assertThat().body(campo, org.hamcrest.Matchers.equalTo(valor));
        });
    }

    public void validarConsultaMongoDb(Document resultadoConsulta) {
        Allure.step("Validando a consulta no Banco MongoDb ", () -> {
            assertTrue(resultadoConsulta != null);
            Allure.addAttachment("Resultado da consulta no MongoDB",
                    resultadoConsulta.toJson(JsonWriterSettings.builder().outputMode(JsonMode.RELAXED).build()));
        });
    }

}