package prat_eng_soft_api_test_automatizado.testSuite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import prat_eng_soft_api_test_automatizado.testCase.AbrigosTestCase;

@Suite
@SelectClasses({AbrigosTestCase.class})
@IncludeTags({"Regressao"})
public class AbrigoRegressaoTestSuite {
    
}
