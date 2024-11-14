package prat_eng_soft_api_test_automatizado.TestCase;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.TestCase.models.Abrigo;
import prat_eng_soft_api_test_automatizado.TestCase.models.AbrigoAddress;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;

@TestMethodOrder(OrderAnnotation.class)
public class AbrigosTestCase extends BaseTestCase {

    private GenericValidation genericValidation;
    private ConexaoBancoDados conexaoBancoDados;

    public AbrigosTestCase() {
        super("abrigo", "/v1/abrigos");
        genericValidation = new GenericValidation();
        conexaoBancoDados = new ConexaoBancoDados();
    }

    /*
     * @BeforeAll
     * public void getToken(){
     * AuthService authService = new AuthService("baseuri", "rota");
     * String Token = authService.getToken();
     * genericService.addHeader("Authorization", "Bearer " + Token);
     * }
     */

    @BeforeEach
    public void allureReport(TestInfo testInfo) {
        Allure.epic("Sprint 1");
        Allure.feature("Micro Serviço de Abrigos");
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Listar todos os abrigos")
    @Tag("Regressao")
    @Order(1)
    public void listarTodosAbrigos(TestInfo testInfo) {
        Allure.description("Teste para validar a listagem de todos os abrigos já cadastrados");
        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigos"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Listar abrigo pelo código")
    @Tag("Regressao")
    @Order(2)
    public void listarAbrigoPeloCodigo() {
        Allure.description("Teste para validar a listagem de um abrigo pelo seu código");
        genericService.addQueryParams("codAbrigo", 1);
        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Listar abrigo pelo nome")
    @Tag("Regressao")
    @Order(3)
    public void listarAbrigoPeloNome() {
        Allure.description("Teste para validar a listagem de um Abrigo pelo seu nome");
        genericService.addQueryParams("nomeAbrigo", "Marau_94403-491");
        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));

    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Incluir abrigo")
    @Tag("Regressao")
    @Order(4)
    public void adicionarAbrigo() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo");

        Abrigo abrigoDTO = Abrigo.criarAbrigo();

        genericService.setBody(abrigoDTO);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirAbrigo"));
        conexaoBancoDados.encontrarAbrigo(resposta.jsonPath().getString("code"));
    }

    // Erros
    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem nome")
    @Tag("Regressao")
    @Order(5)
    public void validarErroABrigoSemNome() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem nome");

        Abrigo abrigoDTO = Abrigo.criarAbrigo();
        abrigoDTO.setName("");
        genericService.setBody(abrigoDTO);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarCampo("errors[0]", "Name is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar a Rua do Abrigo")
    @Tag("Regressao")
    @Order(6)
    public void validarErroABrigoSemCep() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar a Rua do Abrigo");

        Abrigo abrigoDTO = Abrigo.criarAbrigo();
        AbrigoAddress endereco = abrigoDTO.getAddress();
        endereco.setStreet("");
        abrigoDTO.setAddress(endereco);

        genericService.setBody(abrigoDTO);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarCampo("errors[0]", "Street is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

}