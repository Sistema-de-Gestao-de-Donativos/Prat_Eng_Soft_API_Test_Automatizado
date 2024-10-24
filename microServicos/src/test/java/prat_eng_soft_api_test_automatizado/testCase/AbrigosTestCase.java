package prat_eng_soft_api_test_automatizado.testCase;

import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.DTO.AbrigoAddressDTO;
import prat_eng_soft_api_test_automatizado.DTO.AbrigoDTO;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;
import prat_eng_soft_api_test_automatizado.validation.GenericValidation;

public class AbrigosTestCase extends BaseTestCase {

    private GenericValidation genericValidation;

    public AbrigosTestCase() {
        super("http://localhost:8080", "v1/abrigos");
        genericValidation = new GenericValidation();
    }

    @Test
    @DisplayName("Ms de Abrigos = Listar todos os abrigos")
    @Tag("Regressao")
    public void listarTodosAbrigos() {
        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigos"));
    }

    @Test
    @DisplayName("Ms de Abrigos = Listar abrigo pelo código")
    @Tag("Regressao")
    public void listarAbrigoPeloCodigo() {
        queryParams.put("codAbrigo", 1);
        Response resposta = genericService.get(queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Ms de Abrigos = Listar abrigo pelo nome")
    @Tag("Regressao")
    public void listarAbrigoPeloNome() {
        queryParams.put("nomeAbrigo", "Lee Sin");
        Response resposta = genericService.get(queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Ms de Abrigos = Incluir abrigo")
    @Tag("Regressao")
    public void adicionarAbrigo() {
        Faker faker = new Faker(new Locale("pt-BR"));
        AbrigoAddressDTO abrigoAddressDTO = new AbrigoAddressDTO("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));
        AbrigoDTO abrigoDTO = new AbrigoDTO(faker.leagueOfLegends().champion(), faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(), abrigoAddressDTO);
        Response resposta = genericService.post(abrigoDTO);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("IncluirAbrigo"));
    }

    //Falta incluir os testes de cenaŕio de erro
    @Test
    @DisplayName("Ms de Abrigos = Buscar Abrigo que não existe pelo código")
    @Tag("Regressao")
    public void listarAbrigoInexistentePeloCodigo() {
        queryParams.put("codAbrigo", 999999);
        Response resposta = genericService.get(queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_NOT_FOUND);

      //  genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }
}