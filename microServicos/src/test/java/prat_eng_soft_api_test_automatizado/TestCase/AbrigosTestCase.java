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
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.service.AbrigoService;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;

@TestMethodOrder(OrderAnnotation.class)
public class AbrigosTestCase {

    private GenericValidation genericValidation = new GenericValidation();
    private AbrigoService abrigoService = new AbrigoService();

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
        Response resposta = abrigoService.casoFelizListarTodosAbrigos();
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
        Response resposta = abrigoService.casoFelizDeListarAbrigoPeloCodigo();
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
        Response resposta = abrigoService.casoFelizDeListarAbrigoPeloNome();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Listar abrigo pelo nome")
    @Tag("Regressao")
    @Order(4)
    public void listarAbrigoPeloNomeEpeloCodigo() {
        Allure.description("Teste para validar a listagem de um Abrigo pelo seu nome");
        Response resposta = abrigoService.casoFelizDeListarAbrigoPeloNomeECodigo();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Incluir abrigo")
    @Tag("Regressao")
    @Order(5)
    public void adicionarAbrigo() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo");
        Response resposta = abrigoService.casoFelizDeAdcionarAbrigo();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirAbrigo"));
    }

    // Erros
    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem nome")
    @Tag("Regressao")
    @Order(6)
    public void validarErroABrigoSemNome() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem nome");
        Response resposta = abrigoService.casoErroCriarAbrigoSemNome();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Name is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar o Telefone do Abrigo")
    @Tag("Regressao")
    @Order(7)
    public void validarErroABrigoSemInformarTelefone() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Telefone do Abrigo");
        Response resposta = abrigoService.casoErroCriarAbrigoSemTelefone();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Phone is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar o Email do Abrigo")
    @Tag("Regressao")
    @Order(8)
    public void validarErroABrigoSemEmail() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Email do Abrigo");
        Response resposta = abrigoService.casoErroCriarAbrigoSemEmail();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Email is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar o Pais do Abrigo")
    @Tag("Regressao")
    @Order(9)
    public void validarErroABrigoSemPais() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Pais do Abrigo");
        Response resposta = abrigoService.casoErroCriarAbrigoSemPais();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Country is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar o Estado do Abrigo")
    @Tag("Regressao")
    @Order(10)
    public void validarErroABrigoSemEstado() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Estado do Abrigo");
        Response resposta = abrigoService.casoErroCriarAbrigoSemEstado();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "State is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar a Cidade do Abrigo")
    @Tag("Regressao")
    @Order(11)
    public void validarErroABrigoSemCidade() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar a Cidade do Abrigo");
        Response resposta = abrigoService.casoErroCriarAbrigoSemCidade();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "City is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar o Bairo do Abrigo")
    @Tag("Regressao")
    @Order(12)
    public void validarErroABrigoSemBairro() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Bairro do Abrigo");
        Response resposta = abrigoService.casoErroCriarAbrigoSemBairro();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Neighborhood is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar a Rua do Abrigo")
    @Tag("Regressao")
    @Order(13)
    public void validarErroABrigoSemRua() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar a Rua do Abrigo");
        Response resposta = abrigoService.casoErroCriarAbrigoSemRua();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Street is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar o Numero do Abrigo")
    @Tag("Regressao")
    @Order(14)
    public void validarErroABrigoSemNumero() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Numero do Abrigo");
        Response resposta = abrigoService.casoErroCriarAbrigoSemNumero();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Number is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

}