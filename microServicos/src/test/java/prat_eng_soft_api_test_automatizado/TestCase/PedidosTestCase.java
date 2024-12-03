package prat_eng_soft_api_test_automatizado.TestCase;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.service.PedidosService;


public class PedidosTestCase  {

    private GenericValidation genericValidation= new GenericValidation();
    private PedidosService pedidosService = new PedidosService();

    /*
     * @BeforeAll
     * public void getToken(){
     * AuthService authService = new AuthService("baseuri", "rota", "clientid",
     * "clientsecret");
     * String Token = authService.getToken();
     * genericService.addHeader("Authorization", Token);
     * }
     */

    @BeforeEach
    public void allureReport(TestInfo testInfo) {
        Allure.epic("Sprint 3");
        Allure.feature("Micro Serviço de Pedidos");
    }

    @Test
    public void incluirNovoPedido() {
      Response response = pedidosService.casoFelizIncluirPedido();
      genericValidation.setResponse(response);
      genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void consultarPedido() {

        Response response = pedidosService.casoFelizConsultarPedido();
        genericValidation.setResponse(response);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);

    }

    @Test
    public void atualizarPedido() {
    Response response = pedidosService.casoFelizAtualizarPedido();
      genericValidation.setResponse(response);
      genericValidation.validarStatusCode(HttpStatus.SC_OK);

    }

}
