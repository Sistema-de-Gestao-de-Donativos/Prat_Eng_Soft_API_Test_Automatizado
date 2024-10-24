package prat_eng_soft_api_test_automatizado.DTO;

import java.util.List;

public class DoacaoDTO {

    private int codDoador;
    private int CodCD;
    private List<DoacaoItensDTO> itens;

    public DoacaoDTO(int codDoador, int codCD, List<DoacaoItensDTO> itens) {
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

    public List<DoacaoItensDTO> getItens() {
        return itens;
    }

    public void setItens(List<DoacaoItensDTO> itens) {
        this.itens = itens;
    }
    

    
}
