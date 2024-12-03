package prat_eng_soft_api_test_automatizado.utils.models;

public class EstoqueEntradaSemUnidade {
    
    private String nome;
    private int quantidade;
    private String dataValidade;
    private String categoria;

    public EstoqueEntradaSemUnidade(String nome, int quantidade, String dataValidade, String categoria) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

   
    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "EstoqueEntradaDTO [categoria=" + categoria + ", dataValidade=" + dataValidade + ", nome=" + nome
                + ", quantidade=" + quantidade +  "]";
    }

    

}
