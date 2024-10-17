package prat_eng_soft_api_test_automatizado.testCase;

import java.util.HashMap;
import java.util.Map;

import prat_eng_soft_api_test_automatizado.service.GenericService;

public class BaseTestCase {
    protected Map<String, Object> pathParams;
    protected Map<String, Object> queryParams;
    protected GenericService genericService;

    public BaseTestCase( String basePath ) {
        pathParams = new HashMap<>();
        queryParams = new HashMap<>();
        genericService = new GenericService("http://localhost:8080", basePath);
    }

}
