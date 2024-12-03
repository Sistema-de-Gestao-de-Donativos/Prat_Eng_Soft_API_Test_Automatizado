package prat_eng_soft_api_test_automatizado.utils.models;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DoacaoItens {
    private String nome;
    private String unidade;
    private int qtd;
    private String categoria;

    public DoacaoItens(String nome, String unidade, int qtd, String categoria) {
        this.nome = nome;
        this.unidade = unidade;
        this.qtd = qtd;
        this.categoria = categoria;
    }

    public static DoacaoItens criarItem() {
        Faker faker = new Faker(new Locale("pt-BR"));
        return new DoacaoItens(faker.food().ingredient(), faker.food().measurement(),
                faker.number().numberBetween(1, 1000), faker.food().spice());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "DoacaoItens{" +
                "nome='" + nome + '\'' +
                ", unidade='" + unidade + '\'' +
                ", qtd=" + qtd +
                ", categoria='" + categoria + '\'' +
                '}';
    }

}
