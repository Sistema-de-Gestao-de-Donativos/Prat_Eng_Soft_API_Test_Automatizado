package prat_eng_soft_api_test_automatizado.service;

import java.util.Properties;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.models.Estoque;

public class EstoqueService extends GenericService {

    private int codCd;

    private static String getBaseUri() {
        Properties propriedades;
        try {
            propriedades = ConexaoBancoDados.getProperties("rotas");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar carregar arquivo com as Rotas", e);
        }
        return propriedades.getProperty("estoque");
    }

    private void montarRequisicao() {
        setBaseUri(getBaseUri());
        setBasePath("/v1/stock");
    }


    public Response casoFelizEntradaStock(String CD){
        montarRequisicao();
        addPathParam("codCd", CD);
        setBody(Estoque.criarEstoqueEntradas());
        setRota("/{codCd}");
        return post();
    }


    public Response casoFelizEntradaStock(){
        CentroDistribuicaoService centroDistribuicaoService = new CentroDistribuicaoService();
        Response response = centroDistribuicaoService.casoFelizAdcionarCentroDistribuicao();
        response.then().statusCode(200);    
        String codCdString = response.jsonPath().getString("code");
        codCd = Integer.parseInt(codCdString);
        prepararParaNovaRequisicao();
        montarRequisicao();
        addPathParam("codCd", codCd);
        setBody(Estoque.criarEstoqueEntradas());
        setRota("/{codCd}");
        return post();
    }

    public Response casoFelizConsultarStock(){
        Response response = casoFelizEntradaStock();
        response.then().statusCode(201);
        prepararParaNovaRequisicao();
        montarRequisicao();
        setRota("/{codCd}");
        addPathParam("codCd", codCd);
        return get();
    }

    public Response casoFelizDarBaixaEstoque(){
        Response response = casoFelizEntradaStock();
        response.then().statusCode(201);
        prepararParaNovaRequisicao();
        montarRequisicao();
        addPathParam("codCd", codCd);
        setRota("/{codCd}");
        addQueryParams("nome", response.jsonPath().getString("[0].nome"));
        String codCdString = response.jsonPath().getString("[0].quantidade");
        int quantidade = Integer.parseInt(codCdString);
        addQueryParams("qtd", quantidade);
        return delete();
    }

    public Response casoFelizConsultarItemDeUmCd(){
        Response response = casoFelizEntradaStock();
        response.then().statusCode(201);
        prepararParaNovaRequisicao();
        montarRequisicao();
        addPathParam("codCd", codCd);
        addPathParam("nome", response.jsonPath().getString("[0].nome"));
        setRota("/{codCd}/{nome}");
        return get();
    }

    public Response casoDeConsultarItemComCDInvalido(){
        montarRequisicao();
        addPathParam("codCd", "TESTE");
        addPathParam("nome", "TESTE");
        setRota("/{codCd}/{nome}");
        return get();
    }

    public Response casoDeConsultarEstoqueComCDInvalido(){
        montarRequisicao();
        addPathParam("codCd", "TESTE");
        setRota("/{codCd}");
        return get();
    }

    public Response casoDeIncluirItemSemNome(){
        montarRequisicao();
        addPathParam("codCd", codCd);
        setBody(Estoque.criarEstoqueEntradasSemNome());
        setRota("/{codCd}");
        return post();
    }

    public Response casoDeIncluirItemSemQuantidade(){
        montarRequisicao();
        addPathParam("codCd", 0);
        setBody(Estoque.criarEstoqueEntradaSemQuantidade());
        setRota("/{codCd}");
        return post();
    }

    public Response casoDeIncluirItemSemUnidade(){
        montarRequisicao();
        addPathParam("codCd", 0);
        setBody(Estoque.criarEstoqueEntradaSemUnidade());
        setRota("/{codCd}");
        return post();
    }

    public Response casoDeIncluirItemSemDataValidade(){
        montarRequisicao();
        addPathParam("codCd", 0);
        setBody(Estoque.criarEstoqueEntradaSemDataValidade());
        setRota("/{codCd}");
        return post();
    }

    public Response casoDeIncluirItemSemCategoria(){
        montarRequisicao();
        addPathParam("codCd", 0);
        setBody(Estoque.criarEstoqueEntradaSemCategoria());
        setRota("/{codCd}");
        return post();
    }

    public Response casoDeExcluirItemSemNome(){
        montarRequisicao();
        addPathParam("codCd", 0);
        addQueryParams("qtd", 1);
        setRota("/{codCd}");
        return delete();
    }

    public Response casoDeExcluirItemSemQuantidade(){
        montarRequisicao();
        addPathParam("codCd", 0);
        addQueryParams("nome", "TESTE");
        setRota("/{codCd}");
        return delete();
    }

    public Response casoDeExcluirItemQuantidadeMaiorQueDisponivel(){
        Response response = casoFelizEntradaStock();
        response.then().statusCode(201);
        prepararParaNovaRequisicao();
        montarRequisicao();
        addPathParam("codCd", codCd);
        setRota("/{codCd}");
        addQueryParams("nome", response.jsonPath().getString("[0].nome"));
        String codCdString = response.jsonPath().getString("[0].quantidade");
        int quantidade = Integer.parseInt(codCdString);
        addQueryParams("qtd", quantidade+1);
        return delete();
    }

    public Response casoDeExcluirItemNaoExistente(){
        montarRequisicao();
        addPathParam("codCd", 1);
        setRota("/{codCd}");
        addQueryParams("nome", "TESTE");
        addQueryParams("qtd", 1);
        return delete();
    }


    
}
