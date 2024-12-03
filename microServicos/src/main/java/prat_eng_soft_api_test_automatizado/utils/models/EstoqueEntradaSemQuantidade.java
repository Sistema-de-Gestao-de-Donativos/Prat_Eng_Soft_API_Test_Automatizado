package prat_eng_soft_api_test_automatizado.utils.models;

public class EstoqueEntradaSemQuantidade {
    
    private String nome;
    private String unidade;
    private String dataValidade;
    private String categoria;

    public EstoqueEntradaSemQuantidade(String nome, String unidade, String dataValidade, String categoria) {
        this.nome = nome;
        this.unidade = unidade;
        this.dataValidade = dataValidade;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
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
                + ", unidade=" + unidade + "]";
    }

    

}
