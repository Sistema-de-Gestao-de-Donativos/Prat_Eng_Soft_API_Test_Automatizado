package prat_eng_soft_api_test_automatizado.service;

import java.util.Properties;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.TestCase.models.Usuarios;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.UsuariosBancoDados;

public class UsuariosService extends GenericService {

    private UsuariosBancoDados usuariosBancoDados = new UsuariosBancoDados();

    private static String getBaseUri() {
        Properties propriedades;
        try {
            propriedades = ConexaoBancoDados.getProperties("rotas");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar carregar arquivo com as Rotas", e);
        }
        return propriedades.getProperty("usuario");
    }

    private void montarRequisicao() {
        setBaseUri(getBaseUri());
        setBasePath("/v1");
    }

    private Usuarios montarUsuario() {
        return Usuarios.criarUsuario(usuariosBancoDados.getNome(), usuariosBancoDados.getCpf());
    }

    public Response casoFelizIncluIncluirUsuarioVoluntario() {
        montarRequisicao();
        Usuarios usuariosModel = montarUsuario();
        usuariosModel.setRole("voluntario");
        setRota("/users");
        setBody(usuariosModel);
        return post();
    }

    public Response casoFelizIncluirUsuarioAdminCD() {
        montarRequisicao();
        Usuarios usuariosModel = montarUsuario();
        usuariosModel.setRole("adminCD");
        setRota("/users");
        setBody(usuariosModel);
        return post();
    }

    public Response casoFelizIncluirUsuarioAdminAbrigo() {
        montarRequisicao();
        Usuarios usuariosModel = montarUsuario();
        usuariosModel.setRole("adminAbrigo");
        setRota("/users");
        setBody(usuariosModel);
        return post();
    }

    public Response casoFelizIncluirUsuarioSuperAdmin() {
        montarRequisicao();
        Usuarios usuariosModel = montarUsuario();
        usuariosModel.setRole("superadmin");
        setRota("/users");
        setBody(usuariosModel);
        return post();
    }

    public Response casoFelizExcluirUsuario(String role) {
        montarRequisicao();
        String userId = usuariosBancoDados.incluirUsuario(role);
        addPathParam("user_id", userId);
        setRota("/users/{user_id}");
        return delete();
    }

    public Response casoFelizListarTodosUsuarios() {
        montarRequisicao();
        setRota("/users/");
        return get();
    }

    public Response casoFelizListarTodosUsuariosVoluntarios() {
        montarRequisicao();
        addQueryParams("role", "voluntario");
        setRota("/users/");
        return get();
    }

    public Response casoFelizListarTodosUsuariosAdminCD() {
        montarRequisicao();
        addQueryParams("role", "adminCD");
        setRota("/users/");
        return get();
    }

    public Response casoFelizListarTodosUsuariosAdminAbrigo() {
        montarRequisicao();
        addQueryParams("role", "adminAbrigo");
        setRota("/users/");
        return get();
    }

    public Response casoFelizListarTodosUsuariosSuperAdmin() {
        montarRequisicao();
        addQueryParams("role", "superadmin");
        setRota("/users/");
        return get();
    }

    public Response casoFelizListarUsuarioPorId(String role) {
        montarRequisicao();
        String userId = usuariosBancoDados.incluirUsuario(role);
        addPathParam("user_id", userId);
        setRota("/users/{user_id}");
        return get();
    }

    public Response casoFelizListarUsuarioPeloPapel(String role) {
        montarRequisicao();
        usuariosBancoDados.incluirUsuario(role);
        addPathParam("role", role);
        addPathParam("codEntidade", "1");
        setRota("/users/{role}/{codEntidade}");
        return get();
    }
}
