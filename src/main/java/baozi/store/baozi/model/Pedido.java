package baozi.store.baozi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    private boolean status;

    public Pedido() {}

    public Pedido(Cliente cliente, Produto produto, Integer quantidade, boolean status) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.status = status;
    }

    public Cliente getCliente() { return cliente; }

    public void setCliente( Cliente cliente) { this.cliente = cliente; }

    public Produto getProduto() { return produto; }

    public void setProduto(Produto produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }

    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public boolean getStatus() {return status;}

    public void setStatus(boolean status) { this.status = status; }

}
