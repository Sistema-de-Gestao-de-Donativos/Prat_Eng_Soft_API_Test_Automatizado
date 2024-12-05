package prat_eng_soft_api_test_automatizado.TestCase;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
        Allure.description("Primeiro se faz uma requisição para adcionar um abrigo e depois se faz a requisição para listar todos os abrigos, desta forma garantimos que sempre teremos ao menos um abrigo cadastrado para ser listado");
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
        Allure.description("Primeiro se faz uma requisição para adcionar um abrigo e depois se faz a requisição para listar o abrigo pelo seu código, desta forma garantimos que sempre teremos ao menos um abrigo cadastrado para ser listado");
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
        Allure.description("Primeiro se faz uma requisição para adcionar um abrigo e depois se faz a requisição para listar o abrigo pelo seu nome, desta forma garantimos que sempre teremos ao menos um abrigo cadastrado para ser listado");
        Response resposta = abrigoService.casoFelizDeListarAbrigoPeloNome();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Listar abrigo pelo nome e codigo")
    @Tag("Regressao")
    @Order(4)
    public void listarAbrigoPeloNomeEpeloCodigo() {
        Allure.description("Primeiro se faz uma requisição para adcionar um abrigo e depois se faz a requisição para listar o abrigo pelo seu nome e código, desta forma garantimos que sempre teremos ao menos um abrigo cadastrado para ser listado");
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
        Allure.description("Se faz uma requisição para adicionar um abrigo, os dados são gerados de forma aleatória");
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
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar o nome do abrigo, o esperado é que o sistema retorne um erro informando que o nome é obrigatório");
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
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar o Telefone do abrigo, o esperado é que o sistema retorne um erro informando que o Telefone é obrigatório");
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
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar o Email do abrigo, o esperado é que o sistema retorne um erro informando que o Email é obrigatório");
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
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar o Pais do abrigo, o esperado é que o sistema retorne um erro informando que o Pais é obrigatório");
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
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar o Estado do abrigo, o esperado é que o sistema retorne um erro informando que o Estado é obrigatório");
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
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar a Cidade do abrigo, o esperado é que o sistema retorne um erro informando que a Cidade é obrigatória");
        Response resposta = abrigoService.casoErroCriarAbrigoSemCidade();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "City is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar criar um abrigo sem informar o Bairro do Abrigo")
    @Tag("Regressao")
    @Order(12)
    public void validarErroABrigoSemBairro() {
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar o Bairro do abrigo, o esperado é que o sistema retorne um erro informando que o Bairro é obrigatório");
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
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar a Rua do abrigo, o esperado é que o sistema retorne um erro informando que a Rua é obrigatória");
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
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar o Numero do abrigo, o esperado é que o sistema retorne um erro informando que o Numero é obrigatório");
        Response resposta = abrigoService.casoErroCriarAbrigoSemNumero();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_BAD_REQUEST);
        genericValidation.validarMensagem("errors[0]", "Number is required");
        genericValidation.validarContrato(ContratoManager.getContrato("AbrigosErro"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar fazer uma requisição sem informar o token")
    @Tag("Regressao")
    @Order(15)
    public void validarErroSemToken() {
        Allure.description("Se faz uma requisição para adicionar um abrigo sem informar o token, o esperado é que o sistema retorne um erro informando que o token é obrigatório");
        Response resposta = abrigoService.casoErroSemToken();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Validar erro ao tentar fazer uma requisição com token inválido")
    @Tag("Regressao")
    @Order(16)
    public void validarErroTokenInvalido() {
        Allure.description("Se faz uma requisição para adicionar um abrigo com um token inválido, o esperado é que o sistema retorne um erro informando que o token é inválido");
        Response resposta = abrigoService.casoErroTokenInvalido();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_UNAUTHORIZED);
    }

}