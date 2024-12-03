package prat_eng_soft_api_test_automatizado.utils.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

public class Doacao {

    private int codDoador;
    private int CodCD;
    private List<DoacaoItens> itens;

    public Doacao(int codDoador, int codCD, List<DoacaoItens> itens) {
        this.codDoador = codDoador;
        CodCD = codCD;
        this.itens = itens;
    }

    public static Doacao criarDoacao() {
        Faker faker = new Faker(new Locale("pt-BR"));
        List<DoacaoItens> itens = new ArrayList<>();
        itens.add(DoacaoItens.criarItem());
        return new Doacao(faker.number().numberBetween(1, 1000), faker.number().numberBetween(1, 1000), itens);
    }

    public int getCodDoador() {
        return codDoador;
    }

    public void setCodDoador(int codDoador) {
        this.codDoador = codDoador;
    }

    public int getCodCD() {
        return CodCD;
    }

    public void setCodCD(int codCD) {
        CodCD = codCD;
    }

    public List<DoacaoItens> getItens() {
        return itens;
    }

    public void setItens(List<DoacaoItens> itens) {
        this.itens = itens;
    }
    

    
}
