package prat_eng_soft_api_test_automatizado.TestCase;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.service.CentroDistribuicaoService;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;

@TestMethodOrder(OrderAnnotation.class)
public class CentroDistribuicaoTestCase {

    private GenericValidation genericValidation = new GenericValidation();
    private CentroDistribuicaoService centroDistribuicaoService = new CentroDistribuicaoService();

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
        Allure.epic("Sprint 1");
        Allure.feature("Micro Serviço de Centro de Distribuição");
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Listar todos os Centros de Distribuições")
    @Tag("Regressao")
    @Order(1)
    public void listarTodosCDs() {
        Allure.description("Teste para validar a listagem de todos os Centros de Distribuições já cadastrados");
        Response resposta = centroDistribuicaoService.casoFelizListarTodosCentrosDistribuicao();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarCDs"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Listar Centro de Distribuição pelo código")
    @Tag("Regressao")
    @Order(2)
    public void listarCDsPeloCodigo() {
        Allure.description("Teste para validar a listagem de um Centro de Distribuição pelo seu código");
        Response resposta = centroDistribuicaoService.casoFelizDeListarCentroDistribuicaoPeloCodigo();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarCDCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Listar Centro de Distribuição pelo nome")
    @Tag("Regressao")
    @Order(3)
    public void listarCDsPeloNome() {
        Allure.description("Teste para validar a listagem de um Centro de Distribuição pelo seu nome");
        Response resposta = centroDistribuicaoService.casoFelizDeListarCentroDistribuicaoPeloNome();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarCDCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Listar Centro de Distribuição pelo nome e pelo seu código")
    @Tag("Regressao")
    @Order(4)
    public void listarCDsPeloNomeECd() {
        Allure.description("Teste para validar a listagem de um Centro de Distribuição pelo seu nome");
        Response resposta = centroDistribuicaoService.casoFelizDeListarCentroDistribuicaoPeloNomeECodigo();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarCDCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Incluir Centro de Distribuição")
    @Tag("Regressao")
    @Order(4)
    public void adicionarCD() {
        Allure.description("Teste para validar a inclusão de um novo Centro de Distribuição");
        Response resposta = centroDistribuicaoService.casoFelizAdcionarCentroDistribuicao();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirCD"));
    }

    // Erros
    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um abrigo sem nome")
    @Tag("Regressao")
    @Order(6)
    public void validarErroCdSemNome() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem nome");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemNome();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Name is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um abrigo sem informar o Telefone do Abrigo")
    @Tag("Regressao")
    @Order(7)
    public void validarErroABrigoSemInformarTelefone() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Telefone do Abrigo");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemTelefone();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Phone is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um abrigo sem informar o Email do Abrigo")
    @Tag("Regressao")
    @Order(8)
    public void validarErroABrigoSemEmail() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Email do Abrigo");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemEmail();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Email is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um abrigo sem informar o Pais do Abrigo")
    @Tag("Regressao")
    @Order(9)
    public void validarErroABrigoSemPais() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Pais do Abrigo");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemPais();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Country is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um abrigo sem informar o Estado do Abrigo")
    @Tag("Regressao")
    @Order(10)
    public void validarErroABrigoSemEstado() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Estado do Abrigo");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemEstado();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "State is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um abrigo sem informar a Cidade do Abrigo")
    @Tag("Regressao")
    @Order(11)
    public void validarErroABrigoSemCidade() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar a Cidade do Abrigo");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemCidade();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "City is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um abrigo sem informar o Bairo do Abrigo")
    @Tag("Regressao")
    @Order(12)
    public void validarErroABrigoSemBairro() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Bairro do Abrigo");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemBairro();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Neighborhood is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um abrigo sem informar a Rua do Abrigo")
    @Tag("Regressao")
    @Order(13)
    public void validarErroABrigoSemRua() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar a Rua do Abrigo");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemRua();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Street is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um abrigo sem informar o Numero do Abrigo")
    @Tag("Regressao")
    @Order(14)
    public void validarErroABrigoSemNumero() {
        Allure.description("Teste para validar a inclusão de um novo Abrigo sem informar o Numero do Abrigo");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemNumero();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Number is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

}
