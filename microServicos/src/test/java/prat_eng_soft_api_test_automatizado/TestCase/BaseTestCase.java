package prat_eng_soft_api_test_automatizado.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import com.github.javafaker.Faker;

import prat_eng_soft_api_test_automatizado.service.GenericService;

public class BaseTestCase {
    protected GenericService genericService;
    protected Faker faker;

    public BaseTestCase(String baseUri, String basePath) {
        genericService = new GenericService(getBaseUri(baseUri), basePath);
        faker = new Faker(new Locale("pt-BR"));
    }

    private String getBaseUri(String nome) {

        Properties propriedades = new Properties();
        String caminhoArquivoSQL = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "test" + File.separator + "resources" + File.separator + "rotas.properties";

        try (InputStream input = new FileInputStream(caminhoArquivoSQL)) {
            // Carregar o arquivo de propriedades
            propriedades.load(input);

            // Acessar as propriedades
            return propriedades.getProperty(nome);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}