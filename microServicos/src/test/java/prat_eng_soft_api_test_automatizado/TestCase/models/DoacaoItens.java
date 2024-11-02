package prat_eng_soft_api_test_automatizado.TestCase.models;

public class DoacaoItens {
    private String nome;
    private String validade;
    private String unidade;
    private int qtd;
    private String categoria;

    public DoacaoItens(String nome, String validade, String unidade, int qtd, String categoria) {
        this.nome = nome;
        this.validade = validade;
        this.unidade = unidade;
        this.qtd = qtd;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
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

    

    
}
