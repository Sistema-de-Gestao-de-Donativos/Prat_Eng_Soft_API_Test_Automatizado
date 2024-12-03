package prat_eng_soft_api_test_automatizado.TestCase;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.service.UsuariosService;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;

@TestMethodOrder(OrderAnnotation.class)
public class UsuariosTestCase {

    private GenericValidation genericValidation = new GenericValidation();
    private UsuariosService usuariosService = new UsuariosService();

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
        Allure.feature("Micro Serviço de Usuários");
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Voluntario")
    @Tag("Regressao")
    @Order(1)
    public void incluirUsuarioVoluntario() {
        Allure.description("Teste para validar a inclusão de um novo usuario Voluntario");
        Response resposta = usuariosService.casoFelizIncluIncluirUsuarioVoluntario();
        this.genericValidation.setResponse(resposta);
        this.genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        this.genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Administrador de um Centro de Distribuição")
    @Tag("Regressao")
    @Order(2)
    public void incluirUsuarioAdminCD() {
        Allure.description(
                "Teste para validar a inclusão de um novo usuario Administrador de um Centro de Distribuição");
        Response resposta = usuariosService.casoFelizIncluirUsuarioAdminCD();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Administrador de um Abrigo")
    @Tag("Regressao")
    @Order(3)
    public void incluirUsuarioAdminAbrigo() {
        Allure.description("Teste para validar a inclusão de um novo usuario Administrador de um Abrigo");
        Response resposta = usuariosService.casoFelizIncluirUsuarioAdminAbrigo();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Super Administrador")
    @Tag("Regressao")
    @Order(4)
    public void incluirUsuarioSuperAdmin() {
        Allure.description("Teste para validar a inclusão de um novo usuario Super Administrador");
        Response resposta = usuariosService.casoFelizIncluirUsuarioSuperAdmin();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));
    }

    @ParameterizedTest
    @DisplayName("Micro Serviço de Usuarios = Excluir Usuario")
    @Tag("Regressao")
    @Order(5)
    @ValueSource(strings = { "voluntario", "adminCD", "adminAbrigo", "superadmin" })
    public void excluirUsuario(String role) {
        Allure.description("Teste para validar a exclusão de um Usuario pelo seu Id, sendo ele um usuario " + role);
        Response resposta = usuariosService.casoFelizExcluirUsuario(role);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar todos os Usuarios")
    @Tag("Regressao")
    @Order(6)
    public void listarTodosUsuarios() {
        Allure.description("Teste para validar a listagem de todos os Usuarios já cadastrados");
        Response resposta = usuariosService.casoFelizListarTodosUsuarios();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar todos os Usuarios Voluntaarios")
    @Tag("Regressao")
    @Order(7)
    public void listarTodosUsuariosVoluntarios() {
        Allure.description("Teste para validar a listagem de todos os Usuarios já cadastrados");
        Response resposta = usuariosService.casoFelizListarTodosUsuariosVoluntarios();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar todos os Usuarios adminCD")
    @Tag("Regressao")
    @Order(8)
    public void listarTodosUsuariosAdminCd() {
        Allure.description("Teste para validar a listagem de todos os Usuarios já cadastrados");
        Response resposta = usuariosService.casoFelizListarTodosUsuariosAdminCD();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar todos os Usuarios Admin de Abrigo")
    @Tag("Regressao")
    @Order(9)
    public void listarTodosUsuariosAdminAbrigo() {
        Allure.description("Teste para validar a listagem de todos os Usuarios já cadastrados");
        Response resposta = usuariosService.casoFelizListarTodosUsuariosAdminAbrigo();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar todos os Usuarios super admin")
    @Tag("Regressao")
    @Order(10)
    public void listarTodosUsuariosSuperAdmin() {
        Allure.description("Teste para validar a listagem de todos os Usuarios já cadastrados");
        Response resposta = usuariosService.casoFelizListarTodosUsuariosSuperAdmin();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));
    }

    @ParameterizedTest
    @DisplayName("Micro Serviço de Usuarios = Listar Usuario pelo UserId")
    @Tag("Regressao")
    @Order(11)
    @ValueSource(strings = { "voluntario", "adminCD", "adminAbrigo", "superadmin" })
    public void listarUsuarioPeloUserId(String role) {
        Allure.description("Teste para validar a consulta de um usuario pelo seu Id");
        Response resposta = usuariosService.casoFelizListarUsuarioPorId(role);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarioPeloId"));
    }

    @ParameterizedTest
    @DisplayName("Micro Serviço de Usuarios = Listar Usuarios pelo Papel")
    @Tag("Regressao")
    @Order(12)
    @ValueSource(strings = { "voluntario", "adminCD", "adminAbrigo", "superadmin" })
    public void listarUsuarioPeloPapel(String role) {
        Allure.description("Teste para validar a listagem de um usuario pelo seu papel");
        Response resposta = usuariosService.casoFelizListarUsuarioPeloPapel(role);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));
    }

}
