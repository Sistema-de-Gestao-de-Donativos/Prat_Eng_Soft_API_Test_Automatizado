package prat_eng_soft_api_test_automatizado.TestCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import io.qameta.allure.Allure;
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

    @BeforeEach
    public void allureReport(TestInfo testInfo) {
        Allure.epic("Sprint 3");
        Allure.feature("Micro Serviço de Autorização");
    }

    //Teste envolvendo super admin

    //Teste envolvendo admin Cd

    //Teste envolvendo admin Abrigo

    //Teste envolvendo voluntarios

}
