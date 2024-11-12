package prat_eng_soft_api_test_automatizado.TestCase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.github.javafaker.Faker;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.TestCase.models.EstoqueEntrada;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
//import prat_eng_soft_api_test_automatizado.utils.ConexaoBancoDados;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;

public class EstoqueTestCase extends BaseTestCase {

    private GenericValidation genericValidation;
   // private ConexaoBancoDados conexaoBancoDados;


    public EstoqueTestCase() {
        super("estoque", "/v1/stock");
        genericValidation = new GenericValidation();
     //   conexaoBancoDados = new ConexaoBancoDados(
     //       "jdbc:mysql://localhost:3306/db_doacao",
    //        "root",
    //        "Leo202426s@");

    }

    @BeforeEach
    public void allureReport(TestInfo testInfo) {
        Allure.epic("Sprint 2");
        Allure.feature("Micro Serviço de Centro de Estoque");
    }

    @Test
    @DisplayName("Micro Serviço de Estoque = Incluir nova entrada de Itens no Estoque")
    @Tag("Regressao")
    @Order(1)
    public void incluirNovaEntradaEstoque() {
        Allure.description("Teste para validar a entrada de um novo item no Estoque");

        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = dateFormat.format(data);

        Faker faker = new Faker(new Locale("pt-BR"));

        EstoqueEntrada estoqueEntradaDTO = new EstoqueEntrada(
                faker.food().ingredient(),
                faker.number().numberBetween(1, 1000),
                faker.food().measurement(),
                dataFormatada,
                faker.food().spice());
        List<EstoqueEntrada> listaEntradas = new ArrayList<>();
        listaEntradas.add(estoqueEntradaDTO);

        genericService.addPathParam("codCd", 1);

        //        Response resposta = genericService.post("{codCd}", pathParams, listaEntradas);

        Response resposta = genericService.post();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirNovoEntradaEstoque"));
      //  genericValidation.validarPersistenciaDosDados(true);

    }

    @Test
    @DisplayName("Micro Serviço de Estoque = Consultar estoque de um determinado Centro de Distribuição")
    @Tag("Regressao")
    @Order(2)
    public void consultaEstoqueCD() {
        Allure.description("Teste para validar a consulta de estoque de um determinado Centro de Distribuição");

        genericService.addPathParam("codCd", 1);

        //        Response resposta = genericService.get("{codCd}", pathParams);

        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("consultaEstoqueCD"));
    }

    
    @Test
    @DisplayName("Micro Serviço de Estoque = Saida de um Item de um Centro de Distribuição")
    @Tag("Regressao")
    @Order(4)
    public void consultaQuantidadeItens() {
        Allure.description("Teste para validar a consulta de quantidade de itens de um Centro de Distribuição");

        genericService.addPathParam("codCd", 10);
        genericService.addPathParam("codItem", 10);

      

        //        Response resposta = genericService.get("{codCd}/{codcodBarras}", pathParams);

        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);

    }

}