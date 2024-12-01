package prat_eng_soft_api_test_automatizado.service;

import java.util.Properties;

import org.apache.http.HttpStatus;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.models.Abrigo;
import prat_eng_soft_api_test_automatizado.utils.models.AbrigoAddress;
import prat_eng_soft_api_test_automatizado.utils.models.AbrigoSemNumero;

public class AbrigoService extends GenericService {

    private Abrigo abrigoDTO;

    private static String getBaseUri() {
        Properties propriedades;
        try {
            propriedades = ConexaoBancoDados.getProperties("rotas");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar carregar arquivo com as Rotas", e);
        }
        return propriedades.getProperty("abrigo");
    }

    private void montarRequisicao() {
        setBaseUri(getBaseUri());
        setBasePath("/v1");
        setRota("/abrigos");
    }

    public Response casoFelizListarTodosAbrigos() {
        Response resposta = casoFelizDeAdcionarAbrigo();
        if (resposta.getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Erro ao tentar adicionar um abrigo");
        }
        prepararParaNovaRequisicao();
        montarRequisicao();
        return get();
    }

    public Response casoFelizDeListarAbrigoPeloCodigo() {
        Response resposta = casoFelizDeAdcionarAbrigo();
        if (resposta.getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Erro ao tentar adicionar um abrigo");
        }
        prepararParaNovaRequisicao();
        montarRequisicao();
        addQueryParams("codAbrigo", resposta.jsonPath().getInt("code"));
        return get();
    }

    public Response casoFelizDeListarAbrigoPeloNome() {
        Response resposta = casoFelizDeAdcionarAbrigo();
        if (resposta.getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Erro ao tentar adicionar um abrigo");
        }

        prepararParaNovaRequisicao();
        montarRequisicao();
        addQueryParams("nomeAbrigo", abrigoDTO.getName());
        return get();
    }

    public Response casoFelizDeListarAbrigoPeloNomeECodigo() {
        Response resposta = casoFelizDeAdcionarAbrigo();
        if (resposta.getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Erro ao tentar adicionar um abrigo");
        }
        prepararParaNovaRequisicao();

        montarRequisicao();
        addQueryParams("codAbrigo", resposta.jsonPath().getInt("code"));
        addQueryParams("nomeAbrigo", abrigoDTO.getName());
        return get();
    }

    public Response casoFelizDeAdcionarAbrigo() {
        montarRequisicao();
        abrigoDTO = Abrigo.criarAbrigo();
        setBody(abrigoDTO);
        return post();
    }

    public Response casoErroCriarAbrigoSemNome() {

        montarRequisicao();
        abrigoDTO = Abrigo.criarAbrigo();
        abrigoDTO.setName("");
        setBody(abrigoDTO);
        return post();

    }

    public Response casoErroCriarAbrigoSemTelefone() {
        montarRequisicao();
        abrigoDTO = Abrigo.criarAbrigo();
        abrigoDTO.setPhone("");
        setBody(abrigoDTO);
        return post();
    }

    public Response casoErroCriarAbrigoSemEmail() {
        montarRequisicao();
        abrigoDTO = Abrigo.criarAbrigo();
        abrigoDTO.setEmail("");
        setBody(abrigoDTO);
        return post();
    }

    public Response casoErroCriarAbrigoSemPais() {
        montarRequisicao();
        abrigoDTO = Abrigo.criarAbrigo();
        AbrigoAddress endereco = abrigoDTO.getAddress();
        endereco.setCountry("");
        abrigoDTO.setAddress(endereco);
        setBody(abrigoDTO);
        return post();
    }

    public Response casoErroCriarAbrigoSemEstado() {
        montarRequisicao();
        abrigoDTO = Abrigo.criarAbrigo();
        AbrigoAddress endereco = abrigoDTO.getAddress();
        endereco.setState("");
        abrigoDTO.setAddress(endereco);
        setBody(abrigoDTO);
        return post();
    }

    public Response casoErroCriarAbrigoSemCidade() {
        montarRequisicao();
        abrigoDTO = Abrigo.criarAbrigo();
        AbrigoAddress endereco = abrigoDTO.getAddress();
        endereco.setCity("");
        abrigoDTO.setAddress(endereco);
        setBody(abrigoDTO);
        return post();
    }

    public Response casoErroCriarAbrigoSemBairro() {
        montarRequisicao();
        abrigoDTO = Abrigo.criarAbrigo();
        AbrigoAddress endereco = abrigoDTO.getAddress();
        endereco.setNeighborhood("");
        abrigoDTO.setAddress(endereco);
        setBody(abrigoDTO);
        return post();
    }

    public Response casoErroCriarAbrigoSemRua() {
        montarRequisicao();
        abrigoDTO = Abrigo.criarAbrigo();
        AbrigoAddress endereco = abrigoDTO.getAddress();
        endereco.setStreet("");
        abrigoDTO.setAddress(endereco);
        setBody(abrigoDTO);
        return post();
    }

    public Response casoErroCriarAbrigoSemNumero() {
        montarRequisicao();
        AbrigoSemNumero abrigoSemNumeroDTO = AbrigoSemNumero.criarAbrigo();
        setBody(abrigoSemNumeroDTO);
        return post();
    }

}
