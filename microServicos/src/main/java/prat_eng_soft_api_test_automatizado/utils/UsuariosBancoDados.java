package prat_eng_soft_api_test_automatizado.utils;

import java.io.IOException;

import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.github.javafaker.Faker;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import io.qameta.allure.Allure;

public class UsuariosBancoDados {

    private Faker faker = new Faker(new Locale("pt-BR"));

    private String urlMongo = "";
    private String databaseMongo = "";
    private String colecaoMongo = "";

    private void carregarDados() throws IOException {
        Properties propriedades = ConexaoBancoDados.getProperties("UsuariosMongoDb");
        urlMongo = propriedades.getProperty("url");
        databaseMongo = propriedades.getProperty("database");
        colecaoMongo = propriedades.getProperty("colecao");
    }

    public Document encontrarUsuarioId(String idUsuario) {

        try {
            carregarDados();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar dados de conexão do MongoDB", e);
        }

        try (MongoClient mongoClient = MongoClients.create(urlMongo)) {
            MongoDatabase database = mongoClient.getDatabase(databaseMongo);
            MongoCollection<Document> collection = database.getCollection(colecaoMongo);
            Document filtro = new Document("_id", idUsuario);

            Allure.step("Consulta realizada no Banco MongoDb ", () -> {
                Allure.addAttachment("Filtro", filtro.toJson());
            });

            return collection.find(filtro).first();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao passar os nomes nos parametros getDatabase ou getCollection", e);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException("Erro ao encontrar usuário no MongoDB", e);
        }
    }

    public Document encontrarUsuarioPeloNome(String nome) {

        try {
            carregarDados();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar dados de conexão do MongoDB", e);
        }

        try (MongoClient mongoClient = MongoClients.create(urlMongo)) {
            MongoDatabase database = mongoClient.getDatabase(databaseMongo);
            MongoCollection<Document> collection = database.getCollection(colecaoMongo);
            Document filtro = new Document("name", nome);

            Allure.step("Consulta realizada no Banco MongoDb ", () -> {
                Allure.addAttachment("Filtro", filtro.toJson());
            });

            return collection.find(filtro).first();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao passar os nomes nos parametros getDatabase ou getCollection", e);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException("Erro ao encontrar usuário no MongoDB", e);
        }

    }

    public Document encontrarUsuarioPeloCpf(String cpf) {
        try {
            carregarDados();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar ler o arquivo de conexao do Mongodb", e);
        }
        try (MongoClient mongoClient = MongoClients.create(urlMongo)) {
            MongoDatabase database = mongoClient.getDatabase(databaseMongo);

            MongoCollection<Document> collection = database.getCollection(colecaoMongo);

            Document filtro = new Document("cpf", cpf);

            Allure.step("Consulta realizada no Banco MongoDb ", () -> {
                Allure.addAttachment("Filtro", filtro.toJson());
            });

            return collection.find(filtro).first();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao passar os nomes nos parametros getDatabase ou getCollection", e);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException("Erro ao encontrar usuário no MongoDB", e);
        }

    }

    public String incluirUsuario(String role) {

        try {
            carregarDados();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar dados de conexão do MongoDB", e);
        }

        String nome = getNome();
        String cpf = getCpf();

        try (MongoClient mongoClient = MongoClients.create(urlMongo)) {

            MongoDatabase database = mongoClient.getDatabase(databaseMongo);

            MongoCollection<Document> collection = database.getCollection(colecaoMongo);

            // Criar o subdocumento "address" com valores genéricos de exemplo
            Document address = new Document("country", "Brasil")
                    .append("state", faker.address().state())
                    .append("city", faker.address().city())
                    .append("neighborhood", faker.address().cityName())
                    .append("street", faker.address().streetName())
                    .append("number", faker.number().numberBetween(1, 1000));

            // Criar o documento principal
            Document documento = new Document("_id", new ObjectId().toHexString())
                    .append("name", nome)
                    .append("address", address)
                    .append("email", faker.internet().emailAddress())
                    .append("phone", faker.phoneNumber().cellPhone())
                    .append("role", role)
                    .append("codEntidade", 1)
                    .append("cpf", cpf)
                    .append("created_at", new Date());

            Allure.step("Insert realizada no Banco MongoDb ", () -> {
                Allure.addAttachment("Filtro", documento.toJson());
            });

            InsertOneResult resultado = collection.insertOne(documento);

            return resultado.getInsertedId().asString().getValue();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }

    }

    public String getCpf() {

        String cpf = GeradorCpf.gerarCpfSemFormatacao();
        while (encontrarUsuarioPeloCpf(cpf) != null) {
            cpf = GeradorCpf.gerarCpfSemFormatacao();
        }

        return cpf;

    }

    public String getNome() {

        String nome = faker.name().fullName();
        while (encontrarUsuarioPeloNome(nome) != null) {
            nome = faker.name().fullName();
        }
        return nome;

    }

}
