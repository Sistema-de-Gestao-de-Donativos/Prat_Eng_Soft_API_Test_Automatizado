package prat_eng_soft_api_test_automatizado.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import io.qameta.allure.Allure;

import com.github.javafaker.Faker;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class ConexaoBancoDados {

    private String urlSql;
    private String urlMongo;
    private String usersql;
    private String passwordsql;
    private String databaseMongo;
    private String colecaoMongo;
    private Faker faker;

    public ConexaoBancoDados() {
        faker = new Faker(new Locale("pt-BR"));
    }

    private void conectarBancoDadosSQL() {
        Properties propriedades = new Properties();
        String caminhoArquivoSQL = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "test" + File.separator + "resources" + File.separator + "sqlConexao.properties";

        try (InputStream input = new FileInputStream(caminhoArquivoSQL)) {
            // Carregar o arquivo de propriedades
            propriedades.load(input);

            // Acessar as propriedades
            urlSql = propriedades.getProperty("url");
            usersql = propriedades.getProperty("user");
            passwordsql = propriedades.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void conectarBancoDadosMongo() {

        Properties propriedades = new Properties();
        String caminhoArquivoSQL = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "test" + File.separator + "resources" + File.separator + "mongoConexao.properties";

        try (InputStream input = new FileInputStream(caminhoArquivoSQL)) {
            // Carregar o arquivo de propriedades
            propriedades.load(input);

            // Acessar as propriedades
            urlMongo = propriedades.getProperty("url");
            databaseMongo = propriedades.getProperty("database");
            colecaoMongo = propriedades.getProperty("colecao");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void validarPersistencia(String busca, Document resultado) {

        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

        Allure.step("Validando Persistencia no Banco de Dados", () -> {
            Allure.addAttachment("Busca realizada", busca);
            Allure.addAttachment("Resultado da consulta",
                    resultado != null ? resultado.toJson(prettyPrint) : "Não encontrado");
        });
    }

    // ------------------------ SQL--------------------------------------

    // Abrigos
    public void incluirAbrigo() {

    }

    public void encontrarAbrigo(String codigo) {
        conectarBancoDadosSQL();
        String tabela = "abrigo";
        String sql = "SELECT * FROM " + tabela + " WHERE code = " + codigo + ";"; // ou "DELETE FROM " + tableName;

        try (
                Connection connection = DriverManager.getConnection(urlSql, usersql, passwordsql);
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            boolean resultado = resultSet.next();

            if (resultado) {

                Allure.step("Resultado da consulta no Banco de dados Abrigo", () -> {

                    ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
                    int colunaContador = metaData.getColumnCount();
                    String resultadoConsulta = "";
                    do {
                        for (int i = 1; i <= colunaContador; i++) {
                            String nomecoluna = metaData.getColumnName(i);
                            String valorColuna = resultSet.getString(i);
                            resultadoConsulta += nomecoluna + " : " + valorColuna + "\n";
                        }
                    } while (resultSet.next());
                    Allure.addAttachment("Resultado da consulta", resultadoConsulta);
                });

            }
            // return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            // return false;
        }
    }

    public void excluirAbrigo() {

    }

    // CDS
    public void incluirCD() {

    }

    public void encontrarCD(String codigo) {
        conectarBancoDadosSQL();
        String tabela = "cd";
        String sql = "SELECT * FROM " + tabela + " WHERE code = " + codigo + ";"; // ou "DELETE FROM " + tableName;

        try (
                Connection connection = DriverManager.getConnection(urlSql, usersql, passwordsql);
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            boolean resultado = resultSet.next();

            if (resultado) {

                Allure.step("Resultado da consulta no Banco de dados Centro de Distribuição", () -> {

                    ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
                    int colunaContador = metaData.getColumnCount();
                    String resultadoConsulta = "";
                    do {
                        for (int i = 1; i <= colunaContador; i++) {
                            String nomecoluna = metaData.getColumnName(i);
                            String valorColuna = resultSet.getString(i);
                            resultadoConsulta += nomecoluna + " : " + valorColuna + "\n";
                        }
                    } while (resultSet.next());
                    Allure.addAttachment("Resultado da consulta", resultadoConsulta);
                });

            }
            // return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            // return false;
        }
    }

    public void excluirCD() {

    }

    // Doacao
    public void incluirItemDoacao() {

    }

    public void encontrarItemDoacao(String codigo) {
        conectarBancoDadosSQL();
        String tabela = "cd";
        String sql = "SELECT * FROM " + tabela + " WHERE code = " + codigo + ";"; // ou "DELETE FROM " + tableName;

        try (
                Connection connection = DriverManager.getConnection(urlSql, usersql, passwordsql);
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            boolean resultado = resultSet.next();

            if (resultado) {

                Allure.step("Resultado da consulta no Banco de dados Doação", () -> {

                    ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
                    int colunaContador = metaData.getColumnCount();
                    String resultadoConsulta = "";
                    do {
                        for (int i = 1; i <= colunaContador; i++) {
                            String nomecoluna = metaData.getColumnName(i);
                            String valorColuna = resultSet.getString(i);
                            resultadoConsulta += nomecoluna + " : " + valorColuna + "\n";
                        }
                    } while (resultSet.next());
                    Allure.addAttachment("Resultado da consulta", resultadoConsulta);
                });

            }
            // return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            // return false;
        }
    }

    public void encontrarDoadorDoacao(String codigo) {
        conectarBancoDadosSQL();
        String tabela = "doador";
        String sql = "SELECT * FROM " + tabela + " WHERE id = " + codigo + ";"; // ou "DELETE FROM " + tableName;

        try (
                Connection connection = DriverManager.getConnection(urlSql, usersql, passwordsql);
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            boolean resultado = resultSet.next();

            if (resultado) {

                Allure.step("Resultado da consulta no Banco de dados Abrigo", () -> {

                    ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
                    int colunaContador = metaData.getColumnCount();
                    String resultadoConsulta = "";
                    do {
                        for (int i = 1; i <= colunaContador; i++) {
                            String nomecoluna = metaData.getColumnName(i);
                            String valorColuna = resultSet.getString(i);
                            resultadoConsulta += nomecoluna + " : " + valorColuna + "\n";
                        }
                    } while (resultSet.next());
                    Allure.addAttachment("Resultado da consulta", resultadoConsulta);
                });

            }
            // return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            // return false;
        }
    }

    public void encontrarEnviooacao(String codigo) {
        conectarBancoDadosSQL();
        String tabela = "doacoes";
        String sql = "SELECT * FROM " + tabela + " WHERE id_doacao = " + codigo + ";"; // ou "DELETE FROM " + tableName;

        try (
                Connection connection = DriverManager.getConnection(urlSql, usersql, passwordsql);
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            boolean resultado = resultSet.next();

            if (resultado) {

                Allure.step("Resultado da consulta no Banco de dados Abrigo", () -> {

                    ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
                    int colunaContador = metaData.getColumnCount();
                    String resultadoConsulta = "";
                    do {
                        for (int i = 1; i <= colunaContador; i++) {
                            String nomecoluna = metaData.getColumnName(i);
                            String valorColuna = resultSet.getString(i);
                            resultadoConsulta += nomecoluna + " : " + valorColuna + "\n";
                        }
                    } while (resultSet.next());
                    Allure.addAttachment("Resultado da consulta", resultadoConsulta);
                });

            }
            // return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            // return false;
        }
    }

    public void excluirItemDoacao() {

    }

    // ------------------------ MONGO--------------------------------------

    // Usuarios
    public void encontrarUsuarioId(String idUsuario) {
        conectarBancoDadosMongo();
        try (MongoClient mongoClient = MongoClients.create(urlMongo)) {

            MongoDatabase database = mongoClient.getDatabase(databaseMongo);

            MongoCollection<Document> collection = database.getCollection(colecaoMongo);

            Document filtro = new Document("_id", idUsuario);
            Document resultado = collection.find(filtro).first();

            String buscaRealizada = "Busca realizada: " + filtro.toJson();

            validarPersistencia(buscaRealizada, resultado);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public boolean encontrarUsuarioPeloNome(String nome) {
        conectarBancoDadosMongo();

        try (MongoClient mongoClient = MongoClients.create(urlMongo)) {
            MongoDatabase database = mongoClient.getDatabase(databaseMongo);

            MongoCollection<Document> collection = database.getCollection(colecaoMongo);

            Document filtro = new Document("name", nome);

            Document resultado = collection.find(filtro).first();

            return resultado != null;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

    }

    public boolean encontrarUsuarioPeloCpf(String cpf) {
        conectarBancoDadosMongo();
        try (MongoClient mongoClient = MongoClients.create(urlMongo)) {
            MongoDatabase database = mongoClient.getDatabase(databaseMongo);

            MongoCollection<Document> collection = database.getCollection(colecaoMongo);

            Document filtro = new Document("cpf", cpf);

            Document resultado = collection.find(filtro).first();

            return resultado != null;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

    }

    public String incluirUsuario(String role) {

        String nome = faker.name().fullName();
        while (encontrarUsuarioPeloNome(nome)) {
            nome = faker.name().fullName();
        }
        String cpf = GeradorCpf.gerarCpfSemFormatacao();
        while (encontrarUsuarioPeloCpf(cpf)) {
            cpf = GeradorCpf.gerarCpfSemFormatacao();
        }

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

            InsertOneResult resultado = collection.insertOne(documento);

            return resultado.getInsertedId().asString().getValue();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }

    }

    // ESTOQUE
    public void incluirEstoque(int cd) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("estoque-ms");

            MongoCollection<Document> collection = database.getCollection("stock_item_meta");

            // Criar o documento principal
            Document documento = new Document("_id", new ObjectId().toHexString())
                    .append("codCd", cd)
                    .append("nome", faker.food().ingredient())
                    .append("quantidade", faker.number().numberBetween(1, 1000))
                    .append("unidade", faker.food().measurement())
                    .append("categoria", faker.food().spice())
                    .append("created_at", new Date());

            InsertOneResult resultado = collection.insertOne(documento);
            resultado.getInsertedId().asString().getValue();

            // return resultado.getInsertedId().asString().getValue();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // return null;
        }

    }

    // PEDIDOS
    public void incluirPedido() {

        // PedidosItens pedidosItens = new PedidosItens("1", "1", 1);
        // PedidosItens pedidosItens2 = new PedidosItens("2", "2", 2);

        // List<PedidosItens> itens = new ArrayList<>();
        // itens.add(pedidosItens);
        // itens.add(pedidosItens2);

        // Pedidos pedidos = new Pedidos("1", "1", itens);

        String nome = faker.name().fullName();
        while (encontrarUsuarioPeloNome(nome)) {
            nome = faker.name().fullName();
        }
        String cpf = GeradorCpf.gerarCpfSemFormatacao();
        while (encontrarUsuarioPeloCpf(cpf)) {
            cpf = GeradorCpf.gerarCpfSemFormatacao();
        }

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
                    .append("role", "role")
                    .append("codEntidade", 1)
                    .append("cpf", cpf)
                    .append("created_at", new Date());

            InsertOneResult resultado = collection.insertOne(documento);

            // return resultado.getInsertedId().asString().getValue();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // return null;
        }

    }

}
