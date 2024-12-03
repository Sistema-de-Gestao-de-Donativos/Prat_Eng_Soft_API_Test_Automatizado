package prat_eng_soft_api_test_automatizado.utils.models;

public class PedidosItens {
    
    private String codItem;
    private String nome;
    private int quantidade;

    public PedidosItens(String id, String codItem, int quantity) {
        this.nome = id;
        this.codItem = codItem;
        this.quantidade = quantity;
    }

    public static PedidosItens criarItem() {
        return new PedidosItens("1", "1", 1);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String id) {
        this.nome = id;
    }

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantity) {
        this.quantidade = quantity;
    }

}
