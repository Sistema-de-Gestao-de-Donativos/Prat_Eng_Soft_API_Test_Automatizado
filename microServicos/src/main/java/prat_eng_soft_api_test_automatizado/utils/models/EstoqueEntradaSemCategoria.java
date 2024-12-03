package prat_eng_soft_api_test_automatizado.utils.models;

public class EstoqueEntradaSemCategoria {
    
    private String nome;
    private int quantidade;
    private String unidade;
    private String dataValidade;

    public EstoqueEntradaSemCategoria(String nome, int quantidade, String unidade, String dataValidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.dataValidade = dataValidade;
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


    @Override
    public String toString() {
        return "EstoqueEntradaDTO [ dataValidade=" + dataValidade + ", nome=" + nome
                + ", quantidade=" + quantidade + ", unidade=" + unidade + "]";
    }

    

}
