package prat_eng_soft_api_test_automatizado.TestCase.models;

import java.util.List;

public class Estoque {
    List <EstoqueEntrada> estoqueEntradaDTO;
    
    public Estoque(List<EstoqueEntrada> estoqueEntradaDTO) {
        this.estoqueEntradaDTO = estoqueEntradaDTO;
    }

    public List<EstoqueEntrada> getEstoqueEntradaDTO() {
        return estoqueEntradaDTO;
    }

    public void setEstoqueEntradaDTO(List<EstoqueEntrada> estoqueEntradaDTO) {
        this.estoqueEntradaDTO = estoqueEntradaDTO;
    }

    @Override
    public String toString() {
        return "EstoqueDTO [estoqueEntradaDTO=" + estoqueEntradaDTO + "]";
    }
    
}