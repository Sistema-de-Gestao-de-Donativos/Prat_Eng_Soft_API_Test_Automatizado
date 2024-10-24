package prat_eng_soft_api_test_automatizado.DTO;

import java.util.List;

public class EstoqueDTO {
    List <EstoqueEntradaDTO> estoqueEntradaDTO;
    
    public EstoqueDTO(List<EstoqueEntradaDTO> estoqueEntradaDTO) {
        this.estoqueEntradaDTO = estoqueEntradaDTO;
    }

    public List<EstoqueEntradaDTO> getEstoqueEntradaDTO() {
        return estoqueEntradaDTO;
    }

    public void setEstoqueEntradaDTO(List<EstoqueEntradaDTO> estoqueEntradaDTO) {
        this.estoqueEntradaDTO = estoqueEntradaDTO;
    }

    @Override
    public String toString() {
        return "EstoqueDTO [estoqueEntradaDTO=" + estoqueEntradaDTO + "]";
    }
    
}