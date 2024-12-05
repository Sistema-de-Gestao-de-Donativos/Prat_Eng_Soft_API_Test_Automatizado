package prat_eng_soft_api_test_automatizado.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.models.Doacao;
import prat_eng_soft_api_test_automatizado.utils.models.DoacaoDoador;
import prat_eng_soft_api_test_automatizado.utils.models.DoacaoItens;

public class DoacaoService extends GenericService {

    private String email;

    private static String getBaseUri() {
        Properties propriedades;
        try {
            propriedades = ConexaoBancoDados.getProperties("rotas");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar carregar arquivo com as Rotas", e);
        }
        return propriedades.getProperty("doacao");
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
        setBasePath("/v1");
    }

    public Response casoFelizIncluirDoador() {
        montarRequisicao();
        DoacaoDoador doacaoDoador = DoacaoDoador.criarDoador();
        email = doacaoDoador.getEmail();
        setBody(doacaoDoador);
        setRota("/doador");
        return post();
    }

    public Response casoFelizConsultarDoadorPeloEmail() {
        Response resposta = casoFelizIncluirDoador();
        resposta.then().statusCode(200);
        prepararParaNovaRequisicao();
        montarRequisicao();
        String body = "{\"email\":\"" + email + "\"}";
        setBody(body);
        setRota("/doador");
        return get();
    }

    public Response casoFelizIncluirDoacao() {
        Response resposta = casoFelizIncluirDoador();
        resposta.then().statusCode(200);
        int idDoador = resposta.jsonPath().getInt("id");
        prepararParaNovaRequisicao();
        EstoqueService estoqueService = new EstoqueService();
        Response resposta2 = estoqueService.casoFelizEntradaStock("5");
        resposta2.then().statusCode(201);
        DoacaoItens item = DoacaoItens.criarItem();
        item.setNome(resposta2.jsonPath().getString("[0].nome"));
        item.setUnidade(resposta2.jsonPath().getString("[0].unidade"));
        item.setQtd(resposta2.jsonPath().getInt("[0].quantidade"));
        item.setCategoria(resposta2.jsonPath().getString("[0].categoria"));
        List<DoacaoItens> itens = new ArrayList<>();
        itens.add(item);
        prepararParaNovaRequisicao();
        montarRequisicao();
        Doacao doacao = Doacao.criarDoacao();
        doacao.setCodDoador(idDoador);
        doacao.setCodCD(5);
        doacao.setItens(itens);
        setBody(doacao);
        setRota("/doacao");
        return post();
    }

    public Response casoFelizBuscarDoacaoPeloId() {
        Response resposta = casoFelizIncluirDoacao();
        resposta.then().statusCode(201);
        prepararParaNovaRequisicao();
        montarRequisicao();
        // addPathParam("idDoacao",resposta.jsonPath().getInt("id"));
        addPathParam("idDoacao", 1);
        setRota("/doacao/{idDoacao}");
        return get();
    }

    public Response casoFelizBuscarPelaData() {
        Response resposta = casoFelizIncluirDoacao();
        resposta.then().statusCode(201);
        prepararParaNovaRequisicao();
        montarRequisicao();
        addQueryParams("startDate", "2024-10-10");
        addQueryParams("endDate", "2024-12-31");
        setRota("/doacao/date");
        return get();
    }

    public Response casoErroConsultaSEmToken() {
        montarRequisicao();
        String body = "{\"email\":\"" + email + "\"}";
        setBody(body);
        setRota("/doador");
        removeHeader("Authorization");
        return get();
    }

    public Response casoErroConsultaComTokenInvalido() {
        montarRequisicao();
        String body = "{\"email\":\"" + email + "\"}";
        setBody(body);
        setRota("/doador");
        setToken("tokenInvalido");
        return get();
    }
}
