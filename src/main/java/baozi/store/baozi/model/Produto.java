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

    private boolean estoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public boolean getEstoque() {
        return getEstoque();
    }

    public void setEstoque(boolean estoque) {
        this.estoque = estoque;
    }

    public Produto(){

    }

    public Produto(String nome, BigDecimal preco, boolean estoque) {

        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;

    }


}
