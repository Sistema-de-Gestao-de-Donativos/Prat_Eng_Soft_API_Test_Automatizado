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
import prat_eng_soft_api_test_automatizado.TestCase.models.CentroDistribuicao;
import prat_eng_soft_api_test_automatizado.TestCase.models.CentroDistribuicaoAddress;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;

@TestMethodOrder(OrderAnnotation.class)
public class CentroDistribuicaoTestCase extends BaseTestCase {

    private GenericValidation genericValidation;
    private ConexaoBancoDados conexaoBancoDados;

    public CentroDistribuicaoTestCase() {
        super("cd", "/v1/cds");
        genericValidation = new GenericValidation();
        conexaoBancoDados = new ConexaoBancoDados();
    }

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
        Response resposta = genericService.get();
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
        genericService.addQueryParams("codCD", 1);
        Response resposta = genericService.get();
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
        genericService.addQueryParams("nameCD", "Cho-Gath");
        Response resposta = genericService.get();
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

        CentroDistribuicao centroDistribuicaoDTO = CentroDistribuicao.criarCentroDistribuicao();

        genericService.setBody(centroDistribuicaoDTO);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirCD"));
        conexaoBancoDados.encontrarCD(resposta.jsonPath().getString("code"));

    }

    // Falta incluir os testes de cenaŕio de erro

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = validar erro ao tentar criar um Centro de Distribuição sem informar o telefone")
    @Tag("Regressao")
    @Order(5)
    public void validarErroCDSemTelefone() {
        Allure.description("Teste para validar a inclusão de um novo Centro de Distribuição sem informar o telefone");

        CentroDistribuicao centroDistribuicaoDTO = CentroDistribuicao.criarCentroDistribuicao();
        centroDistribuicaoDTO.setPhone("");

        genericService.setBody(centroDistribuicaoDTO);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarCampo("errors[0]", "Phone is required");
        genericValidation.validarContrato(ContratoManager.getContrato("CdsErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = validar erro ao tentar criar um Centro de Distribuição sem informar a vizinhança")
    @Tag("Regressao")
    @Order(6)
    public void validarErroCDSemVizinhanca() {
        Allure.description("Teste para validar a inclusão de um novo Centro de Distribuição sem informar a vizinhança");

        CentroDistribuicaoAddress endereco = CentroDistribuicaoAddress.criarEndereco();
        endereco.setNeighborhood("");

        CentroDistribuicao centroDistribuicaoDTO = CentroDistribuicao.criarCentroDistribuicao();
        centroDistribuicaoDTO.setAddress(endereco);

        genericService.setBody(centroDistribuicaoDTO);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarCampo("errors[0]", "Neighborhood is required");
        genericValidation.validarContrato(ContratoManager.getContrato("CdsErro"));
    }
}
