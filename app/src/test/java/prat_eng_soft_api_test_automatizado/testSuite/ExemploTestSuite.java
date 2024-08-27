package prat_eng_soft_api_test_automatizado.testSuite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import prat_eng_soft_api_test_automatizado.testCase.ExemploTestCase;

@Suite
@SelectClasses({ExemploTestCase.class})
@IncludeTags({"Regressao"})
public class ExemploTestSuite {
    
}
