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
        Allure.description("Primeiro se faz uma requisição para adcionar um Centro De Distribuição e depois se faz a requisição para listar todos os Centro de Distribuição, desta forma garantindo que sempre haverá ao menos um Centro de Distribuição para ser listado");
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
        Allure.description("Primeiro se faz uma requisição para adcionar um Centro De Distribuição e depois se faz a requisição para listar um Centro de Distribuição pelo seu código, desta forma garantindo que sempre haverá ao menos um Centro de Distribuição para ser listado");
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
        Allure.description("Primeiro se faz uma requisição para adcionar um Centro De Distribuição e depois se faz a requisição para listar um Centro de Distribuição pelo seu nome, desta forma garantindo que sempre haverá ao menos um Centro de Distribuição para ser listado");
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
        Allure.description("Primeiro se faz uma requisição para adcionar um Centro De Distribuição e depois se faz a requisição para listar um Centro de Distribuição pelo seu nome e pelo seu código, desta forma garantindo que sempre haverá ao menos um Centro de Distribuição para ser listado");
        Response resposta = centroDistribuicaoService.casoFelizDeListarCentroDistribuicaoPeloNomeECodigo();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarCDCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Incluir Centro de Distribuição")
    @Tag("Regressao")
    @Order(5)
    public void adicionarCD() {
        Allure.description("Se faz uma requisição para adicionar um Centro De Distribuição, os dados são gerados de forma aleatória");
        Response resposta = centroDistribuicaoService.casoFelizAdcionarCentroDistribuicao();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirCD"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição sem informar o Telefone do Centro de Distribuição")
    @Tag("Regressao")
    @Order(7)
    public void validarErroCdSemInformarTelefone() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição sem informar o telefone do Centro de Distribuição, o esperado é que o sistema retorne um erro informando que o nome do Centro de Distribuição é obrigatório");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemTelefone();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Phone is required");
        //genericValidation.validarContrato(ContratoManager.getContrato("Centro de DistribuiçãosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição sem informar o Email do Centro de Distribuição")
    @Tag("Regressao")
    @Order(8)
    public void validarErroCentrodeDistribuiçãoSemEmail() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição sem informar o email do Centro de Distribuição, o esperado é que o sistema retorne um erro informando que o nome do Centro de Distribuição é obrigatório");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemEmail();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Email is required");
        //genericValidation.validarContrato(ContratoManager.getContrato("Centro de DistribuiçãosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição sem informar o Pais do Centro de Distribuição")
    @Tag("Regressao")
    @Order(9)
    public void validarErroCentrodeDistribuiçãoSemPais() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição sem informar o Pais do Centro de Distribuição, o esperado é que o sistema retorne um erro informando que o nome do Centro de Distribuição é obrigatório");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemPais();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Country is required");
        //genericValidation.validarContrato(ContratoManager.getContrato("Centro de DistribuiçãosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição sem informar o Estado do Centro de Distribuição")
    @Tag("Regressao")
    @Order(10)
    public void validarErroCentrodeDistribuiçãoSemEstado() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição sem informar o Estado do Centro de Distribuição, o esperado é que o sistema retorne um erro informando que o nome do Centro de Distribuição é obrigatório");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemEstado();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "State is required");
        //genericValidation.validarContrato(ContratoManager.getContrato("Centro de DistribuiçãosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição sem informar a Cidade do Centro de Distribuição")
    @Tag("Regressao")
    @Order(11)
    public void validarErroCentrodeDistribuiçãoSemCidade() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição sem informar a Cidade do Centro de Distribuição, o esperado é que o sistema retorne um erro informando que o nome do Centro de Distribuição é obrigatório");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemCidade();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "City is required");
        //genericValidation.validarContrato(ContratoManager.getContrato("Centro de DistribuiçãosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição sem informar o Bairo do Centro de Distribuição")
    @Tag("Regressao")
    @Order(12)
    public void validarErroCentrodeDistribuiçãoSemBairro() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição sem informar o Bairro do Centro de Distribuição, o esperado é que o sistema retorne um erro informando que o nome do Centro de Distribuição é obrigatório");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemBairro();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Neighborhood is required");
        //genericValidation.validarContrato(ContratoManager.getContrato("Centro de DistribuiçãosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição sem informar a Rua do Centro de Distribuição")
    @Tag("Regressao")
    @Order(13)
    public void validarErroCentrodeDistribuiçãoSemRua() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição sem informar a Rua do Centro de Distribuição, o esperado é que o sistema retorne um erro informando que o nome do Centro de Distribuição é obrigatório");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemRua();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Street is required");
        //genericValidation.validarContrato(ContratoManager.getContrato("Centro de DistribuiçãosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição sem informar o Numero do Centro de Distribuição")
    @Tag("Regressao")
    @Order(14)
    public void validarErroCentrodeDistribuiçãoSemNumero() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição sem informar o Numero do Centro de Distribuição, o esperado é que o sistema retorne um erro informando que o nome do Centro de Distribuição é obrigatório");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemNumero();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Number is required");
        //genericValidation.validarContrato(ContratoManager.getContrato("Centro de DistribuiçãosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição sem informar o Token")
    @Tag("Regressao")
    @Order(15)
    public void validarErroCentrodeDistribuiçãoSemToken() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição sem informar o Token, o esperado é que o sistema retorne um erro informando que o Token é obrigatório");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoSemToken();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Validar erro ao tentar criar um Centro de Distribuição informando um Token inválido")
    @Tag("Regressao")
    @Order(16)
    public void validarErroCentrodeDistribuiçãoTokenInvalido() {
        Allure.description("Se faz uma requisição para adicionar um Centro de Distribuição informando um Token inválido, o esperado é que o sistema retorne um erro informando que o Token é inválido");
        Response resposta = centroDistribuicaoService.casoErroCriarCentroDistribuicaoComTokenInvalido();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_UNAUTHORIZED);
    }

}
