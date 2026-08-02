package baozi.store.baozi.service;

import baozi.store.baozi.dto.PedidoRequest;
import baozi.store.baozi.model.Cliente;
import baozi.store.baozi.model.Pedido;
import baozi.store.baozi.model.Produto;
import baozi.store.baozi.repository.ClienteRepository;
import baozi.store.baozi.repository.PedidoRepository;
import baozi.store.baozi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    public Pedido registrarPedido(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Produto produto = produtoRepository.findById(request.produtoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (!produto.getEstoque()) {
            throw new RuntimeException("Produto indisponível no estoque.");
        }

        if (produto.getQuantidade() < request.quantidade()) {
            throw new RuntimeException("Estoque insuficiente");
        }

        produtoService.venderProduto(request.produtoId(), request.quantidade());

        Pedido newPedido = new Pedido();
        newPedido.setCliente(cliente);
        newPedido.setProduto(produto);
        newPedido.setQuantidade(request.quantidade());

        return pedidoRepository.save(newPedido);
    }

}
