package prat_eng_soft_api_test_automatizado.testCase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import prat_eng_soft_api_test_automatizado.DTO.EstoqueEntradaDTO;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;
import prat_eng_soft_api_test_automatizado.validation.GenericValidation;

public class EstoqueTestCase extends BaseTestCase {

    private GenericValidation genericValidation;

    public EstoqueTestCase() {
        super("http://127.0.0.1:8080", "v1/stock");
        genericValidation = new GenericValidation();
    }

    @Test
    @DisplayName("Ms de Estoque = Incluir nova entrada de Itens no Estoque")
    @Tag("Regressao")
    @Order(1)
    public void incluirNovaEntradaEstoque() {

        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = dateFormat.format(data);

        Faker faker = new Faker(new Locale("pt-BR"));

        EstoqueEntradaDTO estoqueEntradaDTO = new EstoqueEntradaDTO(
                faker.food().ingredient(),
                faker.number().numberBetween(1, 1000),
                faker.food().measurement(),
                dataFormatada,
                faker.food().spice());
        List<EstoqueEntradaDTO> listaEntradas = new ArrayList<>();
        listaEntradas.add(estoqueEntradaDTO);

        pathParams.put("codCd", 10);

        Response resposta = genericService.post("{codCd}", pathParams, listaEntradas);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirNovoEntradaEstoque"));
    }

    @Test
    @DisplayName("Ms de Estoque = Consultar estoque de um determinado Centro de Distribuição")
    @Tag("Regressao")
    @Order(2)
    public void consultaEstoqueCD() {

        pathParams.put("codCd", 10);

        Response resposta = genericService.get("{codCd}", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("consultaEstoqueCD"));
    }

    @Test
    @DisplayName("Ms de Estoque = Saida de um Item de um Centro de Distribuição")
    @Tag("Regressao")
    @Order(3)
    public void saidaEstoqueCD() {

        pathParams.put("codCd", 10);
        queryParams.put("codBarras", "codigo de barra");
        queryParams.put("qtd", 1);

        Response resposta = genericService.delete("{codCd}", pathParams, queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_NO_CONTENT);
    }


    @Test
    @DisplayName("Ms de Estoque = Saida de um Item de um Centro de Distribuição")
    @Tag("Regressao")
    @Order(4)
    public void consultaQuantidadeItens() {

        pathParams.put("codCd", 10);
        pathParams.put("codcodBarras", 10);

        Response resposta = genericService.get("{codCd}/{codcodBarras}", pathParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
    }


}