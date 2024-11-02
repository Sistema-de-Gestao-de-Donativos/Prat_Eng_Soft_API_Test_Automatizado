package prat_eng_soft_api_test_automatizado.TestCase;

import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;

import com.github.javafaker.Faker;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.TestCase.models.Usuarios;
import prat_eng_soft_api_test_automatizado.TestCase.models.UsuariosAddress;
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
        super("http://127.0.0.1:8000", "/v1");
        genericValidation = new GenericValidation();
        conexaoBancoDados = new ConexaoBancoDados();
    }

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

        UsuariosAddress usuariosAddressDTO = new UsuariosAddress("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));

        Usuarios usuariosDTO = new Usuarios(nome, usuariosAddressDTO,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(), "voluntario", faker.number().numberBetween(1, 10),
                cpf);

        Response resposta = genericService.post("/users", pathParams, usuariosDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));

        conexaoBancoDados.encontrarUsuario(resposta.jsonPath().getString("_id"));

    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Administrador de um Centro de Distribuição")
    @Tag("Regressao")
    @Order(2)
    public void incluirUsuarioAdminCD() {
        Allure.description(
                "Teste para validar a inclusão de um novo usuario Administrador de um Centro de Distribuição");

        Faker faker = new Faker(new Locale("pt-BR"));
        UsuariosAddress usuariosAddressDTO = new UsuariosAddress("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));

        Usuarios usuariosDTO = new Usuarios(nome, usuariosAddressDTO,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(), "adminCD", faker.number().numberBetween(1, 10),
                cpf);

        Response resposta = genericService.post("/users", pathParams, usuariosDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));

        conexaoBancoDados.encontrarUsuario(resposta.jsonPath().getString("_id"));

    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Administrador de um Abrigo")
    @Tag("Regressao")
    @Order(3)
    public void incluirUsuarioAdminAbrigo() {
        Allure.description("Teste para validar a inclusão de um novo usuario Administrador de um Abrigo");

        Faker faker = new Faker(new Locale("pt-BR"));
        UsuariosAddress usuariosAddressDTO = new UsuariosAddress("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));

        Usuarios usuariosDTO = new Usuarios(nome, usuariosAddressDTO,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(), "adminAbrigo", faker.number().numberBetween(1, 10),
                cpf);

        Response resposta = genericService.post("/users", pathParams, usuariosDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));

        conexaoBancoDados.encontrarUsuario(resposta.jsonPath().getString("_id"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Incluir Usuario Super Administrador")
    @Tag("Regressao")
    @Order(4)
    public void incluirUsuarioSuperAdmin() {
        Allure.description("Teste para validar a inclusão de um novo usuario Super Administrador");

        Faker faker = new Faker(new Locale("pt-BR"));
        UsuariosAddress usuariosAddressDTO = new UsuariosAddress("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));

        Usuarios usuariosDTO = new Usuarios(nome, usuariosAddressDTO,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(), "superadmin", faker.number().numberBetween(1, 10),
                cpf);

        Response resposta = genericService.post("/users", pathParams, usuariosDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));

        conexaoBancoDados.encontrarUsuario(resposta.jsonPath().getString("_id"));
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Excluir Usuario")
    @Tag("Regressao")
    @Order(5)
    public void excluirUsuario() {
        /** Incluindo um usuario voluntario na mão pra depois apagar ele */
        String userId = conexaoBancoDados.incluirUsuario();

        Allure.description(
                "Teste para validar a exclusão de um Usuario");

        pathParams.put("user_id", userId);
        Response resposta = genericService.delete("/users/{user_id}", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_NO_CONTENT);

        conexaoBancoDados.encontrarUsuario(userId);
    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar todos os Usuarios")
    @Tag("Regressao")
    @Order(6)
    public void listarTodosUsuarios() {
        Allure.description("Teste para validar a listagem de todos os Usuarios já cadastrados");

        Response resposta = genericService.get("users/", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));

    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar Usuario pelo UserId")
    @Tag("Regressao")
    @Order(7)
    public void listarUsuarioPeloUserId() {
        /** Incluindo um usuario voluntario na mão pra depois apagar ele */
        String userId = conexaoBancoDados.incluirUsuario();

        Allure.description("Teste para validar a consulta de um usuario pelo seu Id");

        pathParams.put("user_id", userId);
        Response resposta = genericService.get("users/{user_id}", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarioPeloId"));
        conexaoBancoDados.encontrarUsuario(userId);

    }

    @Test
    @DisplayName("Micro Serviço de Usuarios = Listar Usuarios pelo Papel( Voluntario, AdminCD, AdminAbrigo, SuperAdmin)")
    @Tag("Regressao")
    @Order(8)
    public void listarUsuarioPeloPapel() {
        Allure.description("Teste para validar a listagem de um usuario pelo seu papel");

        pathParams.put("role", "voluntario");
        pathParams.put("codEntidade", "12");
        Response resposta = genericService.get("users/{role}/{codEntidade}", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));

    }

}
