package baozi.store.baozi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    private Integer quantidade;

    private Boolean estoque = true;

    public String getNome() {
        return nome;
    }

    public Long getId() { return id; }

    public void setNome(String nome){
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Boolean getEstoque() {
        return estoque;
    }

    public void setEstoque(Boolean estoque) {
        this.estoque = estoque;
    }

    public Produto(){

    }

    public Produto(String nome, BigDecimal preco, int quantidade, Boolean estoque) {

        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque = estoque;

    }

    public void activeEstoque() {
        this.estoque = true;
    }

    public void deactiveEstoque() {
        this.estoque = false;
    }


}
