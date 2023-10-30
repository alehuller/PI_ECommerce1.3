package pi.quarto.semestre.codigo.model;

public class CarrinhoAllDto {
    private long id;
    private String produtoId;
    private long quantidade;
    private String nome;
    private String valor;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getProdutoId() {
        return produtoId;
    }
    public void setProdutoId(String produtoId) {
        this.produtoId = produtoId;
    }
    public long getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }
}
