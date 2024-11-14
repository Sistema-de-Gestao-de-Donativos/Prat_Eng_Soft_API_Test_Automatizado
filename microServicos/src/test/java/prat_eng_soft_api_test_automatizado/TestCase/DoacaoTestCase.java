package prat_eng_soft_api_test_automatizado.TestCase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.TestCase.models.Doacao;
import prat_eng_soft_api_test_automatizado.TestCase.models.DoacaoDoador;
import prat_eng_soft_api_test_automatizado.TestCase.models.DoacaoItens;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;

public class DoacaoTestCase extends BaseTestCase {

    GenericValidation genericValidation;
    private ConexaoBancoDados conexaoBancoDados;

    public DoacaoTestCase() {
        super("doacao", "v1/");
        genericValidation = new GenericValidation();
        conexaoBancoDados = new ConexaoBancoDados();
    }

    /*
     * @BeforeAll
     * public void getToken(){
     * AuthService authService = new AuthService("baseuri", "rota", "clientid", "clientsecret");
     * String Token = authService.getToken();
     * genericService.addHeader("Authorization", Token);
     * }
     */

    @BeforeEach
    public void allureReport(TestInfo testInfo) {
        Allure.epic("Sprint 2");
        Allure.feature("Micro Serviço de Centro de Doação");
    }

    @Test
    @DisplayName("Micro Serviço de Doação = Inclusão de um novo doador")
    @Tag("Regressao")
    @Order(1)
    public void criarDoador() {
        Allure.description("Teste para validar a criação de um novo Doador");

        DoacaoDoador doacaoItemDTO = DoacaoDoador.criarDoador();
        genericService.setBody(doacaoItemDTO);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("criarDoador"));
        conexaoBancoDados.encontrarDoadorDoacao(resposta.jsonPath().getString("id"));
    }

    @Test
    @DisplayName("Micro Serviço de Doação = Busca de um doador pela Data")
    @Tag("Regressao")
    @Order(3)
    public void buscarDoadorPeloData() {
        Allure.description("Teste para validar busca de um doador por uma escopo de data");

        genericService.addQueryParams("startDate", "2024-10-10");
        genericService.addQueryParams("endDate", "2024-10-31");
        genericService.setRota("doacao/date");

        Response resposta = genericService.get();

        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("buscarDoacaoPelaData"));

    }

    @Test
    @DisplayName("Micro Serviço de Doação = Envio de uma Doação")
    @Tag("Regressao")
    @Order(4)
    public void enviarDoacao() {
        Allure.description("Teste para validar o envio de uma Doação");

        DoacaoItens doacaoItensDTO = new DoacaoItens(
                faker.food().ingredient(),
                new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday()),
                faker.food().measurement(),
                faker.number().numberBetween(1, 1000),
                faker.food().spice());

        List<DoacaoItens> itens = new ArrayList<>();
        itens.add(doacaoItensDTO);

        Doacao doacaoDTO = new Doacao(
                2,
                faker.number().numberBetween(1, 1000),
                itens);

            genericService.setBody(doacaoDTO);
            //genericService.addPathParam("idDoador", 1);
        // Response resposta = genericService.post("doacao", pathParams, doacaoDTO);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);

        conexaoBancoDados.encontrarEnviooacao("1");

    }

    @Test
    @DisplayName("Micro Serviço de Doação = Busca de uma Doação pelo Id")
    @Tag("Regressao")
    @Order(5)
    public void buscarDoacaoPeloId() {
        Allure.description("Teste para validar a busca de uma doação pelo idDoacao");
        genericService.addPathParam("idDoacao", 1);
        // Response resposta = genericService.get("doacao/{idDoacao}",pathParams);

        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("buscarDoacaoPeloId"));

    }

}
