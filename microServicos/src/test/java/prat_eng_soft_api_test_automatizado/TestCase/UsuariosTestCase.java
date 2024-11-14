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
import prat_eng_soft_api_test_automatizado.TestCase.models.Usuarios;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;
import prat_eng_soft_api_test_automatizado.utils.GeradorCpf;

@TestMethodOrder(OrderAnnotation.class)
public class UsuariosTestCase extends BaseTestCase {

    private GenericValidation genericValidation;
    private ConexaoBancoDados conexaoBancoDados;
    private String nome;
    private String cpf;

    public UsuariosTestCase() {
        super("usuario", "/v1");
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
        Allure.epic("Sprint 1");
        Allure.feature("Micro Serviço de Usuários");
    }

    @BeforeEach
    public void setUp() {
        nome = faker.name().fullName();
        while (conexaoBancoDados.encontrarUsuarioPeloNome(nome)) {
            nome = faker.name().fullName();
        }
        cpf = GeradorCpf.gerarCpfSemFormatacao();
        while (conexaoBancoDados.encontrarUsuarioPeloCpf(cpf)) {
            cpf = GeradorCpf.gerarCpfSemFormatacao();
        }
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Voluntario")
    @Tag("Regressao")
    @Order(1)
    public void incluirUsuarioVoluntario() {

        Allure.description("Teste para validar a inclusão de um novo usuario Voluntario");

        Usuarios usuariosModel = Usuarios.criarUsuario(nome, cpf);
        usuariosModel.setRole("voluntario");

        this.genericService.setRota("/users");
        this.genericService.setBody(usuariosModel);

        Response resposta = genericService.post();
        this.genericValidation.setResponse(resposta);
        this.genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        this.genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));

        this.conexaoBancoDados.encontrarUsuarioId(resposta.jsonPath().getString("_id"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Administrador de um Centro de Distribuição")
    @Tag("Regressao")
    @Order(2)
    public void incluirUsuarioAdminCD() {
        Allure.description(
                "Teste para validar a inclusão de um novo usuario Administrador de um Centro de Distribuição");

        Usuarios usuariosModel = Usuarios.criarUsuario(nome, cpf);
        usuariosModel.setRole("adminCD");

        this.genericService.setRota("/users");
        this.genericService.setBody(usuariosModel);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));

        conexaoBancoDados.encontrarUsuarioId(resposta.jsonPath().getString("_id"));

    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Administrador de um Abrigo")
    @Tag("Regressao")
    @Order(3)
    public void incluirUsuarioAdminAbrigo() {
        Allure.description("Teste para validar a inclusão de um novo usuario Administrador de um Abrigo");

        Usuarios usuariosModel = Usuarios.criarUsuario(nome, cpf);
        usuariosModel.setRole("adminAbrigo");

        this.genericService.setRota("/users");
        this.genericService.setBody(usuariosModel);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));

        conexaoBancoDados.encontrarUsuarioId(resposta.jsonPath().getString("_id"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Super Administrador")
    @Tag("Regressao")
    @Order(4)
    public void incluirUsuarioSuperAdmin() {
        Allure.description("Teste para validar a inclusão de um novo usuario Super Administrador");

        Usuarios usuariosModel = Usuarios.criarUsuario(nome, cpf);
        usuariosModel.setRole("superadmin");

        this.genericService.setRota("/users");
        this.genericService.setBody(usuariosModel);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));

        conexaoBancoDados.encontrarUsuarioId(resposta.jsonPath().getString("_id"));
    }

    @ParameterizedTest
    @DisplayName("Micro Serviço de Usuarios = Excluir Usuario")
    @Tag("Regressao")
    @Order(5)
    @ValueSource(strings = { "voluntario", "adminCD", "adminAbrigo", "superadmin" })
    public void excluirUsuario(String role) {
        /** Incluindo um usuario voluntario na mão pra depois apagar ele */
        String userId = conexaoBancoDados.incluirUsuario(role);

        Allure.description(
                "Teste para validar a exclusão de um Usuario pelo seu Id, sendo ele um usuario " + role);

        genericService.addPathParam("user_id", userId);
        genericService.setRota("/users/{user_id}");

        Response resposta = genericService.delete();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_NO_CONTENT);

        conexaoBancoDados.encontrarUsuarioId(userId);
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar todos os Usuarios")
    @Tag("Regressao")
    @Order(6)
    public void listarTodosUsuarios() {
        Allure.description("Teste para validar a listagem de todos os Usuarios já cadastrados");

        genericService.setRota("/users/");

        Response resposta = genericService.get();
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

        genericService.addQueryParams("role", "voluntario");
        genericService.setRota("/users/");

        Response resposta = genericService.get();
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

        genericService.addQueryParams("role", "adminCD");
        genericService.setRota("/users/");

        Response resposta = genericService.get();
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

        genericService.addQueryParams("role", "adminAbrigo");
        genericService.setRota("/users/");

        Response resposta = genericService.get();
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

        genericService.addQueryParams("role", "superadmin");
        genericService.setRota("/users/");

        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));

    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar Usuario pelo UserId")
    @Tag("Regressao")
    @Order(11)
    public void listarUsuarioPeloUserId() {
        /** Incluindo um usuario voluntario na mão pra depois apagar ele */
        String userId = conexaoBancoDados.incluirUsuario("voluntario");

        Allure.description("Teste para validar a consulta de um usuario pelo seu Id");

        genericService.addPathParam("user_id", userId);
        genericService.setRota("/users/{user_id}");

        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarioPeloId"));
        conexaoBancoDados.encontrarUsuarioId(userId);

    }

    @ParameterizedTest
    @DisplayName("Micro Serviço de Usuarios = Listar Usuarios pelo Papel")
    @Tag("Regressao")
    @Order(12)
    @ValueSource(strings = { "voluntario", "adminCD", "adminAbrigo", "superadmin" })
    public void listarUsuarioPeloPapel(String role) {

        conexaoBancoDados.incluirUsuario(role);

        Allure.description("Teste para validar a listagem de um usuario pelo seu papel");

        genericService.addPathParam("role", role);
        genericService.addPathParam("codEntidade", "1");
        genericService.setRota("/users/{role}/{codEntidade}");

        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));

    }

}
