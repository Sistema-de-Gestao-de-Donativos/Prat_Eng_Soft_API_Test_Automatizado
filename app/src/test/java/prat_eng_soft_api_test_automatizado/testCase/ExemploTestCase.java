package prat_eng_soft_api_test_automatizado.testCase;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.service.ExemploService;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;
import prat_eng_soft_api_test_automatizado.validation.ExemploValidation;

public class ExemploTestCase {

    private Map<String, Object> pathParams;
    private ExemploService exemploService ;
    private ExemploValidation exemploValidation;


    public ExemploTestCase() {
        pathParams = new HashMap<>();
        exemploService = new ExemploService();
        exemploValidation = new ExemploValidation();
    }


    @Test
    public void testExemplo(){
    pathParams.put("nome do parametro", "valor do parametro"); //add os parametros que o endpoint precisa

    Response resposta = exemploService.getEndpoint(pathParams); //chama o endpoint

    exemploValidation.setResponse(resposta); //seta a resposta para validação
    
    exemploValidation.validarStatusCode(HttpStatus.SC_OK); //valida o status code 200
    exemploValidation.validarContrato(ContratoManager.getContrato("exemploContrato")); //valida o contrato

    }


}
