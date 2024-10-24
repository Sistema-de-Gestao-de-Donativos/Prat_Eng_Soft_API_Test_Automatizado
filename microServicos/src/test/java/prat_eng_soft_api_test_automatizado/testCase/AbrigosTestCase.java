package prat_eng_soft_api_test_automatizado.testCase;

import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import prat_eng_soft_api_test_automatizado.DTO.AbrigoAddressDTO;
import prat_eng_soft_api_test_automatizado.DTO.AbrigoDTO;
import prat_eng_soft_api_test_automatizado.utils.ContratoManager;
import prat_eng_soft_api_test_automatizado.validation.GenericValidation;

@TestMethodOrder(OrderAnnotation.class)
public class AbrigosTestCase extends BaseTestCase {

    private GenericValidation genericValidation;

    public AbrigosTestCase() {
        super("http://localhost:8080", "v1/abrigos");
        genericValidation = new GenericValidation();
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Listar todos os abrigos")
    @Tag("Regressao")
    @Order(1)
    public void listarTodosAbrigos() {
        Response resposta = genericService.get();
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigos"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Listar abrigo pelo código")
    @Tag("Regressao")
    @Order(2)
    public void listarAbrigoPeloCodigo() {
        queryParams.put("codAbrigo", 1);
        Response resposta = genericService.get(queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Listar abrigo pelo nome")
    @Tag("Regressao")
    @Order(3)
    public void listarAbrigoPeloNome() {
        queryParams.put("nomeAbrigo", "Lee Sin");
        Response resposta = genericService.get(queryParams);
        genericValidation.setResponse(resposta);
        genericValidation.validarStatusCode(HttpStatus.SC_OK);
        genericValidation.validarContrato(ContratoManager.getContrato("ListarAbrigosCodigoOuNome"));
    }

    @Test
    @DisplayName("Micro Serviço de Abrigos = Incluir abrigo")
    @Tag("Regressao")
    @Order(4)
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

}