package prat_eng_soft_api_test_automatizado.testSuite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import prat_eng_soft_api_test_automatizado.testCase.AbrigosTestCase;
import prat_eng_soft_api_test_automatizado.testCase.CentroDistribuicaoTestCase;
import prat_eng_soft_api_test_automatizado.testCase.UsuariosTestCase;

@Suite
@SelectClasses({
    AbrigosTestCase.class, 
    CentroDistribuicaoTestCase.class,
    UsuariosTestCase.class
})
@IncludeTags({"Regressao"})
public class RegressaoTestSuite {
    
}
