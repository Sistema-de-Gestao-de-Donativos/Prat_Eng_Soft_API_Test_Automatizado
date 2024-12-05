package prat_eng_soft_api_test_automatizado.service;

import java.util.Properties;

import org.apache.http.HttpStatus;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.models.CentroDistribuicaoSemNumero;
import prat_eng_soft_api_test_automatizado.utils.models.CentroDistribuicao;
import prat_eng_soft_api_test_automatizado.utils.models.CentroDistribuicaoAddress;

public class CentroDistribuicaoService extends GenericService {
    private CentroDistribuicao centroDistribuicao;

    private static String getBaseUri() {
        Properties propriedades;
        try {
            propriedades = ConexaoBancoDados.getProperties("rotas");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar carregar arquivo com as Rotas", e);
        }
        return propriedades.getProperty("cd");
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
        setRota("/cds");
    }

    public Response casoFelizListarTodosCentrosDistribuicao() {
        Response resposta = casoFelizAdcionarCentroDistribuicao();
        if (resposta.getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Erro ao tentar adicionar um centro de distribuição");
        }
        prepararParaNovaRequisicao();
        montarRequisicao();
        return get();
    }

    public Response casoFelizAdcionarCentroDistribuicao(){
        montarRequisicao();
        centroDistribuicao = CentroDistribuicao.criarCentroDistribuicao();
        setBody(centroDistribuicao);
        return post();
    }

    public Response casoFelizDeListarCentroDistribuicaoPeloCodigo() {
        Response resposta = casoFelizAdcionarCentroDistribuicao();
        if (resposta.getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Erro ao tentar adicionar um abrigo");
        }
        prepararParaNovaRequisicao();
        montarRequisicao();
        addQueryParams("codCD", resposta.jsonPath().getInt("code"));
        return get();
    }

    public Response casoFelizDeListarCentroDistribuicaoPeloNome() {
        Response resposta = casoFelizAdcionarCentroDistribuicao();
        if (resposta.getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Erro ao tentar adicionar um abrigo");
        }

        prepararParaNovaRequisicao();
        montarRequisicao();
        addQueryParams("nameCD", centroDistribuicao.getName());
        return get();
    }

    public Response casoFelizDeListarCentroDistribuicaoPeloNomeECodigo() {
        Response resposta = casoFelizAdcionarCentroDistribuicao();
        if (resposta.getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Erro ao tentar adicionar um abrigo");
        }
        prepararParaNovaRequisicao();

        montarRequisicao();
        addQueryParams("codCD", resposta.jsonPath().getInt("code"));
        addQueryParams("nameCD", centroDistribuicao.getName());
        return get();
    }

    public Response casoErroCriarCentroDistribuicaoSemNome() {
        montarRequisicao();
        centroDistribuicao = CentroDistribuicao.criarCentroDistribuicao();
        centroDistribuicao.setName("");
        setBody(centroDistribuicao);
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoSemTelefone() {
        montarRequisicao();
        centroDistribuicao = CentroDistribuicao.criarCentroDistribuicao();
        centroDistribuicao.setPhone("");
        setBody(centroDistribuicao);
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoSemEmail() {
        montarRequisicao();
        centroDistribuicao = CentroDistribuicao.criarCentroDistribuicao();
        centroDistribuicao.setEmail("");
        setBody(centroDistribuicao);
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoSemPais() {
        montarRequisicao();
        centroDistribuicao = CentroDistribuicao.criarCentroDistribuicao();
        CentroDistribuicaoAddress endereco = centroDistribuicao.getAddress();
        endereco.setCountry("");
        centroDistribuicao.setAddress(endereco);
        setBody(centroDistribuicao);
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoSemEstado() {
        montarRequisicao();
        centroDistribuicao = CentroDistribuicao.criarCentroDistribuicao();
        CentroDistribuicaoAddress endereco = centroDistribuicao.getAddress();
        endereco.setState("");
        centroDistribuicao.setAddress(endereco);
        setBody(centroDistribuicao);
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoSemCidade() {
        montarRequisicao();
        centroDistribuicao = CentroDistribuicao.criarCentroDistribuicao();
        CentroDistribuicaoAddress endereco = centroDistribuicao.getAddress();
        endereco.setCity("");
        centroDistribuicao.setAddress(endereco);
        setBody(centroDistribuicao);
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoSemBairro() {
        montarRequisicao();
        centroDistribuicao = CentroDistribuicao.criarCentroDistribuicao();
        CentroDistribuicaoAddress endereco = centroDistribuicao.getAddress();
        endereco.setNeighborhood("");
        centroDistribuicao.setAddress(endereco);
        setBody(centroDistribuicao);
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoSemRua() {
        montarRequisicao();
        centroDistribuicao = CentroDistribuicao.criarCentroDistribuicao();
        CentroDistribuicaoAddress endereco = centroDistribuicao.getAddress();
        endereco.setStreet("");
        centroDistribuicao.setAddress(endereco);
        setBody(centroDistribuicao);
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoSemNumero() {
        montarRequisicao();
        CentroDistribuicaoSemNumero centroDistribuicaoSemNumero = CentroDistribuicaoSemNumero.criarCentroDistribuicaoSemNumero();
        setBody(centroDistribuicaoSemNumero);
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoSemToken() {
        montarRequisicao();
        removeHeader("Authorization");
        return post();
    }

    public Response casoErroCriarCentroDistribuicaoComTokenInvalido() {
        montarRequisicao();
        setToken("tokenInvalido");
        return post();
    }

}
