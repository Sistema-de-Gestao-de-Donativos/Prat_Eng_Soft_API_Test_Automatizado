package prat_eng_soft_api_test_automatizado.TestCase;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;

public class AutorizacaoTestCase extends BaseTestCase {

    private GenericValidation genericValidation;
    private ConexaoBancoDados conexaoBancoDados;

    public AutorizacaoTestCase() {
        super("autorizacao", "/v1/autorizacao");
        genericValidation = new GenericValidation();
        conexaoBancoDados = new ConexaoBancoDados();
    }

    public Response chamarMsAbrigo(String token) {
        return genericService.get();
    }

    public Response chamarMsCds(String token) {
        return genericService.get();

    }

    public Response chamarMsDoacao(String token) {
        return genericService.get();
    }

    public Response chamarMsEstoque(String token) {
        return genericService.get();
    }

    public Response chamarMsUsuarios(String token) {
        return genericService.get();
    }

    public Response chamarMsPedidos(String token) {
        return genericService.get();
    }

    @BeforeEach
    public void allureReport(TestInfo testInfo) {
        Allure.epic("Sprint 3");
        Allure.feature("Micro Serviço de Autorização");
    }

    @Test
    @DisplayName("Micro Serviço de Autorização = Validar Token Super Admin")
    public void validarTokenSuperAdmin() {
        chamarMsAbrigo(null); // tem que dar sattsu 2xx
        chamarMsCds(null);// tem que dar sattsu 2xx
        chamarMsDoacao(null);// tem que dar sattsu 2xx
        chamarMsEstoque(null);// tem que dar sattsu 2xx
        chamarMsUsuarios(null);// tem que dar sattsu 2xx
        chamarMsPedidos(null);// tem que dar sattsu 2xx
    }

    @Test
    @DisplayName("Micro Serviço de Autorização = Validar Token Admin CD")
    public void validaTokenAdminCd() {
        chamarMsAbrigo(null); // tem que dar sattsu 2xx
        chamarMsCds(null);// tem que dar sattsu 2xx
        chamarMsDoacao(null);// tem que dar sattsu 2xx
        chamarMsEstoque(null);// tem que dar sattsu 2xx
        chamarMsUsuarios(null);// tem que dar sattsu 2xx
        chamarMsPedidos(null);// tem que dar sattsu 2xx
    }

    @Test
    @DisplayName("Micro Serviço de Autorização = Validar Token Admin Abrigo")
    public void validaTokenAdminAbrigo() {
        chamarMsAbrigo(null); // tem que dar sattsu 2xx
        chamarMsCds(null);// tem que dar sattsu 2xx
        chamarMsDoacao(null);// tem que dar sattsu 2xx
        chamarMsEstoque(null);// tem que dar sattsu 2xx
        chamarMsUsuarios(null);// tem que dar sattsu 2xx
        chamarMsPedidos(null);// tem que dar sattsu 2xx
    }

    @Test
    @DisplayName("Micro Serviço de Autorização = Validar Token Voluntário")
    public void validaTokenVoluntario() {
        chamarMsAbrigo(null); // tem que dar sattsu 2xx
        chamarMsCds(null);// tem que dar sattsu 2xx
        chamarMsDoacao(null);// tem que dar sattsu 2xx
        chamarMsEstoque(null);// tem que dar sattsu 2xx
        chamarMsUsuarios(null);// tem que dar sattsu 2xx
        chamarMsPedidos(null);// tem que dar sattsu 2xx
    }

}
