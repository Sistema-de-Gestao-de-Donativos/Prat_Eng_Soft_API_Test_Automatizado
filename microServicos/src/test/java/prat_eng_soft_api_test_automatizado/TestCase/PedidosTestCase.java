package prat_eng_soft_api_test_automatizado.TestCase;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.service.PedidosService;

@TestMethodOrder(OrderAnnotation.class)

public class PedidosTestCase {

    private GenericValidation genericValidation = new GenericValidation();
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
    @DisplayName("Micro Serviço de Pedidos = Incluir novo Pedido")
    @Tag("Regressao")
    @Order(1)
    public void incluirNovoPedido() {
        Allure.description("Teste para validar a inclusão de um novo pedido");
        Response response = pedidosService.casoFelizIncluirPedido();
        genericValidation.setResponse(response);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @DisplayName("Micro Serviço de Pedidos = Consultar pedidos")
    @Tag("Regressao")
    @Order(2)
    public void consultarPedido() {
        Allure.description("Teste para validar a consulta de um pedido");
        Response response = pedidosService.casoFelizConsultarPedido();
        genericValidation.setResponse(response);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Micro Serviço de Pedidos = Atualizar status de um Pedido")
    @Tag("Regressao")
    @Order(3)
    public void atualizarPedido() {
        Allure.description("Teste para validar a troca do Status de um pedido");
        Response response = pedidosService.casoFelizAtualizarPedido();
        genericValidation.setResponse(response);
        genericValidation.validarStatusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Micro Serviço de Pedidos = Validar erro ao Consultar pedidos sem informar o token")
    @Tag("Regressao")
    @Order(6)
    @Disabled("Falta atualizar o servidor com a versão correta")
    public void buscarDoadorSemToken() {
        Allure.description("Teste para validar a Consultar pedidos sem informar o token");
        Response resposta = pedidosService.casoErroConsultaSEmToken();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_FORBIDDEN);
    }
  
    @Test
    @DisplayName("Micro Serviço de Pedidos = Validar erro ao Consultar pedidos informando o token inválido")
    @Tag("Regressao")
    @Order(7)
    @Disabled("Falta atualizar o servidor com a versão correta")
    public void buscarDoadorComTokenInvalido() {
        Allure.description("Teste para validar a Consultar pedidos informando um token inválido");
        Response resposta = pedidosService.casoErroConsultaComTokenInvalido();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_UNAUTHORIZED);
    }


}
