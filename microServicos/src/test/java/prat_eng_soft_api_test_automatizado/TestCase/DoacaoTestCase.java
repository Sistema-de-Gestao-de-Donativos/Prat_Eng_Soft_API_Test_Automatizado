package prat_eng_soft_api_test_automatizado.TestCase;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.service.DoacaoService;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;

@TestMethodOrder(OrderAnnotation.class)
public class DoacaoTestCase {

    private GenericValidation genericValidation = new GenericValidation();
    private DoacaoService doacaoService = new DoacaoService();

    /*
     * @BeforeAll
     * public void getToken(){
     * AuthService authService = new AuthService("baseuri", "rota", "clientid",
     * "clientsecret");
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
        Response resposta = doacaoService.casoFelizIncluirDoador();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("criarDoador"));
    }

    @Test
    @DisplayName("Micro Serviço de Doação = Busca de um doador pelo Email")
    @Tag("Regressao")
    @Order(2)
    public void buscarDoadorPeloEmail() {
        Allure.description("Teste para validar a busca de um doador pelo email");
        Response resposta = doacaoService.casoFelizConsultarDoadorPeloEmail();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("buscarDoadorPeloEmail"));
    }

    @Test
    @DisplayName("Micro Serviço de Doação = Envio de uma Doação")
    @Tag("Regressao")
    @Order(4)
    public void enviarDoacao() {
        Allure.description("Teste para validar o envio de uma Doação");
        Response resposta = doacaoService.casoFelizIncluirDoacao();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        resposta.then().log().all();
    }

    @Test
    @DisplayName("Micro Serviço de Doação = Busca de uma Doação pelo Id")
    @Tag("Regressao")
    @Order(5)
    public void buscarDoacaoPeloId() {
        Allure.description("Teste para validar a busca de uma doação pelo idDoacao");
        Response resposta = doacaoService.casoFelizBuscarDoacaoPeloId();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("buscarDoacaoPeloId"));
    }

    @Test
    @DisplayName("Micro Serviço de Doação = Busca de um doador pela Data")
    @Tag("Regressao")
    @Order(3)
    public void buscarDoadorPeloData() {
        Allure.description("Teste para validar busca de um doador por uma escopo de data");
        Response resposta = doacaoService.casoFelizBuscarPelaData();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("buscarDoacaoPelaData"));

    }

}
