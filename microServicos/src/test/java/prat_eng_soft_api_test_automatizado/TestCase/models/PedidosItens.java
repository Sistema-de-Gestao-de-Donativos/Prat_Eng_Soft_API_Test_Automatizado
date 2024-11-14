package prat_eng_soft_api_test_automatizado.TestCase.models;

public class PedidosItens {

    private String id;
    private String codItem;
    private int quantity;

    public PedidosItens(String id, String codItem, int quantity) {
        this.id = id;
        this.codItem = codItem;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
