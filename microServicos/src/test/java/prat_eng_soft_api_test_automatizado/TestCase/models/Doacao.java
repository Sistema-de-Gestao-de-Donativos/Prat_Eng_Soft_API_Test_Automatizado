package prat_eng_soft_api_test_automatizado.TestCase.models;

import java.util.List;

public class Doacao {

    private int codDoador;
    private int CodCD;
    private List<DoacaoItens> itens;

    public Doacao(int codDoador, int codCD, List<DoacaoItens> itens) {
        this.codDoador = codDoador;
        CodCD = codCD;
        this.itens = itens;
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
