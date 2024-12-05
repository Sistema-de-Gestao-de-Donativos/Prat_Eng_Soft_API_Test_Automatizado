package prat_eng_soft_api_test_automatizado.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.models.Pedidos;
import prat_eng_soft_api_test_automatizado.utils.models.PedidosItens;

public class PedidosService extends GenericService {

    private static String getBaseUri() {
        Properties propriedades;
        try {
            propriedades = ConexaoBancoDados.getProperties("rotas");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar carregar arquivo com as Rotas", e);
        }
        return propriedades.getProperty("pedidos");
    }

    private static String getToken() {
        Properties propriedades;
        try {
            propriedades = ConexaoBancoDados.getProperties("TOKEN");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar carregar arquivo com as Rotas", e);
        }
        return propriedades.getProperty("tokenJava");
    }

    private void montarRequisicao() {
        setToken(getToken());
        setBaseUri(getBaseUri());
        setBasePath("/v1/pedidos");
    }

    public Response casoFelizIncluirPedido() {
        EstoqueService estoqueService = new EstoqueService();
        Response resposta = estoqueService.casoFelizEntradaStock("5");
        resposta.then().statusCode(201);
        resposta.then().log().all();
        prepararParaNovaRequisicao();
        montarRequisicao();
        PedidosItens pedidosItens = new PedidosItens(resposta.jsonPath().getString("[0].nome"),
                resposta.jsonPath().getString("[0]._id"), resposta.jsonPath().getInt("[0].quantidade"));
        List<PedidosItens> listapedidosItens = new ArrayList<>();
        listapedidosItens.add(pedidosItens);
        Pedidos pedidos = new Pedidos("5", "5", listapedidosItens);
        setBody(pedidos);
        return post();
    }

    public Response casoFelizAtualizarPedido() {
        Response response = casoFelizIncluirPedido();
        response.then().statusCode(201);
        prepararParaNovaRequisicao();
        Response response2 = casoFelizConsultarPedido();
        response2.then().statusCode(200);
        prepararParaNovaRequisicao();
        String body = "{\"codPedido\": \"" + response2.jsonPath().getString("[0].codPedido")
                + "\", \"status\": \"CANCELADO\" }";
        setBody(body);
        return put();
    }

    public Response casoFelizConsultarPedido() {
        montarRequisicao();
        return get();
    }

    public Response casoErroConsultaSEmToken() {
        montarRequisicao();
        removeHeader("Authorization");

        return get();
    }

    public Response casoErroConsultaComTokenInvalido() {
        montarRequisicao();
        setToken("invalido");
        return get();
    }

}
