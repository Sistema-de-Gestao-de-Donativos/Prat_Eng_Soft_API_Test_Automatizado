package prat_eng_soft_api_test_automatizado.testCase;

import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.DTO.CentroDistribuicaoAddressDTO;
import prat_eng_soft_api_test_automatizado.DTO.CentroDistribuicaoDTO;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;
import prat_eng_soft_api_test_automatizado.validation.GenericValidation;

@TestMethodOrder(OrderAnnotation.class)
public class CentroDistribuicaoTestCase extends BaseTestCase {

    private GenericValidation genericValidation;

    public CentroDistribuicaoTestCase() {
        super("http://localhost:8081", "/v1/cds");
        genericValidation = new GenericValidation();
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Listar todos os CDs")
    @Tag("Regressao")
    @Order(1)
    public void listarTodosCDs() {
        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarCDs"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Listar CD pelo código")
    @Tag("Regressao")
    @Order(2)
    public void listarCDsPeloCodigo() {
        queryParams.put("codCD", 1);
        Response resposta = genericService.get(queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarCDCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Listar CDs pelo nome")
    @Tag("Regressao")
    @Order(3)
    public void listarCDsPeloNome() {
        queryParams.put("nameCD", "Cho-Gath");
        Response resposta = genericService.get(queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarCDCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Centro Distribuição = Incluir CDs")
    @Tag("Regressao")
    @Order(4)
    public void adicionarCD() {
        Faker faker = new Faker(new Locale("pt-BR"));
        CentroDistribuicaoAddressDTO centroDistribuicaoAddressDTO = new CentroDistribuicaoAddressDTO("Brasil",
                faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));

        CentroDistribuicaoDTO centroDistribuicaoDTO = new CentroDistribuicaoDTO(faker.leagueOfLegends().champion(),
                faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(), centroDistribuicaoAddressDTO);

        Response resposta = genericService.post(centroDistribuicaoDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirCD"));
    }

    // Falta incluir os testes de cenaŕio de erro
}
