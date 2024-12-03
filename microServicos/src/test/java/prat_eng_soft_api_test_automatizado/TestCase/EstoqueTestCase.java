package prat_eng_soft_api_test_automatizado.TestCase;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.Validation.GenericValidation;
import prat_eng_soft_api_test_automatizado.service.EstoqueService;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;

@TestMethodOrder(OrderAnnotation.class)
public class EstoqueTestCase {

  private GenericValidation genericValidation = new GenericValidation();
  private EstoqueService estoqueService = new EstoqueService();

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
    Allure.epic("Sprint 2");
    Allure.feature("Micro Serviço de Centro de Estoque");
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Incluir nova entrada de Itens no Estoque")
  @Tag("Regressao")
  @Order(1)
  public void incluirNovaEntradaEstoque() {
    Allure.description("Teste para validar a entrada de um novo item no Estoque");
    Response resposta = estoqueService.casoFelizEntradaStock("2");
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_CREATED);
    genericValidation.validarContrato(ContratoManager.getContrato("IncluirNovoEntradaEstoque"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Consultar estoque de um determinado Centro de Distribuição")
  @Tag("Regressao")
  @Order(2)
  public void consultaEstoqueCD() {
    Allure.description("Teste para validar a consulta de estoque de um determinado Centro de Distribuição");
    Response resposta = estoqueService.casoFelizConsultarStock();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_OK);
    genericValidation.validarContrato(ContratoManager.getContrato("consultaEstoqueCD"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Dar baixa de um Itemde um estoque de um determinado Centro de Distribuição")
  @Tag("Regressao")
  @Order(3)
  public void darBaixaEstoque() {
    Allure.description("Teste para validar a baixa de um item de um Centro de Distribuição");
    Response resposta = estoqueService.casoFelizDarBaixaEstoque();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_NO_CONTENT);
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = consulta de quantidade de itens de um Centro de Distribuição")
  @Tag("Regressao")
  @Order(4)
  public void consultaQuantidadeItens() {
    Allure.description("Teste para validar a consulta de quantidade de itens de um Centro de Distribuição");
    Response resposta = estoqueService.casoFelizConsultarItemDeUmCd();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Consultar informando um Centro Distribuição inválido para a pesquisa de estoque de um Centro de Distribuição")
  @Tag("Regressao")
  @Order(5)
  public void consultaEstoqueCDInvalido() {
    Allure.description("Teste para validar a consulta de estoque de um Centro de Distribuição inválido");
    Response resposta = estoqueService.casoDeConsultarEstoqueComCDInvalido();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    genericValidation.validarContrato(ContratoManager.getContrato("consultarEstoqueErroCd"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Consultar informando um Centro Distribuição inválido para a pesquisa de um item")
  @Tag("Regressao")
  @Order(5)
  public void consultaItemCentroDistribuicaoInvalido() {
    Allure.description("Teste para validar a consulta de um item de um Centro de Distribuição inválido");
    Response resposta = estoqueService.casoDeConsultarItemComCDInvalido();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    genericValidation.validarContrato(ContratoManager.getContrato("consultarEstoqueErroCd"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Validar erro ao tentar incluir um item no estoque não informando um nome de item")
  @Tag("Regressao")
  @Order(6)
  public void incluirItemSemNome() {
    Allure.description("Teste para validar a inclusão de um item no estoque sem informar o nome do item");
    Response resposta = estoqueService.casoDeIncluirItemSemNome();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    genericValidation.validarContrato(ContratoManager.getContrato("IncluirEntradaSemNomeItem"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Validar erro ao tentar incluir um item no estoque não informando uma quantidade de item")
  @Tag("Regressao")
  @Order(7)
  public void incluirItemSemQuantidade() {
    Allure.description("Teste para validar a inclusão de um item no estoque sem informar a quantidade do item");
    Response resposta = estoqueService.casoDeIncluirItemSemQuantidade();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    genericValidation.validarContrato(ContratoManager.getContrato("incluirEntradaSemQuantidadeItem"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Validar erro ao tentar incluir um item no estoque não informando uma unidade de item")
  @Tag("Regressao")
  @Order(8)
  public void incluirItemSemUnidade() {
    Allure.description("Teste para validar a inclusão de um item no estoque sem informar a unidade do item");
    Response resposta = estoqueService.casoDeIncluirItemSemUnidade();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    genericValidation.validarContrato(ContratoManager.getContrato("incluirEntradaSemUnidade"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Validar erro ao tentar incluir um item no estoque não informando a categoria do item")
  @Tag("Regressao")
  @Order(9)
  public void incluirItemSemCategoria() {
    Allure.description("Teste para validar a inclusão de um item no estoque sem informar a categoria do item");
    Response resposta = estoqueService.casoDeIncluirItemSemCategoria();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    genericValidation.validarContrato(ContratoManager.getContrato("incluirEntradaSemCategoria"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Validar erro ao tentar excluir um item no estoque não informando nome do item")
  @Tag("Regressao")
  @Order(10)
  public void excluirItemSemNome() {
    Allure.description("Teste para validar a exclusão de um item no estoque sem informar o nome do item");
    Response resposta = estoqueService.casoDeExcluirItemSemNome();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    genericValidation.validarContrato(ContratoManager.getContrato("deletarItemEstoqueSemNome"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Validar erro ao tentar excluir um item no estoque não informando a quantidade do item")
  @Tag("Regressao")
  @Order(11)
  public void excluirItemSemQuantidade() {
    Allure.description("Teste para validar a exclusão de um item no estoque sem informar a quantidade do item");
    Response resposta = estoqueService.casoDeExcluirItemSemQuantidade();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    genericValidation.validarContrato(ContratoManager.getContrato("deletarItemEstoqueSemQuantidade"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Validar erro ao tentar excluir um item no estoque informando uma quantidade maior que a disponível")
  @Tag("Regressao")
  @Order(12)
  public void excluirItemQuantidadeMaiorQueDisponivel() {
    Allure.description(
        "Teste para validar a exclusão de um item no estoque informando uma quantidade maior que a disponível");
    Response resposta = estoqueService.casoDeExcluirItemQuantidadeMaiorQueDisponivel();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_NOT_FOUND);
    genericValidation.validarMensagem("detail", "Item not found or insufficient quantity");
    genericValidation.validarContrato(ContratoManager.getContrato("deletarItemNaoEncontrado"));
  }

  @Test
  @DisplayName("Micro Serviço de Estoque = Validar erro ao tentar excluir um item no estoque informando um item que não existe")
  @Tag("Regressao")
  @Order(13)
  public void excluirItemNaoExistente() {
    Allure.description("Teste para validar a exclusão de um item no estoque informando um item que não existe");
    Response resposta = estoqueService.casoDeExcluirItemNaoExistente();
    genericValidation.setResponse(resposta);
    genericValidation.validarStatusCode(HttpStatus.SC_NOT_FOUND);
    genericValidation.validarMensagem("detail", "Item not found or insufficient quantity");
    genericValidation.validarContrato(ContratoManager.getContrato("deletarItemNaoEncontrado"));
  }

}