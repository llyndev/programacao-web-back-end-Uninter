package baozi.store.baozi.dto;

import baozi.store.baozi.model.Pedido;

import java.math.BigDecimal;

public record PedidoResponse(
        String nomeCliente,
        String nomeProduto,
        BigDecimal precoProduto,
        Integer quantidade,

        boolean status
) {

    public PedidoResponse(Pedido pedido){
        this(pedido.getCliente().getNome(), pedido.getProduto().getNome(), pedido.getProduto().getPreco(), pedido.getQuantidade(), pedido.getStatus());
    }

}
