package prat_eng_soft_api_test_automatizado.TestSuite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import prat_eng_soft_api_test_automatizado.TestCase.AbrigosTestCase;
import prat_eng_soft_api_test_automatizado.TestCase.CentroDistribuicaoTestCase;
import prat_eng_soft_api_test_automatizado.TestCase.DoacaoTestCase;
import prat_eng_soft_api_test_automatizado.TestCase.EstoqueTestCase;
import prat_eng_soft_api_test_automatizado.TestCase.UsuariosTestCase;

@Suite
@SelectClasses({
    AbrigosTestCase.class, 
    CentroDistribuicaoTestCase.class,
    UsuariosTestCase.class,
    DoacaoTestCase.class,
    EstoqueTestCase.class
})
@IncludeTags({"Regressao"})
public class RegressaoTestSuite {
    
}
