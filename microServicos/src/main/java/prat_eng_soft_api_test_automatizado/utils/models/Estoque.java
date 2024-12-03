package prat_eng_soft_api_test_automatizado.utils.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

public class Estoque {
    List<EstoqueEntrada> estoqueEntradaDTO;

    public Estoque() {
        this.estoqueEntradaDTO = new ArrayList<>();
    }

    public Estoque(List<EstoqueEntrada> estoqueEntradaDTO) {
        this.estoqueEntradaDTO = estoqueEntradaDTO;
    }

    public static List<EstoqueEntrada> criarEstoqueEntradas() {
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

        Estoque estoque = new Estoque();
        estoque.addEstoque(estoqueEntradaDTO);
        return estoque.getEstoqueEntradaDTO();
    }

    public static List<EstoqueEntradaSemNome> criarEstoqueEntradasSemNome() {
        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = dateFormat.format(data);
        Faker faker = new Faker(new Locale("pt-BR"));

        EstoqueEntradaSemNome estoqueEntraSemNomedaDTO = new EstoqueEntradaSemNome(
                faker.number().numberBetween(1, 1000),
                faker.food().measurement(),
                dataFormatada,
                faker.food().spice());

        List<EstoqueEntradaSemNome> estoqueEntradaSemNome = new ArrayList<>();
        estoqueEntradaSemNome.add(estoqueEntraSemNomedaDTO);
        return estoqueEntradaSemNome;
    }

    public static List<EstoqueEntradaSemQuantidade> criarEstoqueEntradaSemQuantidade(){
        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = dateFormat.format(data);
        Faker faker = new Faker(new Locale("pt-BR"));

        EstoqueEntradaSemQuantidade estoqueEntradaSemQuantidadeDTO = new EstoqueEntradaSemQuantidade(
                faker.food().ingredient(),
                faker.food().measurement(),
                dataFormatada,
                faker.food().spice());

        List<EstoqueEntradaSemQuantidade> estoqueEntradaSemQuantidade = new ArrayList<>();
        estoqueEntradaSemQuantidade.add(estoqueEntradaSemQuantidadeDTO);
        return estoqueEntradaSemQuantidade;
    }

    public static List<EstoqueEntradaSemUnidade> criarEstoqueEntradaSemUnidade(){
        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = dateFormat.format(data);
        Faker faker = new Faker(new Locale("pt-BR"));

        EstoqueEntradaSemUnidade estoqueEntradaSemUnidadeDTO = new EstoqueEntradaSemUnidade(
                faker.food().ingredient(),
                faker.number().numberBetween(1, 1000),
                dataFormatada,
                faker.food().spice());

        List<EstoqueEntradaSemUnidade> estoqueEntradaSemUnidade = new ArrayList<>();
        estoqueEntradaSemUnidade.add(estoqueEntradaSemUnidadeDTO);
        return estoqueEntradaSemUnidade;
    }

    public static List<EstoqueEntradaSemDataValidade> criarEstoqueEntradaSemDataValidade(){
        Faker faker = new Faker(new Locale("pt-BR"));

        EstoqueEntradaSemDataValidade estoqueEntradaSemDataValidadeDTO = new EstoqueEntradaSemDataValidade(
                faker.food().ingredient(),
                faker.number().numberBetween(1, 1000),
                faker.food().measurement(),
                faker.food().spice());

        List<EstoqueEntradaSemDataValidade> estoqueEntradaSemDataValidade = new ArrayList<>();
        estoqueEntradaSemDataValidade.add(estoqueEntradaSemDataValidadeDTO);
        return estoqueEntradaSemDataValidade;
    }

    public static List<EstoqueEntradaSemCategoria> criarEstoqueEntradaSemCategoria(){
        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = dateFormat.format(data);
        Faker faker = new Faker(new Locale("pt-BR"));

        EstoqueEntradaSemCategoria estoqueEntradaSemCategoriaDTO = new EstoqueEntradaSemCategoria(
                faker.food().ingredient(),
                faker.number().numberBetween(1, 1000),
                faker.food().measurement(),
                dataFormatada);

        List<EstoqueEntradaSemCategoria> estoqueEntradaSemCategoria = new ArrayList<>();
        estoqueEntradaSemCategoria.add(estoqueEntradaSemCategoriaDTO);
        return estoqueEntradaSemCategoria;
    }




    public  List<EstoqueEntrada> getEstoqueEntradaDTO() {
        return estoqueEntradaDTO;
    }


    public void setEstoqueEntradaDTO(List<EstoqueEntrada> estoqueEntradaDTO) {
        this.estoqueEntradaDTO = estoqueEntradaDTO;
    }

    public void addEstoque(EstoqueEntrada entrada) {
        estoqueEntradaDTO.add(entrada);
    }

    @Override
    public String toString() {
        return "EstoqueDTO [estoqueEntradaDTO=" + estoqueEntradaDTO + "]";
    }

}