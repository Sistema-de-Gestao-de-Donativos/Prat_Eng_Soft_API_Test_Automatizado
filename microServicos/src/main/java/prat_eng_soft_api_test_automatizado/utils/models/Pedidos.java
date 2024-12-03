package prat_eng_soft_api_test_automatizado.utils.models;

import java.util.List;

public class Pedidos {

    private String codCentroDestribuicao;
    private String codAbrigo;
    private List<PedidosItens> items;

    public Pedidos(String codCentroDestribuicao, String codAbrigo, List<PedidosItens> itens) {
        this.codCentroDestribuicao = codCentroDestribuicao;
        this.codAbrigo = codAbrigo;
        this.items = itens;
    }

    public String getCodCentroDestribuicao() {
        return codCentroDestribuicao;
    }

    public void setCodCentroDestribuicao(String codCentroDestribuicao) {
        this.codCentroDestribuicao = codCentroDestribuicao;
    }

    public String getCodAbrigo() {
        return codAbrigo;
    }

    public void setCodAbrigo(String codAbrigo) {
        this.codAbrigo = codAbrigo;
    }

    public List<PedidosItens> getItems() {
        return items;
    }

    public void setItems(List<PedidosItens> itens) {
        this.items = itens;
    }

}
