package prat_eng_soft_api_test_automatizado.testCase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.DTO.DoacaoDoadorDTO;
import prat_eng_soft_api_test_automatizado.DTO.DoacaoItemDTO;
import prat_eng_soft_api_test_automatizado.DTO.EstoqueEntradaDTO;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;
import prat_eng_soft_api_test_automatizado.validation.GenericValidation;

public class DoacaoTestCase extends BaseTestCase {
    
    GenericValidation genericValidation;
    
    public DoacaoTestCase() {
        super("http://localhost:8080", "v1/");
        genericValidation = new GenericValidation();
    }

    @Test
    @DisplayName("Ms de Doacoes = ICraicao de um novo ietm")
    @Tag("Regressao")
    @Order(1)
    public void criarItem() {
        Faker faker = new Faker(new Locale("pt-BR"));


        DoacaoItemDTO doacaoItemDTO = new DoacaoItemDTO(
                faker.number().numberBetween(1, 1000),
                faker.food().ingredient(),
                faker.number().numberBetween(1, 1000));

        Response resposta = genericService.post("item", pathParams, doacaoItemDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("criarItem"));
    }

    @Test
    @DisplayName("Ms de Doacoes = Inclusao de um novo doador")
    @Tag("Regressao")
    @Order(1)
    public void criarDoador() {
        Faker faker = new Faker(new Locale("pt-BR"));

        DoacaoDoadorDTO doacaoItemDTO = new DoacaoDoadorDTO(
                faker.name().fullName(),
                faker.number().digits(11),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone());

        Response resposta = genericService.post("doador", pathParams, doacaoItemDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("criarDoador"));
    }

    @Test
    @DisplayName("Ms de Doacoes = Busca de um  doador pelo Email")
    @Tag("Regressao")
    @Order(1)
    public void buscarDoadorPeloEmail() {
        queryParams.put("emailRequest", "feliciano.alvares@hotmail.com");
        String body = "{\"email\": \"feliciano.alvares@hotmail.com\"}";
        Response resposta = genericService.get("doador", body);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("criarDoador"));
    }

    @Test
    @DisplayName("Ms de Doacoes = Envio de uma Doacao")
    @Tag("Regressao")
    @Order(1)
    public void enviarDoacao() {
        queryParams.put("emailRequest", "feliciano.alvares@hotmail.com");
        String body = "{\"email\": \"feliciano.alvares@hotmail.com\"}";
        Response resposta = genericService.get("doador", body);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("criarDoador"));
    }



}
