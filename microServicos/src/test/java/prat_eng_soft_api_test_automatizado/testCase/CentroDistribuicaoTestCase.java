package prat_eng_soft_api_test_automatizado.testCase;

import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.DTO.CentroDistribuicaoAddressDTO;
import prat_eng_soft_api_test_automatizado.DTO.CentroDistribuicaoDTO;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;
import prat_eng_soft_api_test_automatizado.validation.GenericValidation;

public class CentroDistribuicaoTestCase extends BaseTestCase {

    private GenericValidation genericValidation;

    public CentroDistribuicaoTestCase() {
        super("http://localhost:8080", "/v1/cds");
        genericValidation = new GenericValidation();
    }

    @Test
    @DisplayName("Ms de Centro Distribuição = Listar todos os CDs")
    @Tag("Regressao")
    public void listarTodosCDs() {
        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigos"));
    }

    @Test
    @DisplayName("Ms de Centro Distribuição = Listar CDs pelo código")
    @Tag("Regressao")
    public void listarCDsPeloCodigo() {
        queryParams.put("codAbrigo", 2);
        Response resposta = genericService.get(queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Ms de Centro Distribuição = Listar CDs pelo nome")
    @Tag("Regressao")
    public void listarCDsPeloNome() {
        queryParams.put("nomeAbrigo", "New Haley Licensed Rubber Table");
        Response resposta = genericService.get(queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Ms de Centro Distribuição = Incluir CDs")
    @Tag("Regressao")
    public void adicionarCD() {
        Faker faker = new Faker(new Locale("pt-BR"));
        CentroDistribuicaoAddressDTO centroDistribuicaoAddressDTO = new CentroDistribuicaoAddressDTO("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));
        
        CentroDistribuicaoDTO centroDistribuicaoDTO = new CentroDistribuicaoDTO(faker.leagueOfLegends().champion(), faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(), centroDistribuicaoAddressDTO);

        Response resposta = genericService.post(centroDistribuicaoDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirAbrigo"));
    }

    //Falta incluir os testes de cenaŕio de erro
}
