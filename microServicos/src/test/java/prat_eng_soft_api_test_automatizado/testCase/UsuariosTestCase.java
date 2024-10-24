package prat_eng_soft_api_test_automatizado.testCase;

import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.DTO.UsuariosAddressDTO;
import prat_eng_soft_api_test_automatizado.DTO.UsuariosDTO;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;
import prat_eng_soft_api_test_automatizado.utils.GeradorCpf;
import prat_eng_soft_api_test_automatizado.validation.GenericValidation;

public class UsuariosTestCase extends BaseTestCase {

    private GenericValidation genericValidation;

    public UsuariosTestCase() {
        super("http://127.0.0.1:8000", "/v1");
        genericValidation = new GenericValidation();
    }

    @Test
    @DisplayName("Ms de Usuarios = Incluir Usuario Voluntario")
    @Tag("Regressao")
    public void incluirUsuarioVoluntario() {
        Faker faker = new Faker(new Locale("pt-BR"));
        UsuariosAddressDTO usuariosAddressDTO = new UsuariosAddressDTO("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));

        UsuariosDTO usuariosDTO = new UsuariosDTO(faker.leagueOfLegends().champion(), usuariosAddressDTO,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(), "voluntario", faker.number().numberBetween(1, 1000),
                GeradorCpf.gerarCpf());

        Response resposta = genericService.post("/users", pathParams, usuariosDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));
    }

    @Test
    @DisplayName("Ms de Usuarios = Incluir Usuario Admin CD")
    @Tag("Regressao")
    public void incluirUsuarioAdminCD() {
        Faker faker = new Faker(new Locale("pt-BR"));
        UsuariosAddressDTO usuariosAddressDTO = new UsuariosAddressDTO("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));

        UsuariosDTO usuariosDTO = new UsuariosDTO(faker.leagueOfLegends().champion(), usuariosAddressDTO,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(), "adminCD", faker.number().numberBetween(1, 1000),
                GeradorCpf.gerarCpf());

        Response resposta = genericService.post("/users", pathParams, usuariosDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));
    }

    @Test
    @DisplayName("Ms de Usuarios = Incluir Usuario Admin Abrigo")
    @Tag("Regressao")
    public void incluirUsuarioAdminAbrigo() {
        Faker faker = new Faker(new Locale("pt-BR"));
        UsuariosAddressDTO usuariosAddressDTO = new UsuariosAddressDTO("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));

        UsuariosDTO usuariosDTO = new UsuariosDTO(faker.leagueOfLegends().champion(), usuariosAddressDTO,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(), "adminAbrigo", faker.number().numberBetween(1, 1000),
                GeradorCpf.gerarCpf());

        Response resposta = genericService.post("/users", pathParams, usuariosDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));
    }

    @Test
    @DisplayName("Ms de Usuarios = Incluir Usuario Super Admin")
    @Tag("Regressao")
    public void incluirUsuarioSuperAdmin() {
        Faker faker = new Faker(new Locale("pt-BR"));
        UsuariosAddressDTO usuariosAddressDTO = new UsuariosAddressDTO("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));

        UsuariosDTO usuariosDTO = new UsuariosDTO(faker.leagueOfLegends().champion(), usuariosAddressDTO,
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(), "superadmin", faker.number().numberBetween(1, 1000),
                GeradorCpf.gerarCpf());

        Response resposta = genericService.post("/users", pathParams, usuariosDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("incluirUsuario"));
    }

    @Test
    @DisplayName("Ms de Usuarios = Excluir Usuario")
    @Tag("Regressao")
    public void excluirUsuario() {
        pathParams.put("user_id", "671723125971092b1dccd207");
        Response resposta = genericService.delete("/users/{user_id}", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Ms de Usuarios = Listar todos os Usuarios")
    @Tag("Regressao")
    public void listarTodosUsuarios() {
        Response resposta = genericService.get("users/", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));
    }

    @Test
    @DisplayName("Ms de Usuarios = Listar Usuario pelo UserId")
    @Tag("Regressao")
    public void listarUsuarioPeloUserId() {
        pathParams.put("user_id", "67156701c3d61381677502e3");
        Response resposta = genericService.get("users/{user_id}", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarioPeloId"));
    }

    @Test
    @DisplayName("Ms de Usuarios = Listar Usuario pelo Papel( Voluntario, AdminCD, AdminAbrigo, SuperAdmin)")
    @Tag("Regressao")
    public void listarUsuarioPeloPapel() {
        pathParams.put("role", "voluntario");
        pathParams.put("codEntidade", "12");
        Response resposta = genericService.get("users/{role}/{codEntidade}", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("listarUsuarios"));
    }

}
