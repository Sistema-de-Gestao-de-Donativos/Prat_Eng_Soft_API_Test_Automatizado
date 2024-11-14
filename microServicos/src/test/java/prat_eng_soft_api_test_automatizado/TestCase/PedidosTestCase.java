package prat_eng_soft_api_test_automatizado.TestCase;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.TestCase.models.Pedidos;
import prat_eng_soft_api_test_automatizado.TestCase.models.PedidosItens;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;

public class PedidosTestCase extends BaseTestCase {

    private GenericValidation genericValidation;
    private ConexaoBancoDados conexaoBancoDados;


    public PedidosTestCase() {
        super("pedidos", "/v1/pedidos");
        genericValidation = new GenericValidation();
        conexaoBancoDados = new ConexaoBancoDados();

    }

     /*  @BeforeAll
    public void getToken(){
        AuthService authService = new AuthService("baseuri", "rota");
        String Token = authService.getToken();
        genericService.addHeader("Authorization", "Bearer " + Token);
    }*/


    @BeforeEach
    public void allureReport(TestInfo testInfo) {
        Allure.epic("Sprint 3");
        Allure.feature("Micro Serviço de Pedidos");
    }

    @Test
    public void incluirNovoPedido() {

        PedidosItens pedidosItens = new PedidosItens("12367236d3ba9bb45662982031445", "67236d3ba9bb456629820314", 759);
      //PedidosItens pedidosItens2 = new PedidosItens("2", "2", 2);

        List<PedidosItens> itens = new ArrayList<>();
        itens.add(pedidosItens);
      //  itens.add(pedidosItens2);

        Pedidos pedidos = new Pedidos("1", "1", itens);

        genericService.setBody(pedidos);
        Response response = genericService.post();
        genericValidation.setResponse(response);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);




    }

    @Test
    public void consultarPedido() {

        Response response = genericService.get();
        genericValidation.setResponse(response);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);



    }

    @Test
    public void atualizarPedido() {

    }
    
}
